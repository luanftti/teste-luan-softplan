# Use a multi-stage build to combine services

# Stage 1: Build the backend
FROM maven:3.8.4-openjdk-17 AS backend-build
WORKDIR /app
COPY api-pessoa /app
RUN mvn clean package

# Stage 2: Build the frontend
FROM node:18.13.0 AS frontend-build
WORKDIR /app
COPY front-pessoa /app
RUN npm install && npm run build

# Stage 3: Final stage
FROM nginx:latest

RUN apt-get update && apt-get install -y openjdk-17-jdk

COPY --from=frontend-build /app/dist/front-pessoa /usr/share/nginx/html

COPY front-pessoa/nginx.conf /etc/nginx/conf.d/default.conf

# Copy the backend jar file
COPY --from=backend-build /app/target/*.jar /app/api-pessoa.jar

# Copy the init-db.sql file
COPY init-db.sql /docker-entrypoint-initdb.d/init-db.sql

# Install PostgreSQL
RUN apt-get update && apt-get install -y postgresql postgresql-contrib

# Set environment variables for PostgreSQL
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin@123
ENV POSTGRES_DB=postgres

# Expose ports
EXPOSE 80 5432 8080

# Start services
CMD service postgresql start && \
    java -jar /app/api-pessoa.jar & \
    nginx -g 'daemon off;'