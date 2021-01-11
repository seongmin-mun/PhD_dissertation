package Processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataGenerationForBERT {

    public static List<String> SE_List (int start, int end, List<String> Inlist) throws IOException {
        List<String> outList = new ArrayList<>();
        for (int  i = start; i < end; i++){
            outList.add(Inlist.get(i));
        }
        return outList;
    }

    public static void Ey_process (String dirFile) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        StringBuilder builder = new StringBuilder();
        String tagLine = null;

        //builder.append("Index,Label,Sentence\n");

        //LOC, GOL, EFF, CRT, THM, INS, AGT, FNS
        List<String> FNSList = new ArrayList<>();
        List<String> INSList = new ArrayList<>();
        List<String> GOLList = new ArrayList<>();
        List<String> EFFList = new ArrayList<>();
        List<String> CRTList = new ArrayList<>();
        List<String> LOCList = new ArrayList<>();
        List<String> AGTList = new ArrayList<>();
        List<String> THMList = new ArrayList<>();

        while ((tagLine = tagBr.readLine()) != null) {
            int Label;
            String [] eachSentence = tagLine.split(" ### ");
            if(eachSentence[0].equals("FNS") && eachSentence[1].equals("FNS") && eachSentence[2].equals("FNS")){
                Label = 0;
                FNSList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("INS") && eachSentence[1].equals("INS") && eachSentence[2].equals("INS")){
                Label = 1;
                INSList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("GOL") && eachSentence[1].equals("GOL") && eachSentence[2].equals("GOL")){
                Label = 2;
                GOLList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("EFF") && eachSentence[1].equals("EFF") && eachSentence[2].equals("EFF")){
                Label = 3;
                EFFList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("CRT") && eachSentence[1].equals("CRT") && eachSentence[2].equals("CRT")){
                Label = 4;
                CRTList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("LOC") && eachSentence[1].equals("LOC") && eachSentence[2].equals("LOC")){
                Label = 5;
                LOCList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("AGT") && eachSentence[1].equals("AGT") && eachSentence[2].equals("AGT")){
                Label = 6;
                AGTList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("THM") && eachSentence[1].equals("THM") && eachSentence[2].equals("THM")){
                Label = 7;
                THMList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            }
        }

        List<String> TestList = new ArrayList<>();
        List<String> TrainList = new ArrayList<>();

        //FNS
        List<String> FNSList_test = SE_List(0,(FNSList.size()/10),FNSList);
        for(String FNS_test : FNSList_test){
            TestList.add(FNS_test);
        }

        List<String> FNSList_train = SE_List((FNSList.size()/10),FNSList.size(),FNSList);
        for(String FNS_train : FNSList_train){
            TrainList.add(FNS_train);
        }


        //INS
        List<String> INSList_test = SE_List(0,(INSList.size()/10),INSList);
        for(String INS_test : INSList_test){
            TestList.add(INS_test);
        }

        List<String> INSList_train = SE_List((INSList.size()/10),INSList.size(),INSList);
        for(String INS_train : INSList_train){
            TrainList.add(INS_train);
        }

        //GOL
        List<String> GOLList_test = SE_List(0,(GOLList.size()/10),GOLList);
        for(String GOL_test : GOLList_test){
            TestList.add(GOL_test);
        }

        List<String> GOLList_train = SE_List((GOLList.size()/10),GOLList.size(),GOLList);
        for(String GOL_train : GOLList_train){
            TrainList.add(GOL_train);
        }

        //EFF
        List<String> EFFList_test = SE_List(0,(EFFList.size()/10),EFFList);
        for(String EFF_test : EFFList_test){
            TestList.add(EFF_test);
        }

        List<String> EFFList_train = SE_List((EFFList.size()/10),EFFList.size(),EFFList);
        for(String EFF_train : EFFList_train){
            TrainList.add(EFF_train);
        }

        //CRT
        List<String> CRTList_test = SE_List(0,(CRTList.size()/10),CRTList);
        for(String CRT_test : CRTList_test){
            TestList.add(CRT_test);
        }

        List<String> CRTList_train = SE_List((CRTList.size()/10),CRTList.size(),CRTList);
        for(String CRT_train : CRTList_train){
            TrainList.add(CRT_train);
        }

        //LOC
        List<String> LOCList_test = SE_List(0,(LOCList.size()/10),LOCList);
        for(String LOC_test : LOCList_test){
            TestList.add(LOC_test);
        }

        List<String> LOCList_train = SE_List((LOCList.size()/10),LOCList.size(),LOCList);
        for(String LOC_train : LOCList_train){
            TrainList.add(LOC_train);
        }

        //AGT
        List<String> AGTList_test = SE_List(0,(AGTList.size()/10),AGTList);
        for(String AGT_test : AGTList_test){
            TestList.add(AGT_test);
        }

        List<String> AGTList_train = SE_List((AGTList.size()/10),AGTList.size(),AGTList);
        for(String AGT_train : AGTList_train){
            TrainList.add(AGT_train);
        }


        //THM
        List<String> THMList_test = SE_List(0,(THMList.size()/10),THMList);
        for(String THM_test : THMList_test){
            TestList.add(THM_test);
        }

        List<String> THMList_train = SE_List((THMList.size()/10),THMList.size(),THMList);
        for(String THM_train : THMList_train){
            TrainList.add(THM_train);
        }


        StringBuilder train_builder = new StringBuilder();

        train_builder.append("Index,Label,Sentence\n");

        int train_num = 0;
        for(String each_train : TrainList){
            System.out.println(train_num+","+each_train);
            train_builder.append(train_num+","+each_train+"\n");
            train_num++;
        }

        String train_OutputFile = "./Data/BERT/train_Ey.csv";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(train_OutputFile, false), "UTF-8"));
        bw.write(train_builder.toString());
        bw.flush();
        bw.close();


        StringBuilder test_builder = new StringBuilder();

        test_builder.append("Index,Label,Sentence\n");
        int test_num = 0;
        for(String each_test : TestList){
            System.out.println(test_num+","+each_test);
            test_builder.append(test_num+","+each_test+"\n");
            test_num++;
        }

        String test_OutputFile = "./Data/BERT/test_Ey.csv";
        BufferedWriter test_bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test_OutputFile, false), "UTF-8"));
        test_bw.write(test_builder.toString());
        test_bw.flush();
        test_bw.close();

        //System.out.println(SE_List(0,(FNSList.size()/10),FNSList));  //1512 167

    };




    public static void Eyse_process (String dirFile) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        StringBuilder builder = new StringBuilder();
        String tagLine = null;

        //builder.append("Index,Label,Sentence\n");

        //SRC, LOC

        List<String> SRCList = new ArrayList<>();
        List<String> LOCList = new ArrayList<>();

        while ((tagLine = tagBr.readLine()) != null) {
            int Label;
            String [] eachSentence = tagLine.split(" ### ");
            if(eachSentence[0].equals("SRC") && eachSentence[1].equals("SRC") && eachSentence[2].equals("SRC")){
                Label = 0;
                SRCList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("LOC") && eachSentence[1].equals("LOC") && eachSentence[2].equals("LOC")){
                Label = 1;
                LOCList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            }
        }

        List<String> TestList = new ArrayList<>();
        List<String> TrainList = new ArrayList<>();

        //SRC
        List<String> SRCList_test = SE_List(0,(SRCList.size()/10),SRCList);
        for(String SRC_test : SRCList_test){
            TestList.add(SRC_test);
        }

        List<String> SRCList_train = SE_List((SRCList.size()/10),SRCList.size(),SRCList);
        for(String SRC_train : SRCList_train){
            TrainList.add(SRC_train);
        }


        //LOC
        List<String> LOCList_test = SE_List(0,(LOCList.size()/10),LOCList);
        for(String LOC_test : LOCList_test){
            TestList.add(LOC_test);
        }

        List<String> LOCList_train = SE_List((LOCList.size()/10),LOCList.size(),LOCList);
        for(String LOC_train : LOCList_train){
            TrainList.add(LOC_train);
        }


        StringBuilder train_builder = new StringBuilder();

        train_builder.append("Index,Label,Sentence\n");

        int train_num = 0;
        for(String each_train : TrainList){
            System.out.println(train_num+","+each_train);
            train_builder.append(train_num+","+each_train+"\n");
            train_num++;
        }

        String train_OutputFile = "./Data/BERT/train_Eyse.csv";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(train_OutputFile, false), "UTF-8"));
        bw.write(train_builder.toString());
        bw.flush();
        bw.close();


        StringBuilder test_builder = new StringBuilder();

        test_builder.append("Index,Label,Sentence\n");
        int test_num = 0;
        for(String each_test : TestList){
            System.out.println(test_num+","+each_test);
            test_builder.append(test_num+","+each_test+"\n");
            test_num++;
        }

        String test_OutputFile = "./Data/BERT/test_Eyse.csv";
        BufferedWriter test_bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test_OutputFile, false), "UTF-8"));
        test_bw.write(test_builder.toString());
        test_bw.flush();
        test_bw.close();

        //System.out.println(SE_List(0,(FNSList.size()/10),FNSList));  //1512 167

    };



    public static void Lo_process (String dirFile) throws IOException {
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        StringBuilder builder = new StringBuilder();
        String tagLine = null;

        //builder.append("Index,Label,Sentence\n");

        //FNS, INS, DIR, EFF, CRT, LOC

        List<String> FNSList = new ArrayList<>();
        List<String> INSList = new ArrayList<>();
        List<String> DIRList = new ArrayList<>();
        List<String> EFFList = new ArrayList<>();
        List<String> CRTList = new ArrayList<>();
        List<String> LOCList = new ArrayList<>();

        while ((tagLine = tagBr.readLine()) != null) {
            int Label;
            String [] eachSentence = tagLine.split(" ### ");
            if(eachSentence[0].equals("FNS") && eachSentence[1].equals("FNS") && eachSentence[2].equals("FNS")){
                Label = 0;
                FNSList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("INS") && eachSentence[1].equals("INS") && eachSentence[2].equals("INS")){
                Label = 1;
                INSList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("DIR") && eachSentence[1].equals("DIR") && eachSentence[2].equals("DIR")){
                Label = 2;
                DIRList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("EFF") && eachSentence[1].equals("EFF") && eachSentence[2].equals("EFF")){
                Label = 3;
                EFFList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("CRT") && eachSentence[1].equals("CRT") && eachSentence[2].equals("CRT")){
                Label = 4;
                CRTList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            } else if (eachSentence[0].equals("LOC") && eachSentence[1].equals("LOC") && eachSentence[2].equals("LOC")){
                Label = 5;
                LOCList.add(String.valueOf(Label)+","+eachSentence[4].replace(",",""));
            }
        }

        List<String> TestList = new ArrayList<>();
        List<String> TrainList = new ArrayList<>();

        //FNS
        List<String> FNSList_test = SE_List(0,(FNSList.size()/10),FNSList);
        for(String FNS_test : FNSList_test){
            TestList.add(FNS_test);
        }

        List<String> FNSList_train = SE_List((FNSList.size()/10),FNSList.size(),FNSList);
        for(String FNS_train : FNSList_train){
            TrainList.add(FNS_train);
        }


        //INS
        List<String> INSList_test = SE_List(0,(INSList.size()/10),INSList);
        for(String INS_test : INSList_test){
            TestList.add(INS_test);
        }

        List<String> INSList_train = SE_List((INSList.size()/10),INSList.size(),INSList);
        for(String INS_train : INSList_train){
            TrainList.add(INS_train);
        }

        //DIR
        List<String> DIRList_test = SE_List(0,(DIRList.size()/10),DIRList);
        for(String DIR_test : DIRList_test){
            TestList.add(DIR_test);
        }

        List<String> DIRList_train = SE_List((DIRList.size()/10),DIRList.size(),DIRList);
        for(String DIR_train : DIRList_train){
            TrainList.add(DIR_train);
        }

        //EFF
        List<String> EFFList_test = SE_List(0,(EFFList.size()/10),EFFList);
        for(String EFF_test : EFFList_test){
            TestList.add(EFF_test);
        }

        List<String> EFFList_train = SE_List((EFFList.size()/10),EFFList.size(),EFFList);
        for(String EFF_train : EFFList_train){
            TrainList.add(EFF_train);
        }

        //CRT
        List<String> CRTList_test = SE_List(0,(CRTList.size()/10),CRTList);
        for(String CRT_test : CRTList_test){
            TestList.add(CRT_test);
        }

        List<String> CRTList_train = SE_List((CRTList.size()/10),CRTList.size(),CRTList);
        for(String CRT_train : CRTList_train){
            TrainList.add(CRT_train);
        }

        //LOC
        List<String> LOCList_test = SE_List(0,(LOCList.size()/10),LOCList);
        for(String LOC_test : LOCList_test){
            TestList.add(LOC_test);
        }

        List<String> LOCList_train = SE_List((LOCList.size()/10),LOCList.size(),LOCList);
        for(String LOC_train : LOCList_train){
            TrainList.add(LOC_train);
        }


        StringBuilder train_builder = new StringBuilder();

        train_builder.append("Index,Label,Sentence\n");

        int train_num = 0;
        for(String each_train : TrainList){
            System.out.println(train_num+","+each_train);
            train_builder.append(train_num+","+each_train+"\n");
            train_num++;
        }

        String train_OutputFile = "./Data/BERT/train_Lo.csv";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(train_OutputFile, false), "UTF-8"));
        bw.write(train_builder.toString());
        bw.flush();
        bw.close();


        StringBuilder test_builder = new StringBuilder();

        test_builder.append("Index,Label,Sentence\n");
        int test_num = 0;
        for(String each_test : TestList){
            System.out.println(test_num+","+each_test);
            test_builder.append(test_num+","+each_test+"\n");
            test_num++;
        }

        String test_OutputFile = "./Data/BERT/test_Lo.csv";
        BufferedWriter test_bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test_OutputFile, false), "UTF-8"));
        test_bw.write(test_builder.toString());
        test_bw.flush();
        test_bw.close();

        //System.out.println(SE_List(0,(FNSList.size()/10),FNSList));  //1512 167

    };


}
