import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Similarity {

    public void checkSimilarity(ArrayList<String> sky, ArrayList<String> bbc) {
        ArrayList<String> similarStories = new ArrayList<>(); //dynamic
        for (int i = 0; i < sky.size(); i++) {
            for (int j = 0; j < bbc.size(); j++) {
                double checkSimilarity = similarity(sky.get(i), bbc.get(j));
                if(checkSimilarity > 0.98){
                    if(!similarStories.contains(sky.get(i))){
                        similarStories.add(sky.get(i));
                    }
                    if(!similarStories.contains(bbc.get(j))){
                        similarStories.add(bbc.get(j));
                    }
                }
            }
        }

        for(String story : similarStories){
            System.out.println(story);
        }

        PopulateExcel.insertData(similarStories, "PopularTitles");
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
