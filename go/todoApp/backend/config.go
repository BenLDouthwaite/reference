package main

import (
	"fmt"
	"os"
)

func getConfig() map[string]string {
	cfg := map[string]string{}
	cfg["dataSourceUrn"] = getDataSourceUrn()
	return cfg
}

func getDataSourceUrn() string {
	dbUrl := os.Getenv("DB_URL")
	dbUser := os.Getenv("DB_USER")
	dbPass := os.Getenv("DB_PASS")

	return fmt.Sprintf("%s:%s@tcp(%s)/todo", dbUser, dbPass, dbUrl)
}
