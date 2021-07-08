# Cheatsheet:

- Common commands I use at work, as a reference. 
- Official cheatsheet [here](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)

## Deployments

- Create deployment from yaml file:
`kubectl apply -f my-deployment.yaml`
    - Cab be a URL, e.g.: kubectl apply -f https://k8s.io/examples/controllers/nginx-deployment.yaml
  
## Cronjobs

If you have a scheduled cronjob, but want to trigger it manually
`kubectl create job --from=cronjob/{name} {job-name}`

To patch a scheduled cronjob and suspend it:
`kubectl patch cronjobs {cronjob-name} -p '{"spec" : {"suspend" : true }}'` 
