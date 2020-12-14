# Minikube

## Installation
### Mac

` brew install minikube`

## Notes

By defult, created with limited cpu and memory,
must stop and delete minikube before recreating with more cpu and mem
e.g. `minikube stop && minikube delete && minikube start --cpus 4 --memory 8192`

## Commands

`minikube start`
Configures kubectl to use minikube and start a cluster

`minikube version`
Check version. shockingly.

`minikube dashboard`
View a GUI dashboard in browser

# Example deployment

( Note point to your minikube docker when local )
`eval $(minikube docker-env)`

* Build the docker image (in correct dir)
`docker build -t docker-image-name .`

`kubectl create deployment my-dep-name --image=docker-image-name`

* Check deployment
`kubectl get deployments`
`kubectl get pods` -> Check no issues

* Expose deployment
`kubectl expose deployment my-dep-name --type=LoadBalancer --port=8080`

* Check service
`kubectl get services`

* Run the service on minikube
`minikube service my-dep-name`

`kubectl get deployments -o wide`
Allows us to see the image version

`kubectl set image deployment personal-website personal-website=benldouthwaite/personal-website:v0.0.4`

Probably not the best steps for updating the image but they work...
```
docker build -t benldouthwaite/personal-website:latest .
docker push benldouthwaite/personal-website:latest
kubectl set image deployment personal-website personal-website=benldouthwaite/personal-website:latest
```

TODO Look up imagePullPolicy. Would be cool to get this setup

## Mamaging your cluster

Minikube is configured to persist files stored under the following host directories:

/data
/var/lib/minikube
/var/lib/docker