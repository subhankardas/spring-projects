package com.codespark.springbootelasticsearch.models;

import com.codespark.springbootelasticsearch.common.OsType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OsDetails {

    private String name;
    private String version;
    private OsType type;

}
