# Utilize uma imagem base com o JDK (Java Development Kit) desejado
FROM openjdk:11-jre-slim

# Adicione uma descrição ou etiquetas se quiser
LABEL description="Aplicação Java"

# Defina o diretório de trabalho no contêiner
WORKDIR /usr/app

# Copie o JAR da sua aplicação para o diretório de trabalho no contêiner
COPY ./build/libs/dom-soft-0.0.1-SNAPSHOT.jar /usr/app/

# Exponha a porta que sua aplicação utilizará (altere para a porta correta da sua aplicação, se necessário)
EXPOSE 8080

# Defina o comando padrão para executar sua aplicação
CMD ["java", "-jar", "dom-soft-0.0.1-SNAPSHOT.jar"]