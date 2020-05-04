package com.searchoutfit.app.controller;

import com.searchoutfit.app.vision.ManageProductSet;
import com.searchoutfit.app.vision.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webscraper.Product;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin
@RestController
public class ImageController {

    @Autowired
    ManageProductSet manageProductSet;

    @Autowired
    ProductSearch productSearch;

    @RequestMapping(value = "/img-upload", method = RequestMethod.POST)
    public void uploadImage(@RequestParam MultipartFile file) throws Exception {
        manageProductSet.uploadObject("neat-fin-275018",
                                    "uploadimages1337",
                                    "1", file.getBytes());
        productSearch.getSimilarProductsGcs("neat-fin-275018",
                "europe-west1",
                "clothes",
                "apparel-v2",
                "gs://uploadimages1337/1");
    }

}
