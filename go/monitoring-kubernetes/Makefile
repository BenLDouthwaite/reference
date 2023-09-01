# Application
.PHONY: deploy-application
deploy-application:
	kubectl apply -f deploy/app/deployment.yaml
	kubectl apply -f deploy/app/service.yaml

# Docker
.PHONY: docker-push
docker-push:
	docker build -t benldouthwaite/go-hello:latest -f Dockerfile .
	docker push benldouthwaite/go-hello:latest
	docker rmi benldouthwaite/go-hello:latest
	docker system prune --volumes --force

# Roles
.PHONY: deploy-roles
deploy-roles:
	kubectl apply -f deploy/monitor/service-account.yaml
	kubectl apply -f deploy/monitor/cluster-role.yaml
	kubectl apply -f deploy/monitor/cluster-role-binding.yaml

# Prometheus
.PHONY: deploy-prometheus
deploy-prometheus:
	kubectl apply -f deploy/prometheus/config-map.yaml
	kubectl apply -f deploy/prometheus/deployment.yaml
	kubectl apply -f deploy/prometheus/service.yaml

# Grafana
.PHONY: deploy-grafana
deploy-grafana:
	kubectl apply -f deploy/grafana/deployment.yaml
	kubectl apply -f deploy/grafana/service.yaml
	kubectl apply -f deploy/grafana/pvc.yaml
