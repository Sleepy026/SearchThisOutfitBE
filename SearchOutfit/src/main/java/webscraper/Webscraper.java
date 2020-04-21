package webscraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Webscraper {

    String Url = "https://www.aboutyou.hu/ferfi/ruhazat/polok/polok?styleGroup=4";

    Document page;

    public List<String> getClothesImages(){
        try {
            page = Jsoup.connect(Url).userAgent("Jsoup Scraper").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String clothesSelector = ".jgRgsg";
        Elements elements = page.select(clothesSelector );
        List<String> elems = new ArrayList<>();
        for (Element element : elements) {
            elems.add(element.child(0).absUrl("src"));
        }
        return elems;
    }


}
