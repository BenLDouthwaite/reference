# Hello World Nginx

A simple project intended to demonstrate some k8s concepts, running on minikube

## How to deploy Nginx - Easy version (No config)

1. Create the deployment 
`kubectl create deployment nginx --image=nginx`

2. Expose to the network
`kubectl create service nodeport nginx --tcp=80:80`

3. Run the service on minikube
`minikube service nginx`

The service is now available!

## How to deploy Nginx - Custom version

1. Define the custom nginx config in a config map:
    [Configmap example](config-nginx.yaml)
`kubectl apply -f config-nginx.yaml`

2. Define the deployment in a yaml file:
    [Deployment example](deployment-nginx.yaml)
`kubectl apply -f deployment-nginx.yaml`
    - Note: The selected label for the pod is `nginx-label`
    
3. Expose our service
    [Service example](service-nginx.yaml)
`kubectl apply -f service-nginx.yaml`

4. Run the service on minikube
`minikube service nginx-service`

We can now see out configured Nginx server returning "Hello World"