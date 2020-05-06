## Update from 05-2020
Deploy the project to AWS for FMI course;
- Final project for FMI Course Spring v5: in branch course-project-spring5

### Spring Boot with AWS DocumentDB

AWS Document DB integration with spring data mongodb repository:

Update mongo uri specified by AWS DocumentDB cluster in <i>application.properties</i> file or pass it as an environment variable to point to cluster endpoint

```
spring.data.mongodb.uri=${MONGO_URI}
```

#### SSL setup for AWS DocumentDB: 

To connect through SSL, set below environment variable pointing to location of the certificate [https://s3.amazonaws.com/rds-downloads/rds-ca-2019-root.pem]. Alternatively this can be copied to the base directory.

```
java -jar app.jar -DsslCertificate=<<PATH TO SSL CERTIFICATE>>
```

A trust store is created in the main application class with the CA certificate contained in the file.

- Run using java command 

```
java -jar app.jar -DsslCertificate=/home/ec2-user/rds-ca-2019-root.pem -DSPRING_DATA_MONGO_URI=mongodb://<<cluster endpoint>>
```
