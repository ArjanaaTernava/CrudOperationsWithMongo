package com.sbmongo.springboot_mongo_project.service;

import com.sbmongo.springboot_mongo_project.collection.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import org.bson.Document;
import java.util.List;

@Component
public interface PersonService {
    String save(Person person);
    List<Person> getPersonStartWith(String name);

    void delete(String id);

    List<Person> getPersonByAgeInBetween(Integer minAge,Integer maxAge);

    Page<Person> searchPerson(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);

    List<Document> getOldestPersonByCity();

    List<Document> getPopulationByCity();
}
