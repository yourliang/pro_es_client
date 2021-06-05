package com.itcast.es;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class ES_Doc_Query {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = ES_Client.createEsClient("localhost", 9200, "http");

        //1.查询索引中全部数据
        /*SearchRequest request = new SearchRequest();
        request.indices("student", "user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(search.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/


        //2.条件查询: termQuery
        /*SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //3.分頁查詢
       /* SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.from(2);
        builder.size(2);
        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/


        //4.查詢排序
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.DESC);

        request.source(builder);

        SearchResponse searchResponse = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(searchResponse.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //5.过滤字段
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        String[] excludes = {"age"};
        String[] includes = {};
        builder.fetchSource(includes, excludes);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //6.组合查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
       *//* boolQueryBuilder.must(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));*//*
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex", "男"));
        builder.query(boolQueryBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        // 7. 范围查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        rangeQuery.gte(30);
        rangeQuery.lt(50);

        builder.query(rangeQuery);

        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //8. 模糊查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "wangwu").fuzziness(Fuzziness.TWO);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //9.高亮查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "wangwu6667");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        builder.highlighter(highlightBuilder);
        builder.query(termsQueryBuilder);

        request.source(builder);
        SearchResponse searchResponse = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(searchResponse.getTook());

        for(SearchHit hit : hits)
        {
            System.out.println(hit.getSourceAsString());
        }*/

        //10. 聚合查询
/*        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }*/


        // 11. 分组查询
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }


        esClient.close();
    }
}
