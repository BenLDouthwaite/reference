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
