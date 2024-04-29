package com.codespark.springbootelasticsearch.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.codespark.springbootelasticsearch.common.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "endpoints_", createIndex = false)
public class Endpoint {

    // ----- Constants -----
    public static final String INDEX_NAME_PREFIX = "endpoints_";

    // ----- Fields -----
    @Id
    private UUID machineId;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Ip)
    private String ipAddress;

    @Field(type = FieldType.Object)
    private OsDetails os;

    @Field(type = FieldType.Object)
    private List<InstalledSoftware> installedSoftwares;

    @Field(type = FieldType.Boolean)
    private boolean active;

    /**
     * Returns the index name based on the machineId.
     * This is done to optimize search performance.
     */
    @JsonIgnore
    public String getIndexName() {
        return INDEX_NAME_PREFIX + (Math.abs(machineId.hashCode()) % Constants.BUCKET_COUNT);
    }

}
