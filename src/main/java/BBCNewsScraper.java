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
            removeTags(title.toString());
        }
        return headings;
    }

    private static void removeTags(String title) {
        StringBuilder stringBuilder = new StringBuilder(title);
        String startTag = "<h3 class=\"gs-c-promo-heading__title gel-pica-bold nw-o-link-split__text\">";
        String endTag = "</h3>";

        stringBuilder.replace(0, startTag.length(), "");
        stringBuilder.replace(stringBuilder.length() - endTag.length(), stringBuilder.length(), "");

        if(!headings.contains(stringBuilder.toString())){
            headings.add(stringBuilder.toString());
        }
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
