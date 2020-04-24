package main

import (
	"github.com/gorilla/mux"
)

func NewRouter(env *Env) *mux.Router {
	router := mux.NewRouter().StrictSlash(true)
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
