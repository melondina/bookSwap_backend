# This part of dockerfile intended for build Java app with Maven
# Start from create Maven build
FROM maven as build

# Create work directory - В Docker-контейнерах существует понятие "рабочей директории" (working directory),
# которая представляет собой директорию внутри контейнера, в которой выполняются команды. Когда вы устанавливаете
# рабочую директорию в Dockerfile с помощью команды WORKDIR, все последующие команды (такие как COPY, RUN, CMD и другие)
# в Dockerfile будут выполняться внутри этой директории, если не указано иное.

WORKDIR /workspace/app

# Create copy of the files pom and src, from project to the docker container -
# After command will be completed, pom.xml will be in Root directory. Src in the folder src.
COPY pom.xml .
COPY src src

#Runs a Maven command inside a container. This command first cleans the project (clean), then assembles the project
#into a JAR file (package). The -DskipTests=true flag specifies that tests should not be run at build time.
RUN mvn -DskipTests=true clean package

#Creates a target/dependency directory inside the container and extracts all JAR files from the target directory
# (where Maven usually stores the built JAR) inside this new directory.
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#Next part show how to build and start Java-app from container.
#base image for the container. eclipse-temurin:17-jre-alpine is an image with Java Runtime Environment (JRE) version 17
# installed on Alpine Linux.
FROM eclipse-temurin:17-jre-alpine

#This line defines the Docker image build argument. In the context of Docker, build arguments allow you to pass variable
# values while building an image. These variables can be defined in the Dockerfile using the ARG keyword and can be used
# in other Dockerfile statements
ARG DEPENDENCY=/workspace/app/target/dependency

#This command copies the files from the ${DEPENDENCY}/BOOT-INF/lib folder (which probably contains the libraries needed
# to run the application) to /app/lib inside the container.
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib

#This command copies files from the ${DEPENDENCY}/META-INF folder (often used for application metadata)
#to /app/META-INF inside the container.
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

#This command copies files from the ${DEPENDENCY}/BOOT-INF/classes folder (which probably contains your application's
#compiled classes) to the root directory / app inside a container.
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#ENTRYPOINT [...]: Эта строка определяет точку входа для контейнера. В данном случае, при запуске контейнера будет
# выполнена команда java -cp app:app/lib/* -Dspring.profiles.active=prod de.ait.BackendDemoApplication. Это запустит
#Java-приложение с указанными параметрами класспаса, профилем Spring (prod) и основным классом de.ait.BackendDemoApplication.
ENTRYPOINT ["java","-cp","app:app/lib/*", "-Dspring.profiles.active=prod", "de.ait.todo.BackendDemoApplication"]