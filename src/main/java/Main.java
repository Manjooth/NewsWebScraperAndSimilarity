import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Similarity similarity = new Similarity();

        ArrayList<String> sky = SkyNewsScraper.run();
        ArrayList<String> bbc = BBCNewsScraper.run();
        PopulateExcel.insertData(sky, bbc, "NewsTitles");
        similarity.checkSimilarity(sky, bbc);
    }
}
