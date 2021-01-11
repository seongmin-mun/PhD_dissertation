package DataRefine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

//https://codechacha.com/ko/java-parse-json/

public class JsonToCSV {
    public static void DataProcessing (String dirFile) throws IOException {

        BufferedReader Br = new BufferedReader(new FileReader(dirFile));
        String jsonString = Br.readLine();


        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("DSMs_info");

        // 배열의 모든 아이템을 출력합니다.
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            String method = obj.getString("method");
            String postposition = obj.getString("postposition");
            String window = obj.getString("window");

            System.out.println("method(" + i + "): " + method);
            System.out.println("postposition(" + i + "): " + postposition);
            System.out.println("window(" + i + "): " + window);

            StringBuilder builder = new StringBuilder();

            builder.append("Index,x,y,frequency,name_kr,name_eng,pos,pos_kr,pos_eng\n");

            JSONArray wordnetArray = obj.getJSONArray("wordnet");
            for (int j = 0; j < wordnetArray.length(); j++) {
                JSONObject innerObj = wordnetArray.getJSONObject(j);
                float x = innerObj.getFloat("x");
                float y = innerObj.getFloat("y");
                int frequency = innerObj.getInt("frequency");
                String name_kr = innerObj.getString("name_kr");
                String name_eng = innerObj.getString("name_eng");
                String pos = innerObj.getString("pos");
                String pos_kr = innerObj.getString("pos_kr");
                String pos_eng = innerObj.getString("pos_eng");

                builder.append(j+","+x+","+y+","+frequency+","+name_kr+","+name_eng+","+pos+","+pos_kr+","+pos_eng+"\n");
            }

            String OutputFile = "./Data/CSVfiles/WordLevel/"+method+"_"+postposition+"_"+window+".csv";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
            bw.write(builder.toString());
            bw.flush();
            bw.close();
            //'x','y','frequency','name_kr','name_eng','pos','pos_kr','pos_eng'
            System.out.println();
        }
    };
}
