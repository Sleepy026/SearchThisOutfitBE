package com.searchoutfit.app;

import imageprocess.aboutYouPOJO2.AboutYouPOJO2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class AppApplication {

    public static final String apiEndpoint = "https://api-cloud.aboutyou.de/v1/products?page=1&perPage=1000";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AppApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AboutYouPOJO2> request = new HttpEntity<>(new AboutYouPOJO2());
        ResponseEntity<AboutYouPOJO2> resp = restTemplate.exchange(apiEndpoint,
                HttpMethod.GET,
                request,
                AboutYouPOJO2.class);
        AboutYouPOJO2 valami = resp.getBody();



        System.out.println("asd");

    }

}
