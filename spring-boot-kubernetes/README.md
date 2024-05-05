## Spring Boot + Docker + Kubernetes
**Developing and deploying Spring Boot microservices on Kubernetes** - This guide delves into the exciting journey of building and deploying Spring Boot applications with the power of Docker and Kubernetes. We'll navigate through the steps, from crafting the initial Spring Boot app to orchestrating it within a Kubernetes cluster.

Here's a breakdown of the steps to deploy a Spring Boot app using Docker and Kubernetes:

1. **Build the application**: This creates a JAR file containing your application code.
```cmd
mvn clean install -DskipTests
```
2. **Run the application**: This will create a docker image from our Dockerfile and run services locally for our testing purpose.
```cmd
docker compose -f ./docker-compose-prod.yml up -d --build
```
3. **Push the Docker Image to a Registry**: To deploy your application to a remote Kubernetes cluster, tag and push the image to a container registry like Docker Hub.
```bash
docker login

docker tag spring-boot-k8s:latest subhankardas831/spring-boot-k8s:latest                

docker push subhankardas831/spring-boot-k8s:latest
```
4. **Create a Kubernetes Deployment and Service**: We will use Kompose to convert our docker compose file to k8s deployment and service yaml files. Install Kompose by following installation guide at [Kompose.io](https://kompose.io/installation/).
```bash
kompose convert -f ./docker-compose-prod.yml -o ./k8s
```
5. Now we update the deployment file for our application to use the image from the Docker Hub i.e. *subhankardas831/spring-boot-k8s:latest* after replacing the image in the file.
6. **Deploy to Kubernetes**: Use the generated files i.e. the deployment and service YAML files to deploy them to your Kubernetes cluster.
```bash
kubectl apply -f ./k8s
```
7. Now to see the running pods we can use the command:
```cmd
kubectl get pods
```
The console output should look like this, where we can see both our database and application running in k8s pods.
```cmd
NAME                               READY   STATUS    RESTARTS   AGE
mysqldb-7b5f687946-fl5hq           1/1     Running   0          4s
spring-boot-k8s-58c78fb9fc-2pxpn   1/1     Running   0          4s
```
8. Similarly we can check our running services:
```cmd
kubectl get services
```
The console output should look like this, where we can see our services running locally as well as our application exposed for TCP connections.
```cmd
NAME                  TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
kubernetes            ClusterIP      10.96.0.1        <none>        443/TCP          34s
mysqldb               ClusterIP      10.105.119.86    <none>        3306/TCP         25s
spring-boot-k8s       ClusterIP      10.107.41.52     <none>        8080/TCP         25s
spring-boot-k8s-tcp   LoadBalancer   10.106.245.242   localhost     8080:32559/TCP   25s
```
9. To delete our running deployments or services we can use commands
```cmd
kubectl delete deployment mysqldb, spring-boot-k8s

kubectl delete --all services
```