package main

import (
	"database/sql"
	"fmt"
	"log"
	"strconv"
)

var (
	DBCon *sql.DB // TODO Can I remove the global var somehow?
)

func dbSetup() {
	db, err := openConnectionTest()
	err = pingDbTest(err, db)
	insertTest(err, db)
	err = queryManyTest(err, db)
	todo := queryOneTest(err, db)
	fmt.Println(todo)
}

func openConnectionTest() (*sql.DB, error) {
	fmt.Println("DB setup")
	db, err := sql.Open("mysql", "root:root@tcp(127.0.0.1:3306)/todo")
	if err != nil {
		panic(err.Error()) // Just for example purpose. You should use proper error handling instead of panic
	}
	defer db.Close()
	return db, err
}

func pingDbTest(err error, db *sql.DB) error {
	// Open doesn't open a connection. Validate DSN data:
	err = db.Ping()
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}
	return err
}

func queryOneTest(err error, db *sql.DB) Todo {
	var todo Todo
	err = db.QueryRow("SELECT id, text FROM todo where id = ?", 1).Scan(&todo.Id, &todo.Text)
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}
	return todo
}

func queryManyTest(err error, db *sql.DB) error {
	// Execute the query
	results, err := db.Query("SELECT id, text FROM todo")
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}

	for results.Next() {
		var todo Todo
		// for each row, scan the result into our tag composite object
		err = results.Scan(&todo.Id, &todo.Text)
		if err != nil {
			panic(err.Error()) // proper error handling instead of panic in your app
		}
		// and then print out the tag's Name attribute
		log.Println(strconv.Itoa(todo.Id), todo.Text)
	}
	return err
}

func insertTest(err error, db *sql.DB) {
	// perform a getDbCon.Query insert
	insert, err := db.Query("INSERT INTO todo VALUES ( 1, 'TEST' )")

	// if there is an error inserting, handle it
	if err != nil {
		panic(err.Error())
	}
	// be careful deferring Queries if you are using transactions
	defer insert.Close()
}
