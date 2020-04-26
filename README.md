# Final project for FMI Course Spring v5 
[https://github.com/iproduct/course-spring5/wiki]


## Technologies used:
- Java 1.8, Spring Boot 2.2
- jjwt 0.9.1
- NodeJS v12.14.0, Angular 8
- MongoDB 4.2
- Docker&Docker-compose
- Gradle 6.0.1
  - https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html
  - https://docs.mongodb.com/manual/administration/install-community/
  - https://angular.io/guide/setup-local


## Functional summary:
Travel by bus (TBB) is online system for browsing and buying bus tickets. It supports different roles – Traveler, Bus Company and Admin. The system provides ability for travelers to search the best route, and for bus companies to manage their lines. In addition to that it allows users to register, and administrators to manage them.

The system is developed using Spring 5 Application Development Framework. The frontend uses Angular 8 to make Single Page Application (SPA).  The backend is implemented as a REST/JSON API using JSON data serialization. Routing is done client-side. JWT is used for authorization.
The main user roles are:
* Traveler
  * Search route by start point, end point and travel date
(and optionally return date)
  * Filter results by different criteria – duration, price, distance
  * Buy ticket
  * View list of all bought tickets
* Administrator
  * Manages users and bus lines
*	Bus Company 
    * Create new bus lines with information for start point, end point, price, distance, duration, departure time, arrival time, number of seats
    * Edit/Delete bus line
* All users 
  * Edit profile data – password, first name, last name


## Detailed description:
- in Tbb documentation
