docker compose up applicationdb -d
docker exec -T applicationdb psql -U postgres -c "CREATE DATABASE authenticationdb;"
docker exec -T applicationdb psql -U postgres -c "CREATE DATABASE scriptdb;"
docker exec -T applicationdb psql -U postgres -c "CREATE DATABASE executiondb;"
