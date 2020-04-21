package com.searchoutfit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webscraper.Webscraper;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AppApplication.class, args);
        Webscraper w =new Webscraper();
        List<String> clothesImages = w.getClothesImages();
        System.out.println(clothesImages.toString());
    }

}
