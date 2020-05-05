package com.searchoutfit.app.vision;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageContext;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.ProductSearchClient;
import com.google.cloud.vision.v1.ProductSearchParams;
import com.google.cloud.vision.v1.ProductSearchResults.Result;
import com.google.cloud.vision.v1.ProductSetName;
import com.google.protobuf.ByteString;
import com.searchoutfit.app.vision.model.ProductModel;
import org.springframework.stereotype.Service;
import webscraper.Product;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductSearch {

    public List<ProductModel> getSimilarProductsGcs(
            String projectId,
            String computeRegion,
            String productSetId,
            String productCategory,
            String gcsUri)
            throws Exception {
        try (ImageAnnotatorClient queryImageClient = ImageAnnotatorClient.create()) {

            // Get the full path of the product set.
            String productSetPath = ProductSetName.of(projectId, computeRegion, productSetId).toString();

            // Get the image from Google Cloud Storage
            ImageSource source = ImageSource.newBuilder().setGcsImageUri(gcsUri).build();

            // Create annotate image request along with product search feature.
            Feature featuresElement = Feature.newBuilder().setType(Type.PRODUCT_SEARCH).build();
            Image image = Image.newBuilder().setSource(source).build();
            ImageContext imageContext =
                    ImageContext.newBuilder()
                            .setProductSearchParams(
                                    ProductSearchParams.newBuilder()
                                            .setProductSet(productSetPath)
                                            .addProductCategories(productCategory)
                                            //.setFilter(filter)
                            )
                            .build();

            AnnotateImageRequest annotateImageRequest =
                    AnnotateImageRequest.newBuilder()
                            .addFeatures(featuresElement)
                            .setImage(image)
                            .setImageContext(imageContext)
                            .build();
            List<AnnotateImageRequest> requests = Arrays.asList(annotateImageRequest);

            // Search products similar to the image.
            BatchAnnotateImagesResponse response = queryImageClient.batchAnnotateImages(requests);

            List<Result> similarProducts = response.getResponses(0).getProductSearchResults().getResultsList();
            List<ProductModel> models = new ArrayList<>();
            for (Result product : similarProducts) {
                ProductModel model = ProductModel.builder()
                        .name(product.getProduct().getDisplayName())
                        .score(product.getScore())
                        .url(product.getProduct().getDescription())
                        .imgUrl(product.getProduct().getDisplayName())
                        .build();
                models.add(model);
            }
            return models;
        }
    }
}
