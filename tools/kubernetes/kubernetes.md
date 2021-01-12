# Kubernetes (k8s)

Contents:
- [Overview](#Overview)
- [Concepts](concepts.md)
- [Kubectl](kubectl.md)
- [Minikube](minikube.md)
- [Helm](helm.md)

### Clusters

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

### StatefulSet

* Set of pods with an attached volume, that will retain their state after restarts, e.g a persistent cache

## Install Kubectl

https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-linux


## Mysql setup

To setup a client for testing the local mysql image

```
kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
```

## Misc

- zsh has a plugin to make working with kubectl much easier to enable find the 'plugins' config in `~/.zshrc` and set:
`plugins (... kubectl)`
Where '...' is any existing plugins (likely `git`)
