package com.sbmongo.springboot_mongo_project.service;

import com.sbmongo.springboot_mongo_project.collection.Photo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface PhotoService {
    String addPhoto(String originalFileName, MultipartFile image) throws IOException;

    Photo getPhoto(String id);
}
