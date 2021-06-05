package com.itcast.es;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ES_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.add(new IndexRequest().index("user").id("1010").source(XContentType.JSON, "name", "wangwu6667", "age" , 60, "sex", "男"));


        bulkRequest.add(new IndexRequest().index("user").id("1009").source(XContentType.JSON, "name", "wangwu6666", "age" , 60, "sex", "男"));

        BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
        System.out.println(bulk.getItems());

        esClient.close();
    }
}
