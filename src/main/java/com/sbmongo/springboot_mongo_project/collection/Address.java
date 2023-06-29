package com.sbmongo.springboot_mongo_project.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String addressOne;
    private String addressTwo;
    private String city;
}
