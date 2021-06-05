package com.itcast.es;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ES_Doc_Update {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");


        //修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");

        request.doc(XContentType.JSON, "sex", "女");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());


        ES_Client.closeEsClient(esClient);
    }
}
