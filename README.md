# README #

Spring boot microservice 

## Run

```
#!java
java -Dserver.port=`/$CONFIG_DIR/available-port.sh` -Dlogs=$LOGS_DIR -jar auth-service-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
```
