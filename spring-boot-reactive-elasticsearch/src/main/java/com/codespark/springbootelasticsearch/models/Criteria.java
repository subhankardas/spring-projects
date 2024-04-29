package com.codespark.springbootelasticsearch.models;

import java.util.List;

import lombok.Data;

@Data
public class Criteria {

    private List<CriteriaBlock> criteria;

}
