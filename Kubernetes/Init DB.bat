kubectl delete service,deployments,persistentvolumeclaim,configmap,secrets --all
kubectl apply -f Secrets\secrets.yml
kubectl apply -f PersistentVolumeClaim\pvc-db.yml
kubectl apply -f ConfigMap\configmap-db.yml
kubectl apply -f Deployments\deployment-db.yml
kubectl apply -f Services\svc-db.yml