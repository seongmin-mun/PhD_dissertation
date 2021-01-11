package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonBERTsentence {
    public static List sentenceList (String OriginalFile, String testFile, String post) throws IOException {

        BufferedReader OriginalBr = new BufferedReader(new FileReader(OriginalFile));
        String OriginalLine = null;

        HashMap<String, String> OriginalHash = new HashMap<String, String>();

        while ((OriginalLine = OriginalBr.readLine()) != null) {
            String [] eachLine = OriginalLine.split(" ### ");
            OriginalHash.put(eachLine[4].replace(",",""),eachLine[3].replace(",/SP",""));
        }

        BufferedReader testBr = new BufferedReader(new FileReader(testFile));
        String testLine = null;

        List<String> wholeList = new ArrayList<>();

        while ((testLine = testBr.readLine()) != null) {
            String [] eachLine = testLine.split(",");
            if(eachLine[0].equals("Index")){
                continue;
            } else {
                wholeList.add(eachLine[0]+","+eachLine[1]+","+eachLine[2]);
            }
        }

        List<String> sentenceList = new ArrayList<>();

        int num = 0;
        for (String AllInfo : wholeList){
            String [] info = AllInfo.split(",");
            String index = info[0];
            String label = info[1];
            String sentence = info[2];
            String sentence_pos = OriginalHash.get(sentence);
            System.out.println(sentence_pos);
            String function = "";
            if(post.equals("Ey")){
                if(label.equals("0")){
                    function = "fns";
                } else if(label.equals("1")){
                    function = "ins";
                } else if(label.equals("2")){
                    function = "gol";
                } else if(label.equals("3")){
                    function = "eff";
                } else if(label.equals("4")){
                    function = "crt";
                } else if(label.equals("5")){
                    function = "loc";
                } else if(label.equals("6")){
                    function = "agt";
                } else if(label.equals("7")){
                    function = "thm";
                }
            } else if(post.equals("Eyse")){
                if(label.equals("0")){
                    function = "src";
                } else if(label.equals("1")){
                    function = "loc";
                }
            } else if(post.equals("Lo")){
                if(label.equals("0")){
                    function = "fns";
                } else if(label.equals("1")){
                    function = "ins";
                } else if(label.equals("2")){
                    function = "dir";
                } else if(label.equals("3")){
                    function = "eff";
                } else if(label.equals("4")){
                    function = "crt";
                } else if(label.equals("5")){
                    function = "loc";
                }
            }
            String position = "{'index':'index"+index+"','function':'"+function+"','sentence':'"+sentence.replace("'","").replace("\"","")+"','sentence_pos':'"+sentence_pos.replace("'/SS","").replace("\"/SS","")+"'}";
            sentenceList.add(position);
            num += 1;

//            if (num == 29){
//                break;
//            }

        }



        return sentenceList;
    };
}
