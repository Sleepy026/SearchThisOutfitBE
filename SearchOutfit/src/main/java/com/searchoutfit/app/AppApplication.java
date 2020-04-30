package com.searchoutfit.app;

import com.searchoutfit.app.vision.ProductCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webscraper.Product;
import webscraper.Webscraper;
import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication
public class AppApplication {

    @Autowired
    ProductCatalog productCatalog;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @PostConstruct
    public void init(){
        Webscraper webscraper = new Webscraper();
//        List<Product> products = webscraper.getClothesImages();
//        productCatalog.createProductCatalog(products);
    }


}
