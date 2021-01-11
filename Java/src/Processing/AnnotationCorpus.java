package Processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongmin_M on 2019. 12. 11..
 */
public class AnnotationCorpus {
    public static void test (String dirFile) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        String tagLine = null;

        List<String> tagList = new ArrayList<>();
        int num = 0;
        while ((tagLine = tagBr.readLine()) != null) {

            String [] eachSentence = tagLine.split(" ### ");
            if(eachSentence[0].equals("DIR") && eachSentence[1].equals("DIR") && eachSentence[2].equals("DIR")){
                if(num < 30) {
                    System.out.print(eachSentence[3].replaceAll("\"/SS","").replace(",/SP","").replace("'/SP","")+" ");
                }
                num++;
            }
        }
    };

    //   "/SS ,/SS   '/SS
    public static List generatod (String tagFile, String sentenceFile) throws IOException {

        List<String> tagList = new ArrayList<>();

        BufferedReader tagBr = new BufferedReader(new FileReader(tagFile));
        String tagLine = null;

        while ((tagLine = tagBr.readLine()) != null) {
            tagList.add(tagLine);
            //System.out.println(tagLine);
        }

        List<String> sentenceList = new ArrayList<>();

        BufferedReader sentenceBr = new BufferedReader(new FileReader(sentenceFile));
        String sentenceLine = null;

        while ((sentenceLine = sentenceBr.readLine()) != null) {
            String [] eachSentence = sentenceLine.split(" ### ");
            sentenceList.add(eachSentence[0]+" ### "+eachSentence[1]);
            //System.out.println(sentenceLine);
        }

        List<String> finalList = new ArrayList<>();
        for(int i = 0; i < tagList.size(); i++){
            String outPrint = tagList.get(i).replace(","," ### ")+" ### "+sentenceList.get(i);
            finalList.add(outPrint);
            System.out.println(outPrint);
        }

        return finalList;
    };
}
