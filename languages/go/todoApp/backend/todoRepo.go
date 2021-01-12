package main

func (db *DB) FindTodo(id int) (Todo, error) {
	var todo Todo
	err := db.QueryRow("SELECT id, text FROM todo where id = ?", id).Scan(&todo.Id, &todo.Text)
	if err != nil {
		panic(err.Error()) // proper error handling instead of panic in your app
	}
	return todo, nil
}

func (db *DB) FindTodos() (Todos, error) {

	results, err := db.Query("SELECT id, text FROM todo")
	if err != nil {
		return nil, err
	}

	todos := Todos{}
	for results.Next() {
		var todo Todo
		err = results.Scan(&todo.Id, &todo.Text)
		if err != nil {
			return nil, err
		}
		todos = append(todos, todo)
	}
	if err = results.Err(); err != nil {
		return nil, err
	}
	return todos, nil
}

func (db *DB) InsertTodo(t Todo) (Todo, error) {
	insert, err := db.Prepare("INSERT INTO todo(text) VALUES (?)")
	if err != nil {
		panic(err.Error())
	}
	defer insert.Close()

	_, err = insert.Exec(t.Text)
	if err != nil {
		panic(err.Error())
	}

	return t, nil
}

func (db *DB) DeleteTodo(id int) error {
	delete, err := db.Prepare("DELETE FROM todo WHERE id = ?")
	if err != nil {
		panic(err.Error())
	}
	defer delete.Close()

	_, err = delete.Exec(id)
	if err != nil {
		panic(err.Error())
	}

	return nil
}
