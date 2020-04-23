package main

import (
	"database/sql"
	"fmt"
)

var currentId int

func getDbCon() *sql.DB {
	db, err := sql.Open(
		"mysql",
		"root:root@tcp(127.0.0.1:3306)/todo")
	if err != nil {
		panic(err.Error())
	}
	return db
}

func FindAllTodos() Todos {
	db := getDbCon()

	results, err := db.Query("SELECT id, text FROM todo")
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}

	todos := Todos{}
	for results.Next() {
		var todo Todo
		err = results.Scan(&todo.Id, &todo.Text)
		if err != nil {
			panic(err.Error()) // proper error handling instead of panic in your app
		}
		todos = append(todos, todo)
	}
	return todos
}

func FindOneTodo(id int) Todo {
	db := getDbCon()

	var todo Todo
	err := db.QueryRow("SELECT id, text FROM todo where id = ?", id).Scan(&todo.Id, &todo.Text)
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}
	return todo
}

func RepoCreateTodo(t Todo) Todo {

	db := getDbCon()

	fmt.Println(t)
	t.Id = currentId

	insert, err := db.Prepare("INSERT INTO todo(text) VALUES (?)")
	if err != nil {
		panic(err.Error())
	}
	defer insert.Close()

	_, err = insert.Exec(t.Text)
	if err != nil {
		panic(err.Error())
	}

	return t
}

// TODO Implement delete functionality
//func RepoDestroyTodo(id int) error {
//	for i, t := range todos {
//		if t.Id == id {
//			todos = append(todos[:i], todos[i+1:]...)
//			return nil
//		}
//	}
//	return fmt.Errorf("Could not find Todo with id of %d to delete", id)
//}
