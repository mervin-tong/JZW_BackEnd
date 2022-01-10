#!/bin/bash

kubectl delete -f piesat-school-rpc.yaml
sleep 2
echo "pod delete over"
docker build -t 172.31.19.227:1180/pie-engine-application/piesat-school-rpc:1.0 .
docker push 172.31.19.227:1180/pie-engine-application/piesat-school-rpc:1.0
echo "images push sucess"

kubectl apply -f piesat-school-rpc.yaml
sleep 10
echo "piesat-school-rpc server ready to start"

kubectl  logs -f `kubectl get po -A | grep piesat-school-rpc | awk '{print$2}'` -n pie-engine-application
