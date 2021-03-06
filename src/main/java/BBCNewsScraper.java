import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BBCNewsScraper {
    private final static ArrayList<String> headings = new ArrayList<>();

    public static ArrayList<String> run() {
        Document document = getDocument("https://www.bbc.co.uk/news");
        Elements rawHeadings = document.getElementsByClass("gs-c-promo-heading__title gel-pica-bold nw-o-link-split__text");
        for(Element title : rawHeadings) {
            if(!headings.contains(title.text())){
                headings.add(title.text());
            }
        }
        return headings;
    }

    private static Document getDocument(String url) {
        Connection connection = Jsoup.connect(url);
//        connection.userAgent("custom user agent");
        Document document = null;
        try {
            document = connection.get();
        }catch(IOException exception) {
            System.out.println("An error has occurred " + exception);
            exception.printStackTrace();
            // handle error
        }
        return document;
    }

}
