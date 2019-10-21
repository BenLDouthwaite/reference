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

# Minikube

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
