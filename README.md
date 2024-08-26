# Spring Boot MongoDB Project

This is a Spring Boot application that integrates with MongoDB. It demonstrates basic CRUD operations using MongoDB as the database.

## Project Structure

- `src/main/java/com/sbmongo/springboot_mongo_project`
   - `collection/` - Contains MongoDB model classes.
      - `Address.java`
      - `Person.java`
      - `Photo.java`
   - `controller/` - Contains REST controllers.
      - `PersonController.java`
      - `PhotoController.java`
   - `repository/` - Contains MongoDB repositories.
      - `PersonRepository.java`
      - `PhotoRepository.java`
   - `service/` - Contains service interfaces and implementations.
      - `PersonService.java`
      - `PersonServiceImpl.java`
      - `PhotoService.java`
      - `PhotoServiceImpl.java`
   - `SpringbootMongoProjectApplication.java` - The main application class.

- `src/main/resources/`
   - `application.properties` - Configuration file for MongoDB and other settings.


## Configuration

The application uses MongoDB as the database. Update the `src/main/resources/application.properties` file with your MongoDB connection details.

# Setup and Run

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ArjanaaTernava/CrudOperationsWithMongo.git
    cd CrudOperationsWithMongo
    ```

2. **Build the project using Maven:**

    ```bash
    ./mvnw clean install
    ```

3. **Run the application:**

    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application:**

   The application will be available at [http://localhost:8080](http://localhost:8080).

# Technologies

- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) Spring Boot
- ![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=flat&logo=mongodb&logoColor=white) MongoDB
- ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white) Maven

