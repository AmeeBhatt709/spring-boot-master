package com.example.elasticsearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "mkyong")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String releaseDate;




}