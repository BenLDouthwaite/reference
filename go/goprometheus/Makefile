# Local App commands

# Build the go app

# Docker Stuff

# K8s / Minikube stuff 
.PHONY: kubectl-deploy
kubectl-deploy:
	kubectl apply -f deploy/app/deployment.yaml
	kubectl apply -f deploy/app/service.yaml


# minikube service go-hello-service

# TODO What are the roles for, can I avoid them?
.PHONY: deploy-roles
deploy-roles:
	kubectl apply -f deploy/monitoring/cluster-role.yaml
	kubectl apply -f deploy/monitoring/service-account.yaml
	kubectl apply -f deploy/monitoring/cluster-role-binding.yaml
	
.PHONY: deploy-prometheus
deploy-prometheus:
	kubectl apply -f deploy/monitoring/config-map.yaml
	kubectl apply -f deploy/monitoring/deployment.yaml
	kubectl apply -f deploy/monitoring/service.yaml