package com.sbmongo.springboot_mongo_project.repository;

import com.sbmongo.springboot_mongo_project.collection.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo,String> {
}
