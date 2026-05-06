#criação da imagem docker
FROM maven:3.9-eclipse-temurin-21 AS build

#criando literalmente um path
WORKDIR /app

#copiou no /app um pom.xml por conta do ponto
COPY pom.xml .

#baixar as dependencias corretamente para teste.
RUN mvn dependency:go-offline

#copiou o src no path app/src
COPY src ./src

#rodou o maeven limpando arquivos de building e depois compila os arquivos fontes e empacota tudo, com a flag #somente permite a compilação
RUN mvn clean package -DskipTests

#copiou uma imagem do tomcat 11 do jdk 21
FROM tomcat:11.0-jdk21-temurin

#Remove os arquivos de criação do docker fonçando e sem confirmação por conta da -r e -f
RUN rm -rf /usr/local/tomcat/webapps/*

#Copia do drive do mysql-connector no tomcat
COPY  src/main/webapp/WEB-INF/lib/mysql-connector-j-8.1.0.jar /usr/local/tomcat/lib/mysql-connector-j-8.1.0.jar

#copia de Build o que está no primeiro diretorio com o .war e cola no segundo diretorio com o nome root.war
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

#expoe a porta 8080, seria legal já desviar para minha porta
EXPOSE 8080

#Executa o catalina
CMD ["catalina.sh", "run"]