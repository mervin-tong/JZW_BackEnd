#!/bin/bash

kubectl delete -f piesat-school-rest.yaml
sleep 2
echo "pod delete over"
docker build -t 262870713489.dkr.ecr.cn-northwest-1.amazonaws.com.cn/pie-engine-application:piesat-school-rest-1.0 .
docker push 262870713489.dkr.ecr.cn-northwest-1.amazonaws.com.cn/pie-engine-application:piesat-school-rest-1.0
echo "images push sucess"

kubectl apply -f piesat-school-rest.yaml
sleep 15
echo "piesat-school-rest server ready to start"

kubectl  logs -f `kubectl get po -A | grep piesat-school-rest | awk '{print$2}'` -n pie-engine-application
