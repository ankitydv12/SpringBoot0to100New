Commit ---> Module2.3.2 -> Lambok | h2

In this commit the lombok and h2(In memory database) are added and now the data will save in the h2 database .
The configuration are handle by lombok
From the POSTMAN we can store data to database and also fetch it from

The H2 database uses inmemory database and the url is --> Database JDBC URL [jdbc:h2:mem:22ea8ff6-8f7b-4f37-a07a-cdafee3d8115]

We can store the data to file also by add some properties in application.properties:
    spring.datasource.url=jdbc:h2:file:/media/ankit/Learning/SpringBoot0to100NewLocal/module2/src/main/java/com/ankit/module2/db
    spring.datasource.username=ankitydv12
    spring.datasource.password=Ankit@123

Configuration of some properties in application.properties:
    spring.h2.console.enabled=true --> enable the h2 console at url
    spring.jpa.hibernate.ddl-auto=create -->  Drops existing tables and CREATES fresh tables every time the application starts.
    spring.jpa.hibernate.ddl-auto=none --> Hibernate does NOT perform any schema operation. # Database schema must already exist.
    spring.jpa.hibernate.ddl-auto=update --> Updates the existing schema based on Entity changes. # Preserves existing data.
