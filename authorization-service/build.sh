mvn clean package -DskipTests
docker build -t authorization-service:1.0 .
docker push authorization-service:1.0
docker stack deploy --resolve-image always --compose-file docker-compose.yml --with-registry-auth test
