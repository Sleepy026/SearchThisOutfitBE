package com.searchoutfit.app;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import com.searchoutfit.app.imageprocess.service.ClothService;
import com.searchoutfit.app.vision.ManageProductSet;
import com.searchoutfit.app.vision.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import webscraper.Product;
import webscraper.Webscraper;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@SpringBootApplication
public class AppApplication {


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

//        Webscraper webscraper = new Webscraper();
//        List<Product> products = webscraper.getClothesImages();
//        System.out.println(products);

        try {
//            ManageProductSet.createProductSet("neat-fin-275018","europe-west1", "Clothes", "Clothes" );
          ManageProductSet.listProductSets("neat-fin-275018", "europe-west1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
