package com.searchoutfit.app.imageprocess.service;

import com.searchoutfit.app.imageprocess.aboutYouPOJO2.AboutYouPOJO2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClothService {

    public AboutYouPOJO2 getImagesFromURL(String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AboutYouPOJO2> request = new HttpEntity<>(new AboutYouPOJO2());
        ResponseEntity<AboutYouPOJO2> resp = restTemplate.exchange(url,
                HttpMethod.GET,
                request,
                AboutYouPOJO2.class);
        return resp.getBody();
    }

}
