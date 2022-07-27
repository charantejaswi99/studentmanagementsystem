FROM openjdk:8


# Copy the jar to the production image from the builder stage.
COPY  target/sms.jar sms.jar


# Run the web service on container startup.
CMD java  -jar /sms.jar