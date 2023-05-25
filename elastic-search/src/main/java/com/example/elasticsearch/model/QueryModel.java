package com.example.elasticsearch.model;

import lombok.Data;

@Data
public class QueryModel {
    private String index;
    private String query;
}
