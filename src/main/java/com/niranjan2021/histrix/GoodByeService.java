package com.niranjan2021.histrix;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoodByeService {


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;

    @HystrixCommand(fallbackMethod = "forSlowConnection")
    public String getGoodBye() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = discoveryClient.getInstances("goodbye-service").get(0).getUri().toString();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();

    }

    private String forSlowConnection() {
        return "good bye for slow connections";
    }
}
