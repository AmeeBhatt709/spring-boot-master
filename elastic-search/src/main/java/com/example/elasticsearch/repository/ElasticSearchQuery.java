package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.Product;
import com.google.gson.Gson;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class ElasticSearchQuery {

    private final String indexName = "mkyong";

    private final RestHighLevelClient restHighLevelClient;


    public ElasticSearchQuery(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public SearchResponse searchBook(String query) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", query));
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    public void createProduct(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName)
                .source(new Gson().toJson(product), XContentType.JSON);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

    }

    public void updateProduct(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName)
                .source(new Gson().toJson(product), XContentType.JSON);
        restHighLevelClient.update(new UpdateRequest(indexName, product.getId())
                .doc(indexRequest)
                .docAsUpsert(true), RequestOptions.DEFAULT);
    }

    public void deleteProduct(String product) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(indexName)
                .id(product);


        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

    }
}
