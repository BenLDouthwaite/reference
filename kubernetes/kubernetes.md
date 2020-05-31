# Kubernetes

## Overview

Clusters:
* Allow several computers to work as a single unit
* K8s automates management of containers
* 2 types of resources, 
** Master to coordinate a cluster, scheduling, state, scaling, updates
** Nodes to run apps. VMs or PCs as a worker
*** Nodes communicate with the master using the Kubernetes API

* A pod is a group of one or more containers, tied together for the purposes of amin and networking
* A pod can have only one container.
* A deployment checks the health of a pod, and restarts it is it terminates
* deployments are the recommended was to manage the creation and scaling of pods

* by default, pods are only accessible to the internal IP address in the kuebrnetes cluster
* must expose the pod as a service to be seen from external IP

## Install Kubectl

https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-linux

## Commands

`kubectl cluster-info`
Check cluster info that things are running

`kubectl version`
Check the version, good check it's installed properly
Checks client and server

`kubectl get nodes`
View the nodes in the cluster that can be used to host applications

`kubectl create`
Can be used to create a deployment that manages a pod, the pod runs a container based on the provided docker images
example:
`kubectl create deployment hello-node --image=gcr.io/hello-minikube-zero-install/hello-node`


`kubectl get deployments`
View the deployments

`kubectl get pods`
View the pods

`kubectl get events`

`kubectl config view`
View the config... really hope I can clear these soon.

`kubectl expose`
Expose a pod to public internet
example:
`kubectl expose deployment hello-node --type=LoadBalancer --port=8080`

TODO w** Because I've done this at least 5 times now, after running the above command locally, run the service via minikube (see below)
to make sure it's actually accessible. Delete this once I stop being an idiot and actually remember **

On cloud providers that support load balancers, an external IP address would be provisioned to access the Service. On Minikube, the LoadBalancer type makes the Service accessible through the minikube service command.
`minikube service hello-node`
This will open the service in a browser

Clean up commands
`kubectl delete service hello-node`
`kubectl delete deployment hello-node`
`minikube stop` : optional step
`minikube delete` : optional step

We can describe a failing pod
Example:
`kubectl describe pod failing-pod-name`

`kubectl rollout restart deployment/deployment-name`

To view the logs from a running container, run:

`kubectl logs -f pod-name`

## Mysql setup

To setup a client for testing the local mysql imaget

```
kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
```

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