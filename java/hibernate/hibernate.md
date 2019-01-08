## Hibernate 

### Log SQL commands to console

#### Spring Boot app

In a spring boot app, simple add 

`
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true
`

To the spring Boot app's `application.properties` file

### Hibernate JPA 

When using hibernate JPA to query objects, using nativeQuery = true can ensure data is returned as objects

In contrast, if querying to get dto data from another entities table, it will be returned as an array
