package main

import (
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"log"
	"net/http"
)

type Env struct {
	db Datastore
}

func main() {
	fmt.Println("TODO App")

	cfg := getConfig()
	db, err := NewDB(cfg["dataSourceUrn"])
	if err != nil {
		panic(err)
	}

	env := &Env{db}
	router := NewRouter(env)
	log.Fatal(http.ListenAndServe(":8080", router))
}
