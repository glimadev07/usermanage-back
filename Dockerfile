FROM openjdk:21-ea-1-jdk-slim

ENV APP_HOME /app
ENV GRADLE_USER_HOME $APP_HOME/.gradle
ENV PATH $APP_HOME/gradle/bin:$PATH

COPY gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY build.gradle $APP_HOME/
COPY settings.gradle $APP_HOME/
COPY src $APP_HOME/src

WORKDIR $APP_HOME

RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java --enable-preview -jar $(find . -regex '.*usermanage-[0-9]+\\.[0-9]+\\.[0-9]+-SNAPSHOT\\.jar' | head -n 1)"]