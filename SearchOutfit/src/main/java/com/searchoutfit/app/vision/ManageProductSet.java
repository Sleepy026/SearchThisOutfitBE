package com.searchoutfit.app.vision;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.ImportProductSetsGcsSource.Builder;

import java.io.IOException;

public class ManageProductSet {

    public static void createProductSet(
            String projectId, String computeRegion, String productSetId, String productSetDisplayName)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);

            // Create a product set with the product set specification in the region.
            ProductSet myProductSet =
                    ProductSet.newBuilder().setDisplayName(productSetDisplayName).build();
            CreateProductSetRequest request =
                    CreateProductSetRequest.newBuilder()
                            .setParent(formattedParent)
                            .setProductSet(myProductSet)
                            .setProductSetId(productSetId)
                            .build();
            ProductSet productSet = client.createProductSet(request);
            // Display the product set information
            System.out.println(String.format("Product set name: %s", productSet.getName()));
        }
    }

    public static void importProductSets(String projectId, String computeRegion, String gcsUri)
            throws Exception {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);
            Builder gcsSource = ImportProductSetsGcsSource.newBuilder().setCsvFileUri(gcsUri);

            // Set the input configuration along with Google Cloud Storage URI
            ImportProductSetsInputConfig inputConfig =
                    ImportProductSetsInputConfig.newBuilder().setGcsSource(gcsSource).build();

            // Import the product sets from the input URI.
            OperationFuture<ImportProductSetsResponse, BatchOperationMetadata> response =
                    client.importProductSetsAsync(formattedParent, inputConfig);

            System.out.println(String.format("Processing operation name: %s", response.getName()));
            ImportProductSetsResponse results = response.get();
            System.out.println("Processing done.");
            System.out.println("Results of the processing:");

            for (int i = 0; i < results.getStatusesCount(); i++) {
                System.out.println(
                        String.format(
                                "Status of processing line %s of the csv: %s", i, results.getStatuses(i)));
                // Check the status of reference image.
                if (results.getStatuses(i).getCode() == 0) {
                    ReferenceImage referenceImage = results.getReferenceImages(i);
                    System.out.println(referenceImage);
                } else {
                    System.out.println("No reference image.");
                }
            }
        }
    }

    public static void createProduct(
            String projectId,
            String computeRegion,
            String productId,
            String productDisplayName,
            String productCategory)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);
            // Create a product with the product specification in the region.
            // Multiple labels are also supported.
            Product myProduct =
                    Product.newBuilder()
                            .setName(productId)
                            .setDisplayName(productDisplayName)
                            .setProductCategory(productCategory)
                            .build();
            Product product = client.createProduct(formattedParent, myProduct, productId);
            // Display the product information
            System.out.println(String.format("Product name: %s", product.getName()));
        }
    }

    public static void addProductToProductSet(
            String projectId, String computeRegion, String productId, String productSetId)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product set.
            String formattedName =
                    ProductSearchClient.formatProductSetName(projectId, computeRegion, productSetId);

            // Get the full path of the product.
            String productPath = ProductName.of(projectId, computeRegion, productId).toString();

            // Add the product to the product set.
            client.addProductToProductSet(formattedName, productPath);

            System.out.println(String.format("Product added to product set."));
        }
    }

    public static void removeProductFromProductSet(
            String projectId, String computeRegion, String productId, String productSetId)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product set.
            String formattedParent =
                    ProductSearchClient.formatProductSetName(projectId, computeRegion, productSetId);

            // Get the full path of the product.
            String formattedName =
                    ProductSearchClient.formatProductName(projectId, computeRegion, productId);

            // Remove the product from the product set.
            client.removeProductFromProductSet(formattedParent, formattedName);

            System.out.println(String.format("Product removed from product set."));
        }
    }


    public static void createReferenceImage(
            String projectId,
            String computeRegion,
            String productId,
            String referenceImageId,
            String gcsUri)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product.
            String formattedParent =
                    ProductSearchClient.formatProductName(projectId, computeRegion, productId);
            // Create a reference image.
            ReferenceImage referenceImage = ReferenceImage.newBuilder().setUri(gcsUri).build();

            ReferenceImage image =
                    client.createReferenceImage(formattedParent, referenceImage, referenceImageId);
            // Display the reference image information.
            System.out.println(String.format("Reference image name: %s", image.getName()));
            System.out.println(String.format("Reference image uri: %s", image.getUri()));
        }
    }

    public static void listProductSets(String projectId, String computeRegion) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {
            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);
            // List all the product sets available in the region.
            for (ProductSet productSet : client.listProductSets(formattedParent).iterateAll()) {
                // Display the product set information
                System.out.println(String.format("Product set name: %s", productSet.getName()));
                System.out.println(
                        String.format(
                                "Product set id: %s",
                                productSet.getName().substring(productSet.getName().lastIndexOf('/') + 1)));
                System.out.println(
                        String.format("Product set display name: %s", productSet.getDisplayName()));
                System.out.println("Product set index time:");
                System.out.println(String.format("\tseconds: %s", productSet.getIndexTime().getSeconds()));
                System.out.println(String.format("\tnanos: %s", productSet.getIndexTime().getNanos()));
            }
        }
    }

    public static void getProductSet(String projectId, String computeRegion, String productSetId)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product set.
            String formattedName =
                    ProductSearchClient.formatProductSetName(projectId, computeRegion, productSetId);
            // Get complete detail of the product set.
            ProductSet productSet = client.getProductSet(formattedName);
            // Display the product set information
            System.out.println(String.format("Product set name: %s", productSet.getName()));
            System.out.println(
                    String.format(
                            "Product set id: %s",
                            productSet.getName().substring(productSet.getName().lastIndexOf('/') + 1)));
            System.out.println(
                    String.format("Product set display name: %s", productSet.getDisplayName()));
            System.out.println("Product set index time:");
            System.out.println(String.format("\tseconds: %s", productSet.getIndexTime().getSeconds()));
            System.out.println(String.format("\tnanos: %s", productSet.getIndexTime().getNanos()));
        }
    }

    public static void listProducts(String projectId, String computeRegion) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);

            // List all the products available in the region.
            for (Product product : client.listProducts(formattedParent).iterateAll()) {
                // Display the product information
                System.out.println(String.format("\nProduct name: %s", product.getName()));
                System.out.println(
                        String.format(
                                "Product id: %s",
                                product.getName().substring(product.getName().lastIndexOf('/') + 1)));
                System.out.println(String.format("Product display name: %s", product.getDisplayName()));
                System.out.println(String.format("Product category: %s", product.getProductCategory()));
                System.out.println("Product labels:");
                System.out.println(
                        String.format("Product labels: %s", product.getProductLabelsList().toString()));
            }
        }
    }

    public static void listProductsInProductSet(
            String projectId, String computeRegion, String productSetId) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product set.
            String formattedName =
                    ProductSearchClient.formatProductSetName(projectId, computeRegion, productSetId);
            // List all the products available in the product set.
            for (Product product : client.listProductsInProductSet(formattedName).iterateAll()) {
                // Display the product information
                System.out.println(String.format("Product name: %s", product.getName()));
                System.out.println(
                        String.format(
                                "Product id: %s",
                                product.getName().substring(product.getName().lastIndexOf('/') + 1)));
                System.out.println(String.format("Product display name: %s", product.getDisplayName()));
                System.out.println(String.format("Product description: %s", product.getDescription()));
                System.out.println(String.format("Product category: %s", product.getProductCategory()));
                System.out.println("Product labels: ");
                for (Product.KeyValue element : product.getProductLabelsList()) {
                    System.out.println(String.format("%s: %s", element.getKey(), element.getValue()));
                }
            }
        }
    }

    public static void getProduct(String projectId, String computeRegion, String productId)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product.
            String formattedName =
                    ProductSearchClient.formatProductName(projectId, computeRegion, productId);
            // Get complete detail of the product.
            Product product = client.getProduct(formattedName);
            // Display the product information
            System.out.println(String.format("Product name: %s", product.getName()));
            System.out.println(
                    String.format(
                            "Product id: %s",
                            product.getName().substring(product.getName().lastIndexOf('/') + 1)));
            System.out.println(String.format("Product display name: %s", product.getDisplayName()));
            System.out.println(String.format("Product description: %s", product.getDescription()));
            System.out.println(String.format("Product category: %s", product.getProductCategory()));
            System.out.println(String.format("Product labels: "));
            for (Product.KeyValue element : product.getProductLabelsList()) {
                System.out.println(String.format("%s: %s", element.getKey(), element.getValue()));
            }
        }
    }

    public static void listReferenceImagesOfProduct(
            String projectId, String computeRegion, String productId) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product.
            String formattedParent =
                    ProductSearchClient.formatProductName(projectId, computeRegion, productId);
            for (ReferenceImage image : client.listReferenceImages(formattedParent).iterateAll()) {
                // Display the reference image information.
                System.out.println(String.format("Reference image name: %s", image.getName()));
                System.out.println(
                        String.format(
                                "Reference image id: %s",
                                image.getName().substring(image.getName().lastIndexOf('/') + 1)));
                System.out.println(String.format("Reference image uri: %s", image.getUri()));
                System.out.println(
                        String.format(
                                "Reference image bounding polygons: %s \n",
                                image.getBoundingPolysList().toString()));
            }
        }
    }

    public static void getReferenceImage(
            String projectId, String computeRegion, String productId, String referenceImageId)
            throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the reference image.
            String formattedName =
                    ImageName.format(projectId, computeRegion, productId, referenceImageId);
            // Get complete detail of the reference image.
            ReferenceImage image = client.getReferenceImage(formattedName);
            // Display the reference image information.
            System.out.println(String.format("Reference image name: %s", image.getName()));
            System.out.println(
                    String.format(
                            "Reference image id: %s",
                            image.getName().substring(image.getName().lastIndexOf('/') + 1)));
            System.out.println(String.format("Reference image uri: %s", image.getUri()));
            System.out.println(
                    String.format(
                            "Reference image bounding polygons: %s \n", image.getBoundingPolysList().toString()));
        }
    }

}
