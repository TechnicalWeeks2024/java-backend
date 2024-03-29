FROM maven:3-openjdk-17
WORKDIR /app
COPY . .
RUN mvn -f pom.xml clean package -DskipTests
EXPOSE 7777
WORKDIR /app/target
ENTRYPOINT ["java", "-jar", "magic-java-be.jar"]