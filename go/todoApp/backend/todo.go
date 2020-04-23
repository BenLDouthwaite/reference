package main

type Todo struct {
	Id        int       `json:"id"`
	Text      string    `json:"text"`
	Completed bool      `json:"completed"`
}

type Todos []Todo
