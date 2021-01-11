package Processing;

import Dictionary.DataForJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static translator.Romanization.yale_romanization;

public class JsonDSM {
    public static List Wordnet (String tSNEFile, String WordFile, String method, String post, String window) throws IOException {
        BufferedReader tSNEBr = new BufferedReader(new FileReader(tSNEFile));
        String tSNELine = null;

        HashMap<String, String> tSNEHash = new HashMap<String, String>();

        while ((tSNELine = tSNEBr.readLine()) != null) {
            String [] eachLine = tSNELine.split(",");
            if(eachLine[0].equals("Id")){
                continue;
            } else {
                String point = eachLine[1]+","+eachLine[2];
                tSNEHash.put(eachLine[3],point);
            }
        }

        BufferedReader WordBr = new BufferedReader(new FileReader(WordFile));
        String WordLine = null;

        HashMap<String, String> WordHash = new HashMap<String, String>();

        while ((WordLine = WordBr.readLine()) != null) {
            String [] eachLine = WordLine.split(",");
            if(tSNEHash.containsKey(eachLine[1])) {
                String info = tSNEHash.get(eachLine[1])+","+eachLine[2];
                WordHash.put(eachLine[1],info);
            }
        }

        int num = 0;

        List<String> FinalList = new ArrayList<>();

        for (Map.Entry<String, String> entry : WordHash.entrySet()) {
            String [] wordInfo = entry.getKey().split("/");    //form, pos
            String [] eachInfo = entry.getValue().split(",");  //X, Y, size

            if (wordInfo[1].contains("JKB")){
                String name_kr = wordInfo[0];
                String name_eng = yale_romanization(wordInfo[0]);
                String pos = wordInfo[1].split("_")[0];
                String pos_long = wordInfo[1];
                String pos_kr = "";
                String pos_eng = "";
                for (int i = 0; i < DataForJson.POS_tag.length; i++){
                    if(pos.equals(DataForJson.POS_tag[i])){
                        pos_kr = DataForJson.POS_kr[i];
                        pos_eng = DataForJson.POS_eng[i];
                    }
                }
                Float x = Float.parseFloat(eachInfo[0]);
                Float y = Float.parseFloat(eachInfo[1]);
                int frequency = Integer.parseInt(eachInfo[2]);

                if (num == 298){
                    FinalList.add("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos_long':'"+pos_long+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'}");
                    //System.out.println("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'}");
                } else {
                    FinalList.add("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos_long':'"+pos_long+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                    //System.out.println("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                }

                //System.out.println(num+"  {'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                num += 1;
            } else {
                String name_kr = wordInfo[0];
                String name_eng = yale_romanization(wordInfo[0]);
                String pos = wordInfo[1];
                String pos_kr = "";
                String pos_eng = "";
                for (int i = 0; i < DataForJson.POS_tag.length; i++){
                    if(pos.equals(DataForJson.POS_tag[i])){
                        pos_kr = DataForJson.POS_kr[i];
                        pos_eng = DataForJson.POS_eng[i];
                    }
                }
                Float x = Float.parseFloat(eachInfo[0]);
                Float y = Float.parseFloat(eachInfo[1]);
                int frequency = Integer.parseInt(eachInfo[2]);

                if (num == 298){
                    FinalList.add("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos_long':'null','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'}");
                    //System.out.println("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'}");
                } else {
                    FinalList.add("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos_long':'null','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                    //System.out.println("{'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                }

                //System.out.println(num+"  {'x':"+x+",'y':"+y+",'frequency':"+frequency+",'name_kr':'"+name_kr+"','name_eng':'"+name_eng+"','pos':'"+pos+"','pos_kr':'"+pos_kr+"','pos_eng':'"+pos_eng+"'},");
                num += 1;
            }

        }

        return FinalList;


    }
}
