package com.searchoutfit.app.imageprocess.service;

import com.searchoutfit.app.imageprocess.aboutYouPOJO2.AboutYouPOJO2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClothService {

    private List<AboutYouPOJO2> items = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();

    private AboutYouPOJO2 getImagesFromURL(String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AboutYouPOJO2> request = new HttpEntity<>(new AboutYouPOJO2());
        ResponseEntity<AboutYouPOJO2> resp = restTemplate.exchange(url,
                HttpMethod.GET,
                request,
                AboutYouPOJO2.class);
        return resp.getBody();
    }

    @PostConstruct
    private void init(){
        for (int i = 1; i < 10; i++) {
            items.add(getImagesFromURL("https://api-cloud.aboutyou.de/v1/products?page="+i+"&perPage=1000"));
        }
        getHashes();
        System.out.println(getUrls().toString());
    }

    private void getHashes(){
        items.forEach(x-> x.getEntities()
                .forEach(y-> y.getImages()
                        .forEach(z-> imageUrls.add("https://cdn.aboutstatic.com/file/images/" + z.getHash() + "?width=600&height=800&quality=100"))));
    }

    public List<String> getUrls(){
        return imageUrls;
    }

}
