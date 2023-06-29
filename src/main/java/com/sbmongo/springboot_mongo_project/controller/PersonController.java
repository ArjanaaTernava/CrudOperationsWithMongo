package com.sbmongo.springboot_mongo_project.controller;

import com.sbmongo.springboot_mongo_project.collection.Person;
import com.sbmongo.springboot_mongo_project.service.PersonService;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public String savePerson(@RequestBody Person person){
        return personService.save(person);
    }

    @GetMapping
    public List<Person> getPersonStartWith(@RequestParam("name") String name){
        return personService.getPersonStartWith(name);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable String id){
        personService.delete(id);
        return "Person deleted successfully, with id: " +id;
    }

    @GetMapping("/age")
    public List<Person> getPersonByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return personService.getPersonByAgeInBetween(minAge,maxAge);
    }

    @GetMapping("/search")
    public Page<Person> searchPerson(@RequestParam(required = false) String name, @RequestParam(required = false) Integer minAge ,
                                     @RequestParam(required = false) Integer maxAge, @RequestParam(required = false) String city,
                                     @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5")Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return personService.searchPerson(name,minAge,maxAge,city,pageable);
    }
    @GetMapping("/oldest")
    public List<Document> getOldestPerson(){
        return personService.getOldestPersonByCity();
    }

    @GetMapping("/population")
    public List<Document> getPopulationByCity(){
        return personService.getPopulationByCity();
    }
}
