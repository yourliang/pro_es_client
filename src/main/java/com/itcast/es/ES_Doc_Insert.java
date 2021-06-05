package com.itcast.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ES_Doc_Insert {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");

        //插入数据
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1001");
        User user = new User();
        user.setName("zhangshan");
        user.setAge(30);
        user.setSex("男");

        //向ES插入数据，必须将数据转换为JSON格式
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);
        IndexResponse response = esClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        ES_Client.closeEsClient(esClient);
    }
}
