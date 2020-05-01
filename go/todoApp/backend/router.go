package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"net/http"
)

func NewRouter(env *Env, allowOrigin string) *mux.Router {
	router := mux.NewRouter().StrictSlash(true)

	setupPreFlightResponse(router, allowOrigin)

	for _, route := range getRoutes(env) {
		logHandler := Logger(route.Handler, route.Name)
		router.
			Methods(route.Method).
			Path(route.Pattern).
			Name(route.Name).
			Handler(logHandler)
	}
	return router
}

func setupPreFlightResponse(router *mux.Router, allowOrigin string) *mux.Route {
	return router.Methods("OPTIONS").HandlerFunc(
		func(w http.ResponseWriter, r *http.Request) {
			fmt.Println("preflight detected: ", r.Header)
			w.Header().Add("Connection", "keep-alive")
			w.Header().Add("Access-Control-Allow-Origin", allowOrigin)
			w.Header().Add("Access-Control-Allow-Methods", "POST, OPTIONS, GET, DELETE, PUT")
			w.Header().Add("Access-Control-Allow-Headers", "content-type")
			w.Header().Add("Access-Control-Max-Age", "86400")
		})
}
