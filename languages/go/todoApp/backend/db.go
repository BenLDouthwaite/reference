package main

import (
	"database/sql"
)

type Datastore interface {
	FindTodos() (Todos, error)
	FindTodo(int) (Todo, error)
	InsertTodo(Todo) (Todo, error)
	DeleteTodo(int) error
}

type DB struct {
	*sql.DB
}

func NewDB(dataSourceName string) (*DB, error) {
	db, err := sql.Open("mysql", dataSourceName)
	if err != nil {
		return nil, err
	}
	if err = db.Ping(); err != nil {
		return nil, err
	}
	return &DB{db}, nil
}
