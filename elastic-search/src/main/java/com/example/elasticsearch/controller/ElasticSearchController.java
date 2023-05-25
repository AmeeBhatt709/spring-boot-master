package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.Book;
import com.example.elasticsearch.model.Product;
import com.example.elasticsearch.repository.ElasticSearchQuery;
import com.example.elasticsearch.service.BookService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @Autowired
    private BookService bookService;

    public void ElasticSearchQuery(ElasticSearchQuery elasticSearchQuery) {
        this.elasticSearchQuery = elasticSearchQuery;
    }

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody String book) throws IOException {
        SearchResponse response = elasticSearchQuery.searchBook(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllbook() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<Object> getByTitle(@RequestBody String title) {
        return new ResponseEntity<>(bookService.findByTitle(title), HttpStatus.OK);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws IOException {
        elasticSearchQuery.createProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws IOException {
        elasticSearchQuery.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody String id) throws IOException {
        elasticSearchQuery.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
