#mvn clean install -am -amd -Pdev -Dmaven.test.skip=true -Dsun.net.maxDatagramSockets=50
docker stop sisyphus-uac
docker rm sisyphus-uac
docker build -t sisyphus-uac:0.0.1-SNAPSHOT .
#docker run -d --network sisyphus-master_default --name sisyphus-auth-authorize -p 8010:8080 sisyphus-auth-authorize
