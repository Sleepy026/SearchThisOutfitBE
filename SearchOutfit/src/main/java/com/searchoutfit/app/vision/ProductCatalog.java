package com.searchoutfit.app.vision;
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
                com.google.cloud.vision.v1.Product.KeyValue label = com.google.cloud.vision.v1.Product.KeyValue.newBuilder().setKey(p.getClassification()).setValue(p.getSubClassification()).build();
                manageProductSet.uploadObject("neat-fin-275018", "productsearch1337", p.getProductName(), p.getReferenceImageUrl());
                manageProductSet.createProduct("neat-fin-275018",
                        "europe-west1",
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductUrl(),
                        label,
                        "apparel");
                manageProductSet.createReferenceImage("neat-fin-275018",
                        "europe-west1",
                        p.getProductId(),
                        "img" + p.getProductId(),
                        "gs://productsearch1337/"+ p.getProductName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
