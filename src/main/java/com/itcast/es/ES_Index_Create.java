package com.itcast.es;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class ES_Index_Create {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");

        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse =
                esClient.indices().create(request, RequestOptions.DEFAULT);

        //响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作 ：" + acknowledged);
        ES_Client.closeEsClient(esClient);
    }
}
