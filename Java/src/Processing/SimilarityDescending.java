package Processing;

import Dictionary.DataForJson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimilarityDescending {

    public static void DescendingOrder (String dirFile, String method, String post, String func, int window) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        String tagLine = null;

        HashMap<String, Float> WordHash = new HashMap<String, Float>();

        while ((tagLine = tagBr.readLine()) != null) {
            String [] eachLine = tagLine.split(",");
            boolean ischecked = false;
            for (String pos: DataForJson.POS_tag){
                if(eachLine[0].contains(pos)){
                    ischecked = true;
                }
            }
            if (ischecked == true){
                WordHash.put(eachLine[0],Float.parseFloat(eachLine[1]));
            }
        }

        List<Module.SortingFloat> Rank_result = new ArrayList<>();
        for (Map.Entry<String, Float> entry : WordHash.entrySet()) {
            Module.SortingFloat result = new Module.SortingFloat(entry.getKey(),entry.getValue());
            Rank_result.add(result);
        }

        //정렬하는 부분
        for (int i = 0; i < Rank_result.size(); i++) {
            for (int j = i + 1; j < Rank_result.size(); j++) {
                Module.SortingFloat source = Rank_result.get(i), dest = Rank_result.get(j);
                if (source.count < dest.count) {
                    Rank_result.set(i, dest);
                    Rank_result.set(j, source);
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        int num = 0;
        for (int i = 0; i < Rank_result.size(); i++) {
            num++;
            System.out.println(num + "," + Rank_result.get(i));
            builder.append(num + "," + Rank_result.get(i)+"\n");
        }

        String OutputFile = "./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(window)+".csv";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
        bw.write(builder.toString());
        bw.flush();
        bw.close();

    }
}
