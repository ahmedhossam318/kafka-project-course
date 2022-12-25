package com.course.kafkaproducer.scheduler;

import com.course.kafkaproducer.entity.Commodity;
import com.course.kafkaproducer.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer commodityProducer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities() {
        // restTemplate
        var commodities = restTemplate.exchange("http://localhost:8080/api/commodity/v1/all", HttpMethod.GET
                , null, new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();
        //stream forEach
        commodities.forEach(c -> {
            try {
                commodityProducer.sendMessage(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
