FoodTaxi backend
=====================

The application is based on Spring, Maven, Liquibase and Swagger

## Commands for Liquibase 

mvn liquibase:diff  -- generate diff 

mvn liquibase:update -- update the db with the changelogs from the master.xml


## Commands for Spring Boot 

 mvn spring-boot:run -- run the server

## Command for deploy on the heroku server
git subtree push --prefix source/backend/FoodTaxi heroku master