package com.sbmongo.springboot_mongo_project.service;

import com.sbmongo.springboot_mongo_project.collection.Person;
import com.sbmongo.springboot_mongo_project.repository.PersonRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final MongoTemplate mongoTemplate;

    private final PersonRepository personRepository;

    public PersonServiceImpl(MongoTemplate mongoTemplate, PersonRepository personRepository) {
        this.mongoTemplate = mongoTemplate;
        this.personRepository = personRepository;
    }

    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }

    @Override
    public List<Person> getPersonStartWith(String name) {
        return personRepository.findByFirstNameStartsWith(name);
    }

    @Override
    public void delete(String id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getPersonByAgeInBetween(Integer minAge, Integer maxAge) {
        return personRepository.findByAgeBetween(minAge,maxAge);
    }

    @Override
    public Page<Person> searchPerson(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria>criteria = new ArrayList<>();
        if(name != null && !name.isEmpty()){
            criteria.add(Criteria.where("firstName").regex(name,"i"));
        }
        if(minAge != null &&maxAge!=null){
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }

        if(city != null && !city.isEmpty()){
            criteria.add(Criteria.where("addresses.city").is(city).regex(city,"i"));
        }

        if(!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Person> personPage = PageableExecutionUtils.getPage(
                mongoTemplate.find(query,Person.class),pageable,()->mongoTemplate.count(query.skip(0).limit(0),Person.class)
        );

        return personPage;
    }

    @Override
    public List<Document> getOldestPersonByCity() {

        UnwindOperation unwindOperation = Aggregation.unwind("addresses");

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC,"age");

        GroupOperation groupOperation = Aggregation.group("addresses.city").first(Aggregation.ROOT).as("oldestPerson");

        Aggregation aggregation = Aggregation.newAggregation(unwindOperation,sortOperation,groupOperation);

        List<Document> person = mongoTemplate.aggregate(aggregation,Person.class,Document.class).getMappedResults();
        return person;
    }

    @Override
    public List<Document> getPopulationByCity() {
        UnwindOperation unwindOperation = Aggregation.unwind("addresses");

        GroupOperation groupOperation = Aggregation.group("addresses.city").first(Aggregation.ROOT).as("populationCount");

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC,"populationCount");

        ProjectionOperation projectionOperation = Aggregation.project("populationCount").and("_id").as("city");

        Aggregation aggregation = Aggregation.newAggregation(unwindOperation,sortOperation,groupOperation,projectionOperation);

        List<Document> person = mongoTemplate.aggregate(aggregation,Person.class,Document.class).getMappedResults();
        return person;
    }


}
