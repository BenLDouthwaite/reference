# Kubernetes

## Overview

Clusters:
* Allow several computers to work as a single unit
* K8s automates management of containers
* 2 types of resources, 
** Master to coordinate a cluster, scheduling, state, scaling, updates
** Nodes to run apps. VMs or PCs as a worker
*** Nodes communicate with the master using the Kubernetes API

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

# Minikube

## Commands

`minikube start`
Configures kubectl to use minikube and start a cluster

`minikube version`
Check version. shockingly.