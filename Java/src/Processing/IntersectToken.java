package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectToken {
    public static void AnalysisEyToken () throws IOException {

        HashMap<String, Integer> Total_count = new HashMap<String, Integer>();

        //Ey_AGT
        BufferedReader Ey_AGTFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_AGT_contentOnly.csv"));
        String Ey_AGTLine = null;

        HashMap<String, Integer> Ey_AGTcount = new HashMap<String, Integer>();

        while ((Ey_AGTLine = Ey_AGTFile.readLine()) != null) {
            if(Ey_AGTLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_AGTLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_AGTcount.containsKey(input)) {
                        Ey_AGTcount.put(input,Ey_AGTcount.get(input)+1);
                    } else {
                        Ey_AGTcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_CRT
        BufferedReader Ey_CRTFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_CRT_contentOnly.csv"));
        String Ey_CRTLine = null;

        HashMap<String, Integer> Ey_CRTcount = new HashMap<String, Integer>();

        while ((Ey_CRTLine = Ey_CRTFile.readLine()) != null) {
            if(Ey_CRTLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_CRTLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_CRTcount.containsKey(input)) {
                        Ey_CRTcount.put(input,Ey_CRTcount.get(input)+1);
                    } else {
                        Ey_CRTcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_EFF
        BufferedReader Ey_EFFFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_EFF_contentOnly.csv"));
        String Ey_EFFLine = null;

        HashMap<String, Integer> Ey_EFFcount = new HashMap<String, Integer>();

        while ((Ey_EFFLine = Ey_EFFFile.readLine()) != null) {
            if(Ey_EFFLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_EFFLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_EFFcount.containsKey(input)) {
                        Ey_EFFcount.put(input,Ey_EFFcount.get(input)+1);
                    } else {
                        Ey_EFFcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_FNS
        BufferedReader Ey_FNSFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_FNS_contentOnly.csv"));
        String Ey_FNSLine = null;

        HashMap<String, Integer> Ey_FNScount = new HashMap<String, Integer>();

        while ((Ey_FNSLine = Ey_FNSFile.readLine()) != null) {
            if(Ey_FNSLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_FNSLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_FNScount.containsKey(input)) {
                        Ey_FNScount.put(input,Ey_FNScount.get(input)+1);
                    } else {
                        Ey_FNScount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_GOL
        BufferedReader Ey_GOLFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_GOL_contentOnly.csv"));
        String Ey_GOLLine = null;

        HashMap<String, Integer> Ey_GOLcount = new HashMap<String, Integer>();

        while ((Ey_GOLLine = Ey_GOLFile.readLine()) != null) {
            if(Ey_GOLLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_GOLLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_GOLcount.containsKey(input)) {
                        Ey_GOLcount.put(input,Ey_GOLcount.get(input)+1);
                    } else {
                        Ey_GOLcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_INS
        BufferedReader Ey_INSFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_INS_contentOnly.csv"));
        String Ey_INSLine = null;

        HashMap<String, Integer> Ey_INScount = new HashMap<String, Integer>();

        while ((Ey_INSLine = Ey_INSFile.readLine()) != null) {
            if(Ey_INSLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_INSLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_INScount.containsKey(input)) {
                        Ey_INScount.put(input,Ey_INScount.get(input)+1);
                    } else {
                        Ey_INScount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Ey_LOC
        BufferedReader Ey_LOCFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_LOC_contentOnly.csv"));
        String Ey_LOCLine = null;

        HashMap<String, Integer> Ey_LOCcount = new HashMap<String, Integer>();

        while ((Ey_LOCLine = Ey_LOCFile.readLine()) != null) {
            if(Ey_LOCLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_LOCLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_LOCcount.containsKey(input)) {
                        Ey_LOCcount.put(input,Ey_LOCcount.get(input)+1);
                    } else {
                        Ey_LOCcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }


        //Ey_THM
        BufferedReader Ey_THMFile = new BufferedReader(new FileReader("./Data/ByParticles/Ey/Ey_THM_contentOnly.csv"));
        String Ey_THMLine = null;

        HashMap<String, Integer> Ey_THMcount = new HashMap<String, Integer>();

        while ((Ey_THMLine = Ey_THMFile.readLine()) != null) {
            if(Ey_THMLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Ey_THMLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Ey_THMcount.containsKey(input)) {
                        Ey_THMcount.put(input,Ey_THMcount.get(input)+1);
                    } else {
                        Ey_THMcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        List<Module.SortingInt> Rank_result = new ArrayList<>();

        int total_num = 0;
        for (Map.Entry<String, Integer> entry : Total_count.entrySet()) {

            String mainkey = entry.getKey();
            int maincount = entry.getValue();

            boolean checked_1 = false;
            for (Map.Entry<String, Integer> Ey_AGTentry : Ey_AGTcount.entrySet()) {
                if(Ey_AGTentry.getKey().equals(mainkey)){
                    checked_1 = true;
                }
            }
            boolean checked_2 = false;
            for (Map.Entry<String, Integer> Ey_CRTentry : Ey_CRTcount.entrySet()) {
                if(Ey_CRTentry.getKey().equals(mainkey)){
                    checked_2 = true;
                }
            }
            boolean checked_3 = false;
            for (Map.Entry<String, Integer> Ey_EFFentry : Ey_EFFcount.entrySet()) {
                if(Ey_EFFentry.getKey().equals(mainkey)){
                    checked_3 = true;
                }
            }
            boolean checked_4 = false;
            for (Map.Entry<String, Integer> Ey_FNSentry : Ey_FNScount.entrySet()) {
                if(Ey_FNSentry.getKey().equals(mainkey)){
                    checked_4 = true;
                }
            }
            boolean checked_5 = false;
            for (Map.Entry<String, Integer> Ey_GOLentry : Ey_GOLcount.entrySet()) {
                if(Ey_GOLentry.getKey().equals(mainkey)){
                    checked_5 = true;
                }
            }
            boolean checked_6 = false;
            for (Map.Entry<String, Integer> Ey_INSentry : Ey_INScount.entrySet()) {
                if(Ey_INSentry.getKey().equals(mainkey)){
                    checked_6 = true;
                }
            }
            boolean checked_7 = false;
            for (Map.Entry<String, Integer> Ey_LOCentry : Ey_LOCcount.entrySet()) {
                if(Ey_LOCentry.getKey().equals(mainkey)){
                    checked_7 = true;
                }
            }
            boolean checked_8 = false;
            for (Map.Entry<String, Integer> Ey_THMentry : Ey_THMcount.entrySet()) {
                if(Ey_THMentry.getKey().equals(mainkey)){
                    checked_8 = true;
                }
            }

//            //TOTAL
//            if(checked_1==true&checked_2==true&checked_3==true&checked_4==true&checked_5==true&checked_6==true&checked_7==true&checked_8==true){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }

//            //AGT
//            if(checked_1==true&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==false&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //CRT
//            if(checked_1==false&checked_2==true&checked_3==false&checked_4==false&checked_5==false&checked_6==false&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //EFF
//            if(checked_1==false&checked_2==false&checked_3==true&checked_4==false&checked_5==false&checked_6==false&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //FNS
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==true&checked_5==false&checked_6==false&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //GOL
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==true&checked_6==false&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //INS
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==true&checked_7==false&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //LOC
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==false&checked_7==true&checked_8==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
            //THM
            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==false&checked_7==false&checked_8==true){
                Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
                Rank_result.add(result);
                total_num+=1;
            }
        }

        //정렬하는 부분
        for (int i = 0; i < Rank_result.size(); i++) {
            for (int j = i + 1; j < Rank_result.size(); j++) {
                Module.SortingInt source = Rank_result.get(i), dest = Rank_result.get(j);
                if (source.count < dest.count) {
                    Rank_result.set(i, dest);
                    Rank_result.set(j, source);
                }
            }
        }

        int num = 0;
        for (int i = 0; i < Rank_result.size(); i++) {
            num++;
            System.out.println(num + "," + Rank_result.get(i));
        }

    }




    public static void AnalysisEyseToken () throws IOException {

        HashMap<String, Integer> Total_count = new HashMap<String, Integer>();

        //Eyse_LOC
        BufferedReader Eyse_LOCFile = new BufferedReader(new FileReader("./Data/ByParticles/Eyse/Eyse_LOC_contentOnly.csv"));
        String Eyse_LOCLine = null;

        HashMap<String, Integer> Eyse_LOCcount = new HashMap<String, Integer>();

        while ((Eyse_LOCLine = Eyse_LOCFile.readLine()) != null) {
            if(Eyse_LOCLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Eyse_LOCLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Eyse_LOCcount.containsKey(input)) {
                        Eyse_LOCcount.put(input,Eyse_LOCcount.get(input)+1);
                    } else {
                        Eyse_LOCcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }


        //Eyse_SRC
        BufferedReader Eyse_SRCFile = new BufferedReader(new FileReader("./Data/ByParticles/Eyse/Eyse_SRC_contentOnly.csv"));
        String Eyse_SRCLine = null;

        HashMap<String, Integer> Eyse_SRCcount = new HashMap<String, Integer>();

        while ((Eyse_SRCLine = Eyse_SRCFile.readLine()) != null) {
            if(Eyse_SRCLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Eyse_SRCLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Eyse_SRCcount.containsKey(input)) {
                        Eyse_SRCcount.put(input,Eyse_SRCcount.get(input)+1);
                    } else {
                        Eyse_SRCcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        List<Module.SortingInt> Rank_result = new ArrayList<>();

        int total_num = 0;
        for (Map.Entry<String, Integer> entry : Total_count.entrySet()) {

            String mainkey = entry.getKey();
            int maincount = entry.getValue();

            boolean checked_1 = false;
            for (Map.Entry<String, Integer> Eyse_LOCentry : Eyse_LOCcount.entrySet()) {
                if(Eyse_LOCentry.getKey().equals(mainkey)){
                    checked_1 = true;
                }
            }
            boolean checked_2 = false;
            for (Map.Entry<String, Integer> Eyse_SRCentry : Eyse_SRCcount.entrySet()) {
                if(Eyse_SRCentry.getKey().equals(mainkey)){
                    checked_2 = true;
                }
            }


//            //TOTAL
//            if(checked_1==true&checked_2==true){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }

//            //LOC
//            if(checked_1==true&checked_2==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
            //SRC
            if(checked_1==false&checked_2==true){
                Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
                Rank_result.add(result);
                total_num+=1;
            }
        }

        //정렬하는 부분
        for (int i = 0; i < Rank_result.size(); i++) {
            for (int j = i + 1; j < Rank_result.size(); j++) {
                Module.SortingInt source = Rank_result.get(i), dest = Rank_result.get(j);
                if (source.count < dest.count) {
                    Rank_result.set(i, dest);
                    Rank_result.set(j, source);
                }
            }
        }

        int num = 0;
        for (int i = 0; i < Rank_result.size(); i++) {
            num++;
            System.out.println(num + "," + Rank_result.get(i));
        }

    }


    public static void AnalysisLoToken () throws IOException {

        HashMap<String, Integer> Total_count = new HashMap<String, Integer>();

        //Lo_CRT
        BufferedReader Lo_CRTFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_CRT_contentOnly.csv"));
        String Lo_CRTLine = null;

        HashMap<String, Integer> Lo_CRTcount = new HashMap<String, Integer>();

        while ((Lo_CRTLine = Lo_CRTFile.readLine()) != null) {
            if(Lo_CRTLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_CRTLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_CRTcount.containsKey(input)) {
                        Lo_CRTcount.put(input,Lo_CRTcount.get(input)+1);
                    } else {
                        Lo_CRTcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Lo_DIR
        BufferedReader Lo_DIRFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_DIR_contentOnly.csv"));
        String Lo_DIRLine = null;

        HashMap<String, Integer> Lo_DIRcount = new HashMap<String, Integer>();

        while ((Lo_DIRLine = Lo_DIRFile.readLine()) != null) {
            if(Lo_DIRLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_DIRLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_DIRcount.containsKey(input)) {
                        Lo_DIRcount.put(input,Lo_DIRcount.get(input)+1);
                    } else {
                        Lo_DIRcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Lo_EFF
        BufferedReader Lo_EFFFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_EFF_contentOnly.csv"));
        String Lo_EFFLine = null;

        HashMap<String, Integer> Lo_EFFcount = new HashMap<String, Integer>();

        while ((Lo_EFFLine = Lo_EFFFile.readLine()) != null) {
            if(Lo_EFFLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_EFFLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_EFFcount.containsKey(input)) {
                        Lo_EFFcount.put(input,Lo_EFFcount.get(input)+1);
                    } else {
                        Lo_EFFcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Lo_FNS
        BufferedReader Lo_FNSFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_FNS_contentOnly.csv"));
        String Lo_FNSLine = null;

        HashMap<String, Integer> Lo_FNScount = new HashMap<String, Integer>();

        while ((Lo_FNSLine = Lo_FNSFile.readLine()) != null) {
            if(Lo_FNSLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_FNSLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_FNScount.containsKey(input)) {
                        Lo_FNScount.put(input,Lo_FNScount.get(input)+1);
                    } else {
                        Lo_FNScount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }


        //Lo_INS
        BufferedReader Lo_INSFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_INS_contentOnly.csv"));
        String Lo_INSLine = null;

        HashMap<String, Integer> Lo_INScount = new HashMap<String, Integer>();

        while ((Lo_INSLine = Lo_INSFile.readLine()) != null) {
            if(Lo_INSLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_INSLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_INScount.containsKey(input)) {
                        Lo_INScount.put(input,Lo_INScount.get(input)+1);
                    } else {
                        Lo_INScount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }

        //Lo_LOC
        BufferedReader Lo_LOCFile = new BufferedReader(new FileReader("./Data/ByParticles/Lo/Lo_LOC_contentOnly.csv"));
        String Lo_LOCLine = null;

        HashMap<String, Integer> Lo_LOCcount = new HashMap<String, Integer>();

        while ((Lo_LOCLine = Lo_LOCFile.readLine()) != null) {
            if(Lo_LOCLine.contains("Number,Sentence")){
                continue;
            } else {
                String [] first = Lo_LOCLine.split(",");
                String [] second = first[1].split(" ");
                for(String input : second){
                    if(Lo_LOCcount.containsKey(input)) {
                        Lo_LOCcount.put(input,Lo_LOCcount.get(input)+1);
                    } else {
                        Lo_LOCcount.put(input,1);
                    }

                    if(Total_count.containsKey(input)) {
                        Total_count.put(input,Total_count.get(input)+1);
                    } else {
                        Total_count.put(input,1);
                    }
                }
            }
        }




        List<Module.SortingInt> Rank_result = new ArrayList<>();

        int total_num = 0;
        for (Map.Entry<String, Integer> entry : Total_count.entrySet()) {

            String mainkey = entry.getKey();
            int maincount = entry.getValue();

            boolean checked_1 = false;
            for (Map.Entry<String, Integer> Lo_CRTentry : Lo_CRTcount.entrySet()) {
                if(Lo_CRTentry.getKey().equals(mainkey)){
                    checked_1 = true;
                }
            }
            boolean checked_2 = false;
            for (Map.Entry<String, Integer> Lo_DIRentry : Lo_DIRcount.entrySet()) {
                if(Lo_DIRentry.getKey().equals(mainkey)){
                    checked_2 = true;
                }
            }
            boolean checked_3 = false;
            for (Map.Entry<String, Integer> Lo_EFFentry : Lo_EFFcount.entrySet()) {
                if(Lo_EFFentry.getKey().equals(mainkey)){
                    checked_3 = true;
                }
            }
            boolean checked_4 = false;
            for (Map.Entry<String, Integer> Lo_FNSentry : Lo_FNScount.entrySet()) {
                if(Lo_FNSentry.getKey().equals(mainkey)){
                    checked_4 = true;
                }
            }
            boolean checked_5 = false;
            for (Map.Entry<String, Integer> Lo_INSentry : Lo_INScount.entrySet()) {
                if(Lo_INSentry.getKey().equals(mainkey)){
                    checked_5 = true;
                }
            }
            boolean checked_6 = false;
            for (Map.Entry<String, Integer> Lo_LOCentry : Lo_LOCcount.entrySet()) {
                if(Lo_LOCentry.getKey().equals(mainkey)){
                    checked_6 = true;
                }
            }

            //TOTAL
            if(checked_1==true&checked_2==true&checked_3==true&checked_4==true&checked_5==true&checked_6==true){
                Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
                Rank_result.add(result);
                total_num+=1;
            }

//            //CRT
//            if(checked_1==true&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //DIR
//            if(checked_1==false&checked_2==true&checked_3==false&checked_4==false&checked_5==false&checked_6==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //EFF
//            if(checked_1==false&checked_2==false&checked_3==true&checked_4==false&checked_5==false&checked_6==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //FNS
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==true&checked_5==false&checked_6==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //INS
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==true&checked_6==false){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
//            //LOC
//            if(checked_1==false&checked_2==false&checked_3==false&checked_4==false&checked_5==false&checked_6==true){
//                Module.Sorting result = new Module.Sorting(entry.getKey(),entry.getValue());
//                Rank_result.add(result);
//                total_num+=1;
//            }
        }

        //정렬하는 부분
        for (int i = 0; i < Rank_result.size(); i++) {
            for (int j = i + 1; j < Rank_result.size(); j++) {
                Module.SortingInt source = Rank_result.get(i), dest = Rank_result.get(j);
                if (source.count < dest.count) {
                    Rank_result.set(i, dest);
                    Rank_result.set(j, source);
                }
            }
        }

        int num = 0;
        for (int i = 0; i < Rank_result.size(); i++) {
            num++;
            System.out.println(num + "," + Rank_result.get(i));
        }

    }

}