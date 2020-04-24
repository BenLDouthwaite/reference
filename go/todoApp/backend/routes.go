package main

import (
	"net/http"
)

type Route struct {
	Name    string
	Method  string
	Pattern string
	Handler http.Handler
}

type Routes []Route

func getRoutes(env *Env) Routes {
	var routes = Routes{
		Route{
			"Index",
			"GET",
			"/",
			Index(),
		},
		Route{
			"TodoIndex",
			"GET",
			"/todo",
			TodoIndex(env),
		},
		Route{
			"TodoShow",
			"GET",
			"/todo/{todoId}",
			TodoShow(env),
		},
		Route{
			Name:    "TodoCreate",
			Method:  "POST",
			Pattern: "/todo",
			Handler: TodoCreate(env),
		},
		Route{
			Name:    "TodoDelete",
			Method:  "DELETE",
			Pattern: "/todo/{todoId}",
			Handler: TodoDelete(env),
		},
	}
	return routes
}
