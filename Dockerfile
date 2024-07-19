# Étape 1: Construire l'application
FROM maven:3.9.8 AS build
WORKDIR /app

# Copier le fichier pom.xml et le répertoire src
COPY pom.xml .
COPY src ./src

# Construire le package JAR
RUN mvn clean package -DskipTests

# Étape 2: Créer l'image d'exécution
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copier le JAR construit depuis l'étape précédente
COPY --from=build /app/target/examPlanApp-0.0.1-SNAPSHOT.jar /app/examPlanApp.jar

# Commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "/app/examPlanApp.jar"]
