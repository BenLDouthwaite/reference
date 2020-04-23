package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"log"
	"net/http"
)

func main() {

	//dbSetup()

	var err error
	DBCon, err = sql.Open(
		"mysql",
		"root:root@tcp(127.0.0.1:3306)/todo")
	if err != nil {
		panic(err)
	}
	//err = queryManyTest(err, DBCon)

	fmt.Println("Demo Chat Application")
	router := NewRouter()
	log.Fatal(http.ListenAndServe(":8080", router))
}
