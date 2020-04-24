## Backend

### Add new todo

curl -H "Content-Type: application/json" -d '{"name":"New Todo"}' http://localhost:8080/todos

Can run locally to set vars with

DB_URL=127.0.0.1:3306 DB_USER=root DB_PASS=root go run . 