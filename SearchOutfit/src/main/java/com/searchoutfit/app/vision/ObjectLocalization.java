package com.searchoutfit.app.vision;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;



@Service
public class ObjectLocalization {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    public AnnotateImageResponse processImage(String URL) {
        Resource imageResource = this.resourceLoader.getResource(URL);
        return this.cloudVisionTemplate.analyzeImage(
                imageResource, Feature.Type.OBJECT_LOCALIZATION, Feature.Type.LABEL_DETECTION, Feature.Type.LOGO_DETECTION);
    }

}
