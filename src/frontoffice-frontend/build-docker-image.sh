DOCKER_USERNAME=$1
PROJECT_VERSION=$2
docker build -t "$DOCKER_USERNAME"/frontend-react:latest .
docker tag "$DOCKER_USERNAME"/frontend-react:latest "$DOCKER_USERNAME"/frontend-react:"$PROJECT_VERSION"