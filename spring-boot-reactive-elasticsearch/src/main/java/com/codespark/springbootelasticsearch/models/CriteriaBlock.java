package com.codespark.springbootelasticsearch.models;

import lombok.Data;

@Data
public class CriteriaBlock {

    private String field;
    private String value;
    private String operator;

}
