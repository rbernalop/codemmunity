clean:
	mvn clean

build: clean
	mvn package

run: clean
	for dir in src/*; do \
		if [ -d "$dir" ]; then \
			cd "$dir"; \
			mvn spring-boot:run & \
			cd ../..; \
		fi; \
	done

seed: clean
	mvn package -P seed-database -DskipTests

test: clean
	mvn test