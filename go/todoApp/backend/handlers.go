package main

import (
	"encoding/json"
	"fmt"
	"html"
	"io"
	"io/ioutil"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

func Index() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello, %q", html.EscapeString(r.URL.Path))
	}
}

func TodoIndex(env *Env) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		todos, err := env.db.FindTodos()
		if err != nil {
			panic(err.Error())
		}

		w.Header().Set("Content-Type", "application/json; charset=UTF-8")
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.WriteHeader(http.StatusOK)
		if err := json.NewEncoder(w).Encode(todos); err != nil {
			panic(err)
		}
	})
}

func TodoShow(env *Env) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		vars := mux.Vars(r)
		id, err := strconv.Atoi(vars["todoId"])
		if err != nil {
			panic(err)
		}
		todo, err := env.db.FindTodo(id)
		if err != nil {
			panic(err)
		}

		w.Header().Set("Content-Type", "application/json; charset=UTF-8")
		w.WriteHeader(http.StatusOK)
		if err := json.NewEncoder(w).Encode(todo); err != nil {
			panic(err)
		}
	})
}

func TodoCreate(env *Env) http.Handler {

	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {

		var todo Todo
		body, err := ioutil.ReadAll(io.LimitReader(r.Body, 1048576))
		if err != nil {
			panic(err)
		}
		if err := r.Body.Close(); err != nil {
			panic(err)
		}
		if err := json.Unmarshal(body, &todo); err != nil {
			w.Header().Set("Access-Control-Allow-Origin", "*")
			w.Header().Set("Content-Type", "application/json; charset=UTF-8")
			w.WriteHeader(422) // Unprocessable entity
			if err := json.NewEncoder(w).Encode(err); err != nil {
				panic(err)
			}
		}

		t, err := env.db.InsertTodo(todo)
		if err != nil {
			panic(err)
		}

		w.Header().Set("Content-Type", "application/json; charset=UTF-8")
		w.Header().Set("Access-Control-Allow-Origin", "*")

		w.WriteHeader(http.StatusCreated)
		if err := json.NewEncoder(w).Encode(t); err != nil {
			panic(err)
		}
	})
}

func TodoDelete(env *Env) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		vars := mux.Vars(r)
		id, err := strconv.Atoi(vars["todoId"])

		if err != nil {
			panic(err)
		}
		err = env.db.DeleteTodo(id)
		if err != nil {
			panic(err)
		}

		w.Header().Set("Content-Type", "application/json; charset=UTF-8")
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.WriteHeader(http.StatusOK)
	})
}
