# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Install unzip (required to extract the zip package)
RUN apt-get update && apt-get install -y unzip && rm -rf /var/lib/apt/lists/*

# Install AWS Lambda Runtime Interface Emulator (RIE)
# The RIE is used to mimic the Lambda environment locally
ADD https://github.com/aws/aws-lambda-runtime-interface-emulator/releases/latest/download/aws-lambda-rie /usr/local/bin/aws-lambda-rie
RUN chmod +x /usr/local/bin/aws-lambda-rie

# Set the working directory
WORKDIR /function

# Copy the Lambda package and unzip it
COPY target/devtube3-1.0-SNAPSHOT-lambda-package.zip /function
RUN unzip devtube3-1.0-SNAPSHOT-lambda-package.zip && rm devtube3-1.0-SNAPSHOT-lambda-package.zip

# Set the entry point to use RIE for Lambda
ENTRYPOINT ["/usr/local/bin/aws-lambda-rie", "java", "-jar", "/function/app.jar"]

# Expose port 8080 if needed for debugging or testing purposes
EXPOSE 8080