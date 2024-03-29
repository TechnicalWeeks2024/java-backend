FROM maven:3-openjdk-17
WORKDIR /app
COPY . .
RUN pwd  && ls -l && chmod +x mvnw
RUN mvn -f pom.xml clean package -DskipTests
ARG JAR_FILE=target/magic-java-be.jar
EXPOSE 7777
WORKDIR /app/target
RUN  pwd  && ls -l
ENTRYPOINT ["java", "-jar", "magic-java-be.jar"]