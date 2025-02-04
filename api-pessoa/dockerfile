# Usando a imagem do OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Define o diretório de trabalho
WORKDIR /app

# Instala o Git e o Maven
RUN apt-get update && apt-get install -y git maven

# Clona o repositório (substitua pelo link correto)
RUN git clone https://github.com/luanftti/api-pessoa.git

# Compila a aplicação
WORKDIR /app/api-pessoa
RUN mvn clean package -DskipTests

# Segunda etapa: cria um container mais leve para rodar a aplicação
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR gerado do build para o novo container
COPY --from=build /app/api-pessoa/target/api-pessoa-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do backend
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
