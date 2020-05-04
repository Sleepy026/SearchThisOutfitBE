package webscraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Webscraper {

    String url = "https://www.fashiondays.hu/g/f%C3%A9rfi-/ruh%C3%A1zat";

    Document paginationPage;
    Document page;

    List<Product> products = new ArrayList<>();

    public List<Product> getClothesImages() {
        try {
            paginationPage = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements pagination = paginationPage.select(".pagination > li > a");

        for (Element e:pagination) {
            String url = e.attr("abs:href");
            try {
                page = Jsoup.connect(url).get();
            } catch (IOException a){
                a.printStackTrace();
            }

            String clothesSelector = ".vrecom_product";
            Elements elements = page.select(clothesSelector);

            for (Element element : elements) {
                try {

                    String id = element.child(2).attr("data-gtm-id");
                    String productUrl = element.child(2).attr("href");
                    String productName = element.child(2).attr("title");
                    String classification = element.child(2).attr("data-gtm-classification");
                    String subClassification = element.child(2).attr("data-gtm-subclassification");
                    String referenceImgUrl = element.child(2).child(0).child(1).attr("data-original");

                    products.add(new Product(
                            id,productName,productUrl,referenceImgUrl,classification,subClassification
                    ));
                } catch (Exception exep){
                    System.out.println(exep);
                }
            }
        }


        return products;
    }


}
