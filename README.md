# DistributedExample

How to start the DistributedExample application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/distributed-1.0-SNAPSHOT.jar server config/config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your application's health enter url `http://localhost:8081/healthcheck`


Contributing
---

You need a few things to get started:

* Java 11 – I'm using Amazon Corretto, but in theory any open Java 11 JDK will work
* Maven - installed using Homebrew (`brew install maven`)
* an IDE - the right choice is IntelliJ