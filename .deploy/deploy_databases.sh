docker compose up applicationdb -d
docker exec -it applicationdb psql -U postgres -c "CREATE DATABASE authenticationdb;"
docker exec -it applicationdb psql -U postgres -c "CREATE DATABASE scriptdb;"
docker exec -it applicationdb psql -U postgres -c "CREATE DATABASE executiondb;"
