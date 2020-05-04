package com.searchoutfit.app.vision;
import com.google.cloud.vision.v1.Product.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraper.Product;
import java.io.IOException;
import java.util.List;

@Service
public class ProductCatalog {

    @Autowired
    ManageProductSet manageProductSet;

    public void createProductCatalog(List<Product> products) {
        try {
            for (Product p : products) {
                if(p.getReferenceImageUrl() != null && p.getProductUrl() != null){
                    KeyValue label = KeyValue.newBuilder()
                            .setKey(p.getClassification())
                            .setValue(p.getSubClassification())
                            .build();

                    manageProductSet.uploadObject("neat-fin-275018",
                            "productsearch1337",
                             p.getProductName(),
                             p.getReferenceImageUrl());

                    manageProductSet.createProduct("neat-fin-275018",
                            "europe-west1",
                            p.getProductId(),
                            p.getProductName(),
                            p.getProductUrl(),
                            label,
                            "apparel-v2");

                    manageProductSet.createReferenceImage("neat-fin-275018",
                            "europe-west1",
                            p.getProductId(),
                            "img" + p.getProductId(),
                            "gs://productsearch1337/"+ p.getProductName());

                    manageProductSet.addProductToProductSet(
                            "neat-fin-275018",
                            "europe-west1",
                            p.getProductId(),
                            "clothes");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
