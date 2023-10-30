FROM  openjdk:17
LABEL maintanier ="vorobev kovrov"
EXPOSE 8080
COPY . target/spring_boot_library-0.0.1.jar /usr/app/
ADD target/spring_boot_library-0.0.1.jar spring_boot_library-0.0.1.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","spring_boot_library-0.0.1.jar"]


