# Kubectl

## Commands

* Will usually alias `k` = `kubectl`

`kubectl cluster-info`
Check cluster info that things are running

`kubectl version`
Check the version, good check it's installed properly
Checks client and server

`kubectl get nodes`
View the nodes in the cluster that can be used to host applications
If using minikube, will be a single node, 'minikube'
    
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

`kubectl port-forward {pod-name} 7000:6379`
`kubectl port-forward {pod-name} 7000:6379`
The above would forward requests to localhost:7000 to port 6379 on the given pod
Generally should only be used for dev purposes.

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
