# Kubernetes (k8s)

## Overview

## Minikube [here](minikube.md)
## Helm [here](helm.md)

### Clusters:

* Allow several computers to work as a single unit
* K8s automates management of containers
* 2 types of resources, 
** Master to coordinate a cluster, scheduling, state, scaling, updates
** Nodes to run apps. VMs or PCs as a worker
*** Nodes communicate with the master using the Kubernetes API

### Pods

* The basic unit for running containers inside k8s.
* Pods provide ways to set env vars, mount storage and feed info into a container
* Every pod holds at least 1 container, and controls their execution
    * When containers exit, the pod dies too

*Old*
* A deployment checks the health of a pod, and restarts it is it terminates
* deployments are the recommended was to manage the creation and scaling of pods

* by default, pods are only accessible to the internal IP address in the kuebrnetes cluster
* must expose the pod as a service to be seen from external IP

### ReplicaSets

* ReplicaSets are a low level type in k8s
* Users often choose higher level abstractions
* ReplicaSets ensure a set of identical pods will run at a configured replica count
    * If one pod dies, another will be created
    
### Secrets

* Secrets can be attached as files or env vars
* Base 64 encoded at rest, decoded when attached to a pod
* Use to store tokens, certs, passwords
* Can be attached at runtime, so config data is stored securely

### Deployments

* Controls deploying and maintaining a set of pods
    * Uses a ReplicaSet to keep pods running
* Support rolling updates and rollbacks.
* Can be paused

### DaemonSets

* Can ensure a copy of a pod is running on every node in a cluster
* Will scale with a cluster as it grows or shrinks

### Ingresses

* Route traffic to and from a cluster
* Provide SSL endpoint for multiple apps.

### CronJobs

* Provide scheduling the execution of pods
    * Excellent for backups, reports and automated tests
    
### CustomResourceDefinition (CRD)

* Can create custom resource types.

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

To setup a client for testing the local mysql image

```
kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
```