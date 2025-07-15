# Usa una imagen base con Java 17 (por ejemplo, Adoptium OpenJDK o Eclipse Temurin)
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR de tu aplicación al contenedor
# Asegúrate de reemplazar 'target/your-app.jar' con la ruta real de tu JAR compilado
# Y 'your-app.jar' con el nombre final de tu JAR
COPY target/classes/com/talento_tech/MercadoLiebreByEricApplication app.jar

# Expone el puerto que tu aplicación usa (ej. 8080 para Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "app.jar"]