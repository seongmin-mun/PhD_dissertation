package DataRefine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

//https://codechacha.com/ko/java-parse-json/

public class JsonToCSV_BERTAccuracy {
    public static void DataProcessing (String dirFile) throws IOException {

        BufferedReader Br = new BufferedReader(new FileReader(dirFile));
        String jsonString = Br.readLine();


        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("Accuracy_info");

        for (int g = 1; g < 51; g++){
//            System.out.print(g+",");
        }

        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            String postposition = obj.getString("postposition");
//            System.out.println("postposition(" + i + "): " + postposition);
            System.out.println();


            JSONArray wordnetArray = obj.getJSONArray("accuracy");
            for (int j = 0; j < wordnetArray.length(); j++) {
                JSONObject innerObj = wordnetArray.getJSONObject(j);
                String epoch = innerObj.getString("epoch");
                float total = innerObj.getFloat("total");
//                System.out.print(total+",");
                float loss = innerObj.getFloat("loss");
                if(postposition.equals("ey")){
                    float fns = innerObj.getFloat("fns");
//                    System.out.print(fns+",");
                    float ins = innerObj.getFloat("ins");
//                    System.out.print(ins+",");
                    float gol = innerObj.getFloat("gol");
//                    System.out.print(gol+",");
                    float eff = innerObj.getFloat("eff");
//                    System.out.print(eff+",");
                    float crt = innerObj.getFloat("crt");
//                    System.out.print(crt+",");
                    float loc = innerObj.getFloat("loc");
//                    System.out.print(loc+",");
                    float agt = innerObj.getFloat("agt");
//                    System.out.print(agt+",");
                    float thm = innerObj.getFloat("thm");
//                    System.out.print(thm+",");
                } else if(postposition.equals("eyse")){
                    float src = innerObj.getFloat("src");
//                    System.out.print(src+",");
                    float loc = innerObj.getFloat("loc");
//                    System.out.print(loc+",");
                } else if(postposition.equals("(u)lo")){
                    float fns = innerObj.getFloat("fns");
//                    System.out.print(fns+",");
                    float ins = innerObj.getFloat("ins");
//                    System.out.print(ins+",");
                    float dir = innerObj.getFloat("dir");
//                    System.out.print(dir+",");
                    float eff = innerObj.getFloat("eff");
//                    System.out.print(eff+",");
                    float crt = innerObj.getFloat("crt");
//                    System.out.print(crt+",");
                    float loc = innerObj.getFloat("loc");
                    System.out.print(loc+",");
                }


            }
        }
    };
}
