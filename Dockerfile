FROM openjdk:17-slim
COPY target/*.jar app.jar
EXPOSE 443
RUN mkdir /etc/ssl/cloudflare
COPY config/origin.crt /etc/ssl/cloudflare/origin.crt
COPY config/origin.key /etc/ssl/cloudflare/origin.key
COPY target/VidVault-0.0.1-SNAPSHOT.jar app.jar

WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]
#COPY config/videoupload-453311-3a3aef241e12.json /app/videoupload-453311-3a3aef241e12.json
# older json file->
#COPY config/videoupload-453311-0748888572ad.json /app/videoupload-453311-0748888572ad.json
#COPY .env /app/.env