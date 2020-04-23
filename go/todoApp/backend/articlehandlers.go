package main

import (
	"encoding/json"
	"net/http"
)

type Article struct {
	Title      string    `json:"title"`
	Content string      `json:"content"`
}

type Articles []Article

func ArticlesIndex(w http.ResponseWriter, r *http.Request) {
	articles := Articles {
		Article{Title:"Test title 1", Content:"Test content 1"},
		Article{Title:"Test title 2", Content:"Test content 2"},
		Article{Title:"Test title 3", Content:"Test content 3"},
	}

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.WriteHeader(http.StatusOK)
	if err := json.NewEncoder(w).Encode(articles); err != nil {
		panic(err)
	}
}

