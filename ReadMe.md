Get the RabbitMq Docker Admin DockerFile and build the image


Start the container & expose the ports
docker run -d -p 127.0.0.1:15672:15672/tcp -p 127.0.0.1:5672:5672/tcp <image-id>
