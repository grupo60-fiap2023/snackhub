kubectl delete service,deployments,persistentvolumeclaim,configmap --all
kubectl apply -f PersistentVolumeClaim\pvc-db.yml
kubectl apply -f ConfigMap\configmap-db.yml
kubectl apply -f Deployments\deployment-db.yml
kubectl apply -f Services\svc-db.yml

kubectl apply -f Deployments\deployment-app.yml
kubectl apply -f Services\svc-app.yml