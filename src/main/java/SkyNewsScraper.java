import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SkyNewsScraper {
    private final static ArrayList<String> headings = new ArrayList<>();

    public static ArrayList<String> run() {
        Document document = getDocument("https://news.sky.com/uk");
        Elements rawHeadings = document.getElementsByClass("sdc-site-tile__headline-text");
        for(Element title : rawHeadings) {
            removeTags(title.toString());
        }
        return headings;
    }

    private static void removeTags(String title) {
        StringBuilder stringBuilder = new StringBuilder(title);
        String startTag = "<span class=\"sdc-site-tile__headline-text\">";
        String endTag = "</span>";

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
