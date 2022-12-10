FROM openjdk:18
EXPOSE 8080
ADD target/design-blog.jar design-blog.jar
ENTRYPOINT ["java","-jar","/design-blog.jar"]