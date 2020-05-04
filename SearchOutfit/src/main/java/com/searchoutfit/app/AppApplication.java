package com.searchoutfit.app;

import com.searchoutfit.app.vision.ManageProductSet;
import com.searchoutfit.app.vision.ProductCatalog;
import com.searchoutfit.app.vision.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webscraper.Product;
import webscraper.Webscraper;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@SpringBootApplication
public class AppApplication {

    @Autowired
    ProductCatalog productCatalog;

    @Autowired
    ManageProductSet manageProductSet;

    @Autowired
    ProductSearch productSearch;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {

        productSearch.getSimilarProductsGcs(
                "neat-fin-275018",
                "europe-west1",
                "clothes",
                "apparel-v2",
                "gs://productsearch1337/Mintás póló logóval");
    }


}
