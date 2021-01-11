package Processing;

import Dictionary.DataForJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyWordList {
    public static void WordList (String dirFile) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        StringBuilder builder = new StringBuilder();
        String tagLine = null;

        HashMap<String, Integer> WordHash = new HashMap<String, Integer>();

        while ((tagLine = tagBr.readLine()) != null) {
            int Label;
            String [] eachSentence = tagLine.split(" ### ");
            String [] eachTokens = eachSentence[3].trim().split((" "));
            for (String eachToken : eachTokens){
                //System.out.println(eachToken);
                for (String pos : DataForJson.POS_tag){
                    if(eachToken.contains(pos)){
                        if(WordHash.containsKey(eachToken)) {
                            WordHash.put(eachToken,WordHash.get(eachToken)+1);
                        } else {
                            WordHash.put(eachToken,1);
                        }
                    }
                }
            }
        }

        List<Module.SortingInt> Rank_result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : WordHash.entrySet()) {
            Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
            Rank_result.add(result);
        }

        //정렬하는 부분
        for (int i = 0; i < Rank_result.size(); i++) {
            for (int j = i + 1; j < Rank_result.size(); j++) {
                Module.SortingInt source = Rank_result.get(i), dest = Rank_result.get(j);
                if (source.count < dest.count) {
                    Rank_result.set(i, dest);
                    Rank_result.set(j, source);
                }
            }
        }

        int num = 0;
        for (int i = 0; i < Rank_result.size(); i++) {
            num++;
            System.out.println(num + "," + Rank_result.get(i));
        }
    }
}
