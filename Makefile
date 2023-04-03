clean:
	mvn clean

build: clean
	mvn package

run: clean
    ifeq ($(shell uname), Windows)
        for dir in src/*; do \
            if [ -d $$dir ]; then \
                cd $$dir; \
                mvn spring-boot:run & \
                cd ../..; \
            fi; \
        done
    else
        for dir in src/*; do
          if [ -d "$dir" ]; then
            cd "$dir"
            mvn spring-boot:run &
            cd ../..
          fi
        done
    endif

seed: clean
    mvn package -P seed-database

test: clean
	mvn test