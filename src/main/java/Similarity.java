import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Similarity {

    private static ArrayList<String> similarStories = new ArrayList<>();

    public void checkSimilarity(ArrayList<String> sky, ArrayList<String> bbc, ArrayList<String> independent) {

        compareSources(sky, bbc);
        compareSources(sky, independent);
        compareSources(independent, bbc);

        for(String story : similarStories){
            System.out.println(story);
        }

        PopulateExcel.insertData(similarStories, "PopularTitles");
    }

    private void compareSources(ArrayList<String> sourceA, ArrayList<String> sourceB) {
        for (int i = 0; i < sourceA.size(); i++) {
            for (int j = 0; j < sourceB.size(); j++) {
                double checkSimilarity = similarity(sourceA.get(i), sourceB.get(j));
                if(checkSimilarity > 0.98){
                    if(!similarStories.contains(sourceA.get(i))){
                        similarStories.add(sourceA.get(i));
                    }
                    if(!similarStories.contains(sourceB.get(j))){
                        similarStories.add(sourceB.get(j));
                    }
                }
            }
        }
    }

    public static double similarity(String sentenceA, String sentenceB) {
        String longerString = sentenceA.length() < sentenceB.length() ? sentenceB : sentenceA;
        String shorterString = sentenceA.length() > sentenceB.length() ? sentenceB : sentenceA;

        boolean lengthOfLongerString = longerString.length() == 0;
        if (lengthOfLongerString) {
            return 1.0;
        }

        return (longerString.length() - StringUtils.getLevenshteinDistance(longerString, shorterString)) / (double) longerString.length();
    }
}
