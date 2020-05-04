package webscraper;

public class Product {
    private String productId;
    private String productName;
    private String productUrl;
    private String referenceImageUrl;
    private String classification;
    private String subClassification;

    public Product(String productId, String productName, String productUrl, String referenceImageUrl, String classification, String subClassification) {
        this.productId = productId;
        this.productName = productName;
        this.productUrl = productUrl;
        this.referenceImageUrl = referenceImageUrl;
        this.classification = classification;
        this.subClassification = subClassification;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getReferenceImageUrl() {
        return referenceImageUrl;
    }

    public String getClassification() {
        return classification;
    }

    public String getSubClassification() {
        return subClassification;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", referenceImageUrl='" + referenceImageUrl + '\'' +
                ", classification='" + classification + '\'' +
                ", subClassification='" + subClassification + '\'' +
                '}';
    }
}
