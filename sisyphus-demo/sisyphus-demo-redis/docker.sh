docker stop sisyphus-demo-redis
docker rm sisyphus-demo-redis
docker build -t sisyphus-demo-redis .
docker run -d --mac-127.0.0.1 --network sisyphus-master_default --name sisyphus-demo-redis -p 8002:8080 sisyphus-demo-redis

