package main

import (
	"fmt"
	"html"
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

var responseStatus = prometheus.NewCounterVec(
	prometheus.CounterOpts{
		Name: "response_status",
		Help: "Status of HTTP response",
	},
	[]string{"status"},
)

var httpDuration = promauto.NewHistogramVec(prometheus.HistogramOpts{
	Name: "http_response_time_seconds",
	Help: "Duration of HTTP requests",
}, []string{"path"})

type StatusRecorder struct {
	http.ResponseWriter
	Status int
}

func (r *StatusRecorder) WriteHeader(status int) {
	r.Status = status
	r.ResponseWriter.WriteHeader(status)
}

func prometheusMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		path := r.URL.Path

		timer := prometheus.NewTimer(httpDuration.WithLabelValues(path))

		recorder := &StatusRecorder{
			ResponseWriter: w,
			Status:         200,
		}
		next.ServeHTTP(recorder, r)

		statusCode := recorder.Status

		responseStatus.WithLabelValues(strconv.Itoa(statusCode)).Inc()
		totalRequests.WithLabelValues(path).Inc()

		timer.ObserveDuration()

		fmt.Printf("Handling request for %s from %s, status: %d\n", path, r.RemoteAddr, recorder.Status)
	})
}

func init() {
	fmt.Println("init method")
	prometheus.Register(totalRequests)
	prometheus.Register(responseStatus)
	prometheus.Register(httpDuration)
}

func main() {
	fmt.Println("Start web server with prometheus metrics")

	hello := http.HandlerFunc(helloHandler)
	http.Handle("/hello", prometheusMiddleware(hello))

	http.Handle("/metrics", promhttp.Handler())
	http.ListenAndServe(":8080", nil)
}

func handler() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "test, path = %q", html.EscapeString(r.URL.Path))
	}
}

func helloHandler(w http.ResponseWriter, r *http.Request) {

	// Mock performing work
	delay := time.Duration(rand.Intn(1000)) * time.Millisecond
	time.Sleep(delay)

	// Mock failure chance
	if failureChance := rand.Intn(10); failureChance < 2 {
		http.Error(w, "Error", http.StatusInternalServerError)
		return
	}

	fmt.Fprintf(w, "Hello!")
}
