package com.searchoutfit.app;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import com.searchoutfit.app.imageprocess.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import webscraper.Product;
import webscraper.Webscraper;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class AppApplication {



    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        Webscraper webscraper = new Webscraper();
        List<Product> products = webscraper.getClothesImages();
        System.out.println(products);
    }
}
