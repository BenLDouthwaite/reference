# Rest Assured

Usage guide, https://github.com/rest-assured/rest-assured/wiki/Usage 

## Body Assertions

### Asserting elements in an array response
```
RestAssured.given()
    .get("http://myUrl.com/myEndpoint")
    .then()
    .statusCode(200)
    .body(
            "size()", equalTo(1),
            "[0].someField", equalTo(someValue)
    )
```