# Base image containing Java runtime (in our case it's Java 17)
# After checking on docker hub, this is the version with the least vulnerabilities. Here is the vulnerability detail :
# Denial of service vulnerability. Affects MimeKit (>= v3.0.0 and <= v4.7.0) when used to decrypt or verify incoming
# S/MIME messages as well as importing 3rd-party X.509 certificates for use with encrypting outgoing S/MIME messages.

FROM openjdk:24-ea-17-jdk-windowsservercore-ltsc2022

# Add Maintainer Info
LABEL maintainer="nadim.mohamedamine.pro@gmail.com"

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/bereadii-app.jar app.jar

# Expose the application port
EXPOSE 8070

# Set environment variables for MongoDB connection
ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/bereadii

# Run the application
CMD ["java", "-jar", "app.jar"]