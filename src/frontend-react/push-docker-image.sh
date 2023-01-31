PROJECT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
DOCKER_USERNAME=$(mvn help:evaluate -Dexpression=docker.username -q -DforceStdout)
docker build -t "$DOCKER_USERNAME"/frontend-react:latest .
docker push "$DOCKER_USERNAME"/frontend-react:latest
docker tag "$DOCKER_USERNAME"/frontend-react:latest "$DOCKER_USERNAME"/frontend-react:"$PROJECT_VERSION"
docker push "$DOCKER_USERNAME"/frontend-react:"$PROJECT_VERSION"