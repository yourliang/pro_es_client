package com.itcast.es;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class ES_Index_Delete {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");

        DeleteIndexRequest request = new DeleteIndexRequest("user");

        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        ES_Client.closeEsClient(esClient);
    }
}
