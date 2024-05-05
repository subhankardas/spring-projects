# Base image for Java runtime
FROM openjdk:17-alpine 

# Add author info
LABEL Author="Subhankar Das" Email="subhankardas831@gmail.com"

# Set working directory
WORKDIR /app

# Add the application's jar to the container
COPY target/spring-boot-k8s.jar spring-boot-k8s.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","spring-boot-k8s.jar"]