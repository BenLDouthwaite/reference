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

		//Route{
		//	"OptionsTest",
		//	"OPTIONS",
		//	"/todo",
		//	corsHandle(),
		//},
	}
	return routes
}

//func corsHandle() http.HandlerFunc {
//	return func(w http.ResponseWriter, r *http.Request) {
//		fmt.Println("preflight detected: ", r.Header)
//		w.Header().Add("Connection", "keep-alive")
//		w.Header().Add("Access-Control-Allow-Origin", "http://localhost:3000")
//		w.Header().Add("Access-Control-Allow-Methods", "POST, OPTIONS, GET, DELETE, PUT")
//		w.Header().Add("Access-Control-Allow-Headers", "content-type")
//		w.Header().Add("Access-Control-Max-Age", "86400")
//	}
//}
