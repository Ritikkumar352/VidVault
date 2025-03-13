FROM openjdk:17-oracle
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
#COPY config/videoupload-453311-3a3aef241e12.json /app/videoupload-453311-3a3aef241e12.json
# older json file->
#COPY config/videoupload-453311-0748888572ad.json /app/videoupload-453311-0748888572ad.json
#COPY .env /app/.env