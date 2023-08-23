kubectl delete Services app-svc-lb
kubectl delete Deployments aplicacao-deployment

kubectl apply -f Deployments\deployment-app.yml
kubectl apply -f Services\svc-app.yml