# Test simple API to create dockerised Python API

## Build

`docker build -t hello-world-python .`

## Run 

`docker run -p 8080:8080 hello-world-python`

## Test 

`curl http://localhost:8080`

## Push image to Docker Hub

(if you've not yet, register on Docker Hub)
`docker login --username=YOUR_USERNAME`
`docker push YOUR_USERNAME/hello-world-python` 

## Deploy
### Deploy 1 - AWS UI

At this point you can deploy the docker image using AWS ECS (Elastic Container Service)
here: https://console.aws.amazon.com/ecs/home 

### Deploy 2 - AWS ECS CLI

Tutorial Here : https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_AWSCLI_Fargate.html#ECS_AWSCLI_Fargate_create_cluster

Note: This requires you to have setup the subnet and security group already

`aws ecs create-cluster --cluster-name myTestCluster`

`aws ecs register-task-definition --cli-input-json file://taskfile.json`

```
aws ecs create-service \
    --cluster myTestCluster \
    --service-name hello-service \
    --task-definition hello:2 \
    --desired-count 1 \
    --launch-type "FARGATE" \
    --network-configuration "awsvpcConfiguration={subnets=[subnet-07e5693d78c8c0b08],securityGroups=[sg-09498c923851a1a12],assignPublicIp=ENABLED}"
```

`aws ecs list-services --cluster myTestCluster`

`aws ecs describe-services --cluster myTestCluster --services hello-service`

From here, you can find the ip of the running service in the UI, and can then access port 8080 on the IP.

### Deploy 3 - AWS Cloud Formation

DISCLAIMER: Still in progress, errored the first time, worked the second. Give it a try

This will use AWS Cloud Formation to deploy the app, by configuring all the infrastructure as code.
In this project, this is configured in the `network.yml` and `service.yml` files.

#### Running the Cloud Formation deployment commands

This example taken from https://reflectoring.io/aws-cloudformation-deploy-docker-image/ 
Create the network 
``` 
aws cloudformation create-stack \
  --stack-name test-hello-world-python-network \
  --template-body file://network.yml \
  --capabilities CAPABILITY_IAM
```

Describe the stacks, and see if the "StackStatus" is CREATED

`aws cloudformation describe-stacks`

Create the service
```
aws cloudformation create-stack \
  --stack-name test-hello-world-python-service \
  --template-body file://service.yml \
  --parameters \
      ParameterKey=StackName,ParameterValue=test-hello-world-python-network \
      ParameterKey=ServiceName,ParameterValue=test-hello-world-python \
      ParameterKey=ImageUrl,ParameterValue=benldouthwaite/hello-world-python:latest \
      ParameterKey=ContainerPort,ParameterValue=8080 \
      ParameterKey=HealthCheckPath,ParameterValue=/ \
      ParameterKey=HealthCheckIntervalSeconds,ParameterValue=90
```