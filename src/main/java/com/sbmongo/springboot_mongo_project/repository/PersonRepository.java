package com.sbmongo.springboot_mongo_project.repository;

import com.sbmongo.springboot_mongo_project.collection.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByFirstNameStartsWith(String name);

//    List<Person> findByAgeBetween(Integer minAge, Integer maxAge);
    @Query(value = "{'age': {$gt: ?0, $lt:  ?1}}",
    fields = "{addresses:  0}")
    List<Person> findByAgeBetween(Integer minAge, Integer maxAge);

}
