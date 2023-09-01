# Test simple API to create dockerised Go API

## Build

`docker build -t hello-world-go .`

## Run 

`docker run -p 8080:8080 hello-world-go`

## Test 

`curl http://localhost:8080`