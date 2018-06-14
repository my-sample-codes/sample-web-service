Get the RabbitMq Docker Admin DockerFile from https://hub.docker.com/_/rabbitmq
```
cd <your-local-folder>
wget  <rabbitmq/3.7/debian/management/Dockerfile>
```
Build the image
```
docker build -t rahul/rabbitmqadmin .
```
Find the image id
```
docker images
```
Start the container & expose the ports
```
docker run -d -p 127.0.0.1:15672:15672/tcp -p 127.0.0.1:5672:5672/tcp <image-id>
```
Get into the bash of your running container
```
docker exce -it <jolly_williams <-- containmer name> /bin/bash
```
Execute commands to list exchanges, queues etc.
