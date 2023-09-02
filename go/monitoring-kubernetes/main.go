package main

import (
	"fmt"
	"math/rand"
	"net/http"
	"strconv"
	"time"

	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promauto"
	"github.com/prometheus/client_golang/prometheus/promhttp"
)

var totalRequests = prometheus.NewCounterVec(
	prometheus.CounterOpts{
		Name: "http_requests_total",
		Help: "Number of get requests",
	},
	[]string{"path"},
)

var httpDuration = promauto.NewHistogramVec(prometheus.HistogramOpts{
	Name: "http_response_time_seconds",
	Help: "Duration of HTTP requests",
}, []string{"path"})

// Define our responseStatus status counter
var responseStatus = prometheus.NewCounterVec(
	prometheus.CounterOpts{
		Name: "response_status",
		Help: "Status of HTTP response",
	},
	[]string{"status"},
)

// Create our status recorder struct
type StatusRecorder struct {
	http.ResponseWriter
	Status int
}

// Implementent ResponseWriter on StatusRecorder
func (r *StatusRecorder) WriteHeader(status int) {
	r.Status = status
	r.ResponseWriter.WriteHeader(status)
}

func init() {
	rand.Seed(time.Now().UnixNano())
	prometheus.Register(totalRequests)
	prometheus.Register(httpDuration)
	// Register the new metric
	prometheus.Register(responseStatus)
}

func prometheusMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		path := r.URL.Path
		timer := prometheus.NewTimer(httpDuration.WithLabelValues(path))

		// Wrap out responseWriter in our custom StatusRecorder
		recorder := &StatusRecorder{
			ResponseWriter: w,
			Status:         200,
		}
		next.ServeHTTP(recorder, r)

		statusCode := recorder.Status
		responseStatus.WithLabelValues(strconv.Itoa(statusCode)).Inc()

		totalRequests.WithLabelValues(path).Inc()
		timer.ObserveDuration()
	})
}

func main() {
	hello := http.HandlerFunc(helloHandler)
	http.Handle("/", prometheusMiddleware(hello))
	http.Handle("/metrics", promhttp.Handler())
	http.ListenAndServe(":8080", nil)
}

func helloHandler(w http.ResponseWriter, r *http.Request) {

	delay := time.Duration(rand.Intn(1000)) * time.Millisecond
	time.Sleep(delay)

	if failureChance := rand.Intn(10); failureChance < 2 {
		http.Error(w, "Error", http.StatusInternalServerError)
		return
	}

	fmt.Fprintf(w, "Hello, World!")
}
