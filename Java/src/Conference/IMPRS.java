package Conference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IMPRS {

    public static String[] forLoP = {
            "DIR",
            "INS",
            "FNS"
    };

    public static String[] stoppos = {
            "/SF",
            "/SP",
            "/SS",
            "/SE",
            "/SO",
            "/SL",
            "/SH",
            "/SW",
            "/NF"
    };


    public static List SBMs (String dirFile, String function) throws IOException {   //SimilarityBasedMethods   //IMPRS
        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        String tagLine = null;

        List<String> outList = new ArrayList<>();
        int num = 0;
        while ((tagLine = tagBr.readLine()) != null) {
            String step_1 = tagLine.replaceAll("\\d+\\,","");
            String [] step_2 = step_1.split(" ");
            String step_4 = "";
            for (String step_3: step_2){
                boolean isChecked = false;
                for (String stop : stoppos) {
                    if (step_3.contains(stop)) {
                        isChecked = true;
                    }
                }
                if(step_3.equals("(으)로/JKB")){
                    step_3 = step_3.replace("(으)로","(으)로_"+function);
                }
                if(isChecked==false){
                    step_4 = step_4 + step_3 + " ";
                }
            }
            String step_5 = step_4.replaceAll("\\s$","");
            if((num<=700)&&(num!=0)){
                //System.out.println(step_5);
                outList.add(step_5);
            }
            num+=1;
        }

        return outList;
    };

    public static void AllCorpus () throws IOException {
        String FNSdir = "./Data/ByParticles/Lo/Lo_FNS_input.csv";
        String DIRdir = "./Data/ByParticles/Lo/Lo_DIR_input.csv";
        String INSdir = "./Data/ByParticles/Lo/Lo_INS_input.csv";

        List<String> trainList = new ArrayList<>();

        //FNS
        List<String> FNSlist = SBMs(FNSdir,"FNS");

        int FNSnum = 0;
        for(String resultFNS : FNSlist){
            trainList.add(resultFNS);
            FNSnum+=1;
        }

        //DIR
        List<String> DIRlist = SBMs(DIRdir,"DIR");

        int DIRnum = 0;
        for(String resultDIR : DIRlist){
            trainList.add(resultDIR);
            DIRnum+=1;
        }

        //INS
        List<String> INSlist = SBMs(INSdir,"INS");

        int INSnum = 0;
        for(String resultINS : INSlist){
            trainList.add(resultINS);
            INSnum+=1;
        }

        StringBuilder trainbuilder = new StringBuilder();
        trainbuilder.append("Number,Sentence"+"\n");
        int trainNum = 0;
        for(String resultTRAIN : trainList){
            trainbuilder.append(trainNum+","+resultTRAIN+"\n");
            trainNum+=1;
        }

        String trainOutput = "./Data/IMPRS/"+String.valueOf(0)+"Fold/Lo_train_"+String.valueOf(0)+".csv";
        BufferedWriter TrainBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trainOutput, false), "UTF-8"));
        TrainBw.write(trainbuilder.toString());
        TrainBw.flush();
        TrainBw.close();


    }

    public static void KfoldCrossValidation (int numberK) throws IOException {
        String FNSdir = "./Data/ByParticles/Lo/Lo_FNS_input.csv";
        String DIRdir = "./Data/ByParticles/Lo/Lo_DIR_input.csv";
        String INSdir = "./Data/ByParticles/Lo/Lo_INS_input.csv";

        int range = 700/numberK;

        for (int i = 0; i < numberK; i++){ //start이상 end미만
            int start = i*range;
            int end = (i+1)*range;
            //System.out.println(start+" "+end);

            List<String> testList = new ArrayList<>();
            List<String> trainList = new ArrayList<>();

            //FNS
            List<String> FNSlist = SBMs(FNSdir,"FNS");

            int FNSnum = 0;
            for(String resultFNS : FNSlist){
                if((start <= FNSnum) && (FNSnum < end)){  //범위안에 들어가면 테스트
                    testList.add(resultFNS);
                } else {  //범위 밖이면 트레이닝
                    trainList.add(resultFNS);
                }
                FNSnum+=1;
            }

            //DIR
            List<String> DIRlist = SBMs(DIRdir,"DIR");

            int DIRnum = 0;
            for(String resultDIR : DIRlist){
                if((start <= DIRnum) && (DIRnum < end)){  //범위안에 들어가면 테스트
                    testList.add(resultDIR);
                } else {  //범위 밖이면 트레이닝
                    trainList.add(resultDIR);
                }
                DIRnum+=1;
            }

            //INS
            List<String> INSlist = SBMs(INSdir,"INS");

            int INSnum = 0;
            for(String resultINS : INSlist){
                if((start <= INSnum) && (INSnum < end)){  //범위안에 들어가면 테스트
                    testList.add(resultINS);
                } else {  //범위 밖이면 트레이닝
                    trainList.add(resultINS);
                }
                INSnum+=1;
            }


            StringBuilder testbuilder = new StringBuilder();
            testbuilder.append("Number,Sentence"+"\n");
            int testNum = 0;
            for(String resultTEST : testList){
                testbuilder.append(testNum+","+resultTEST+"\n");
                testNum+=1;
            }

            String testOutput = "./Data/IMPRS/"+String.valueOf(i+1)+"Fold/Lo_test_"+String.valueOf(i+1)+".csv";
            BufferedWriter TestBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testOutput, false), "UTF-8"));
            TestBw.write(testbuilder.toString());
            TestBw.flush();
            TestBw.close();


            StringBuilder trainbuilder = new StringBuilder();
            trainbuilder.append("Number,Sentence"+"\n");
            int trainNum = 0;
            for(String resultTRAIN : trainList){
                trainbuilder.append(trainNum+","+resultTRAIN+"\n");
                trainNum+=1;
            }

            String trainOutput = "./Data/IMPRS/"+String.valueOf(i+1)+"Fold/Lo_train_"+String.valueOf(i+1)+".csv";
            BufferedWriter TrainBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trainOutput, false), "UTF-8"));
            TrainBw.write(trainbuilder.toString());
            TrainBw.flush();
            TrainBw.close();

        }
    }
}
