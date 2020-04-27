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

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AppApplication {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @PostConstruct
    public void processImage() {
        Resource imageResource = this.resourceLoader.getResource("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/gettyimages-960033612-1585764394.jpg");
        AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(
                imageResource, Feature.Type.OBJECT_LOCALIZATION);
        System.out.println("Image Classification results: " + response.getLocalizedObjectAnnotationsList().toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
