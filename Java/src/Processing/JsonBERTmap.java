package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static translator.Romanization.yale_romanization;

public class JsonBERTmap {
    public static List tSNEList (String tSNEFile, String post, String epoch) throws IOException {


        BufferedReader tSNEBr = new BufferedReader(new FileReader(tSNEFile));
        String tSNELine = null;

        List<String> wholeList = new ArrayList<>();

        while ((tSNELine = tSNEBr.readLine()) != null) {
            String [] eachLine = tSNELine.split(",");
            if(eachLine[1].equals("X")){
                continue;
            } else {
                wholeList.add(eachLine[0]+","+eachLine[1]+","+eachLine[2]);
            }
        }

        List<String> tSNEList = new ArrayList<>();

        int num = 0;
        for (String AllInfo : wholeList){
            String [] info = AllInfo.split(",");
            String index = info[0];
            String Xaxis = info[1];
            String Yaxis = info[2];
            String position = "{'index':'index"+index+"','X':"+Xaxis+",'Y':"+Yaxis+"}";
            tSNEList.add(position);
            num += 1;

//            if (num == 29){
//                break;
//            }

        }



        return tSNEList;
    };
}
