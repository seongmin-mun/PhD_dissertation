package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seongmin_M on 2019. 12. 11..
 */
public class GHwithKappa {
    public static void ghCompareKappa (String ghFile, String kappaFile) throws IOException {
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
    }
}
