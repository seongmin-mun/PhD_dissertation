package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seongmin_M on 2019. 12. 11..
 */
public class GSwithOriginal {
    public static void ghCompare (String ghFile, String originFile) throws IOException {

        HashMap<String, String> ghMap = new HashMap<>();
        List<String> ghList = new ArrayList<>();

        BufferedReader ghBr = new BufferedReader(new FileReader(ghFile));
        String ghLine = null;

        while ((ghLine = ghBr.readLine()) != null) {
            String [] eachPart = ghLine.split(" #### ");
            ghMap.put(eachPart[2],eachPart[2]+" ### "+eachPart[3]);
            ghList.add(eachPart[2]+" ### "+eachPart[3]);
            //System.out.println(tagLine);
        }



        List<String> originList = new ArrayList<>();

        BufferedReader originBr = new BufferedReader(new FileReader(originFile));
        String originLine = null;

        while ((originLine = originBr.readLine()) != null) {
            String [] eachSentence = originLine.split(" #### ");
            if (ghMap.containsKey(eachSentence[1])) {   //호를 가지고 있을 경우
                continue;
            } else {
                originList.add(eachSentence[1]+" ### "+eachSentence[2]);
            }
        }

        for(String other : originList){
            System.out.println(other);
        }

//        for(String errorSentence : ghList){
//            System.out.println(errorSentence);
//        }

//        for (Map.Entry<String, String> entry : ghMap.entrySet()) {
//            System.out.println(entry.getValue());
//        }

//        List<String> finalList = new ArrayList<>();
//        for(int i = 0; i < tagList.size(); i++){
//            String outPrint = tagList.get(i)+","+sentenceList.get(i);
//            finalList.add(outPrint);
//            System.out.println(outPrint);
//        }
    };
}
