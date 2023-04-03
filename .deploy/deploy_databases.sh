docker compose up applicationdb -d
sleep 10
docker exec -t applicationdb psql -U postgres -c "CREATE DATABASE authenticationdb;"
docker exec -t applicationdb psql -U postgres -c "CREATE DATABASE scriptdb;"
docker exec -t applicationdb psql -U postgres -c "CREATE DATABASE executiondb;"
