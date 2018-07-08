A Java application that parses a web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration. 

Uses Java 8.0, MySQL 8.0, Spring Boot 2.0, JPA, and Maven 3.5 and several other libraries

Command Line Execution: java -jar target/parser.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200  
