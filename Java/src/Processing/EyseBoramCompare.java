package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongmin_M on 2019. 12. 11..
 */
public class EyseBoramCompare {
    public static void BoramCompare (String brFile, String kappaFile) throws IOException {

        List<String> brList = new ArrayList<>();

        BufferedReader brBr = new BufferedReader(new FileReader(brFile));
        String brLine = null;

        int num = 0;
        while ((brLine = brBr.readLine()) != null) {
            String [] intext = brLine.split(" #### ");

            String errorCheck = intext[0].substring(0,2);
            if(errorCheck.equals("##")){
                //System.out.println(intext[0]);
                //num++;
                continue;
            } else {
                System.out.println(intext[2]+" ### "+intext[3]);
                brList.add(intext[0]);
                num++;
            }

        }

        System.out.println(num);

        BufferedReader kappaBr = new BufferedReader(new FileReader(kappaFile));
        String kappaLine = null;

        List<String> kappaList = new ArrayList<>();

        while ((kappaLine = kappaBr.readLine()) != null) {
            //kappaLine
            String labelCheck = kappaLine.substring(0,3);
            kappaList.add(labelCheck);
        }

//        for(int i = 0; i < kappaList.size(); i++){
//            if(kappaList.get(i).equals(brList.get(i))){
//                System.out.println((i+1)+" : "+kappaList.get(i)+" : "+brList.get(i));
//                continue;
//            } else {
//                System.out.println((i+1)+" : "+kappaList.get(i)+" : "+brList.get(i));
//            }
//        }
    }
}
