docker stop sisyphus-gateway
docker rm sisyphus-gateway
docker build -t sisyphus-gateway:0.0.1-SNAPSHOT .

docker stop sisyphus-auth-authorize
docker rm sisyphus-auth-authorize
docker build -t sisyphus-auth-authorize:0.0.1-SNAPSHOT .