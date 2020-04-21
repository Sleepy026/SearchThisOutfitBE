package com.searchoutfit.app;

import com.searchoutfit.app.imageprocess.aboutYouPOJO2.AboutYouPOJO2;
import com.searchoutfit.app.imageprocess.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AppApplication {


    @Autowired
    ClothService clothService;

    public static final String apiEndpoint = "https://api-cloud.aboutyou.de/v1/products?page=";
    List<AboutYouPOJO2> items = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @PostConstruct
    public void init(){
        for (int i = 1; i < 10; i++) {
            items.add(clothService.getImagesFromURL(apiEndpoint+i+"&perPage=1000"));
        }
        System.out.println("asd");
    }


}
