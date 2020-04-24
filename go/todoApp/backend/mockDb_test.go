package main

import (
	"net/http"
	"net/http/httptest"
	"testing"
)

type mockDB struct{}

func (mdb *mockDB) FindTodos() (Todos, error) {
	todos := make(Todos, 0)
	todos = append(todos, Todo{
		Id:        1,
		Text:      "Test1",
		Completed: false,
	})
	todos = append(todos, Todo{
		Id:        2,
		Text:      "Test2",
		Completed: false,
	})
	return todos, nil
}

func TestTodoIndex(t *testing.T) {

	rec := httptest.NewRecorder()
	req, _ := http.NewRequest("GET", "/books", nil)

	env := &Env{db: &mockDB{}}
	TestIndex(env).ServeHTTP(rec, req)

	expected := "TODO {1 Test1 false}TODO {2 Test2 false}"
	if expected != rec.Body.String() {
		t.Errorf("\n...expected = %v\n...obtained = %v", expected, rec.Body.String())
	}
}
