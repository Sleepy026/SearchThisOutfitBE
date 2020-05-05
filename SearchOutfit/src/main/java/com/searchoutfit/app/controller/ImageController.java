package com.searchoutfit.app.controller;

import com.google.cloud.vision.v1.ProductSearchResults.Result;
import com.searchoutfit.app.vision.ManageProductSet;
import com.searchoutfit.app.vision.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class ImageController {

    @Autowired
    ManageProductSet manageProductSet;

    @Autowired
    ProductSearch productSearch;

    @RequestMapping(value = "/img-upload", method = RequestMethod.POST)
    public List<Result> uploadImage(@RequestParam MultipartFile file) throws Exception {
        manageProductSet.uploadObject("neat-fin-275018",
                                    "uploadimages1337",
                                    "1", file.getBytes());
        return productSearch.getSimilarProductsGcs("neat-fin-275018",
                "europe-west1",
                "clothes",
                "apparel-v2",
                "gs://uploadimages1337/1");
    }

}
