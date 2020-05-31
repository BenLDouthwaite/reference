# API Gateway

Public REST APIs for clients 

Client talks to API Gateway, which proxys requests to lambda, which can query Dynamo DB

* Lambda + Gateway = No infrastructure!
* Support for WebSockets
* Handle many environments
* Handles Authentication and Authorisation
* Can work with Swagger

Integrations:
* Lambda -> Can invoke them. Easiest way to expose REST API for fully serverless app.
* HTTP -> Can expose any REST API, e.g. on Elastic Beanstalk
** Allows rate limiting, caching, user ath etc
* Any AWS Service
** Expose AWS Step Function etc

Endpoint Types:
* Edge-Optimised -> For global clients. Routed through CloudFront edge locations. 

## Steps to get connection from API Gateway to accessible URL:

* Create new API Gateway API
* Create our lambda to be invoked
* Create method connecting a path to the lambda
* Deploy API -> Set stage. Will then receive a URL
