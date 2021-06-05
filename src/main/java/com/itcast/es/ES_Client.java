package com.itcast.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ES_Client {

    //创建es客户端
    public static RestHighLevelClient createEsClient(String hostname, Integer port, String scheme){

        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostname, port, scheme))
        );
        return esClient;
    }


    //关闭es客户端
    public static void closeEsClient(RestHighLevelClient client){
        try {
            if(client != null)
            {
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
