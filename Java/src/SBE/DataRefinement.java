package SBE;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRefinement {
    public static String[] forEyP = {
            "LOC",
            "GOL",
            "EFF",
            "CRT",
            "THM",
            "INS",
            "AGT",
            "FNS"
    };

    public static String[] forEyseP = {
            "SRC",
            "LOC"
    };

    public static String[] forLoP = {
            "FNS",
            "INS",
            "DIR",
            "EFF",
            "CRT",
            "LOC"
    };

    public static String[] postpositions = {
            "Ey",
            "Eyse",
            "Lo"
    };

    public static String[] postpositions_ko = {
            "에/JKB",
            "에서/JKB",
            "(으)로/JKB"
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

    public static List SBMs (String dirFile, String function, String postposition) throws IOException {   //SimilarityBasedMethods   //IMPRS
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
                if(step_3.equals(postposition)){
                    step_3 = step_3.replace(postposition,postposition+"_"+function);
                }
                if(isChecked==false){
                    step_4 = step_4 + step_3 + " ";
                }
            }
            String step_5 = step_4.replaceAll("\\s$","");
            if(num!=0){
                //System.out.println(step_5);
                outList.add(step_5);
            }
            num+=1;
        }

        return outList;
    };

    public static void AllCorpus () throws IOException {
        for (String postposition : postpositions){
            if(postposition.equals("Ey")){
                String postKo = postpositions_ko[0];
                for(String function: forEyP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(0)+"Fold/"+postposition+"_train_"+String.valueOf(0)+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, true), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();
                }
            } else if (postposition.equals("Eyse")){
                String postKo = postpositions_ko[1];
                for(String function: forEyseP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(0)+"Fold/"+postposition+"_train_"+String.valueOf(0)+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, true), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();
                }
            } else {
                String postKo = postpositions_ko[2];
                for(String function: forLoP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(0)+"Fold/"+postposition+"_train_"+String.valueOf(0)+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, true), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();

                }
            }
        }


    }

    public static void KfoldCrossValidation (int numberK) throws IOException {

        for (String postposition : postpositions){
            if(postposition.equals("Ey")){
                String postKo = postpositions_ko[0];
                for(String function: forEyP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();
                    builder.append("Number,Sentence"+"\n");

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(fileNum+","+result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/"+postposition+"/"+postposition+"_"+function+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, false), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();

                    int range = fileNum/numberK;

                    for (int i = 0; i < numberK; i++){
                        int start = i*range;
                        int end = (i+1)*range;

                        List<String> testList = new ArrayList<>();
                        List<String> trainList = new ArrayList<>();

                        int foldNum = 0;
                        for(String result : SBMlist){
                            if((start <= foldNum) && (foldNum < end)){  //범위안에 들어가면 테스트
                                testList.add(result);
                            } else {  //범위 밖이면 트레이닝
                                trainList.add(result);
                            }
                            foldNum+=1;
                        }

                        StringBuilder testbuilder = new StringBuilder();
                        int testNum = 0;
                        for(String resultTEST : testList){
                            testbuilder.append(resultTEST+"\n");
                            testNum+=1;
                        }

                        String testOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_test_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TestBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testOutput, true), "UTF-8"));
                        TestBw.write(testbuilder.toString());
                        TestBw.flush();
                        TestBw.close();


                        StringBuilder trainbuilder = new StringBuilder();
                        int trainNum = 0;
                        for(String resultTRAIN : trainList){
                            trainbuilder.append(resultTRAIN+"\n");
                            trainNum+=1;
                        }

                        String trainOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_train_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TrainBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trainOutput, true), "UTF-8"));
                        TrainBw.write(trainbuilder.toString());
                        TrainBw.flush();
                        TrainBw.close();

                    }
                }
            } else if (postposition.equals("Eyse")){
                String postKo = postpositions_ko[1];
                for(String function: forEyseP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();
                    builder.append("Number,Sentence"+"\n");

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(fileNum+","+result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/"+postposition+"/"+postposition+"_"+function+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, false), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();

                    int range = fileNum/numberK;

                    for (int i = 0; i < numberK; i++){
                        int start = i*range;
                        int end = (i+1)*range;

                        List<String> testList = new ArrayList<>();
                        List<String> trainList = new ArrayList<>();

                        int foldNum = 0;
                        for(String result : SBMlist){
                            if((start <= foldNum) && (foldNum < end)){  //범위안에 들어가면 테스트
                                testList.add(result);
                            } else {  //범위 밖이면 트레이닝
                                trainList.add(result);
                            }
                            foldNum+=1;
                        }

                        StringBuilder testbuilder = new StringBuilder();
                        int testNum = 0;
                        for(String resultTEST : testList){
                            testbuilder.append(resultTEST+"\n");
                            testNum+=1;
                        }

                        String testOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_test_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TestBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testOutput, true), "UTF-8"));
                        TestBw.write(testbuilder.toString());
                        TestBw.flush();
                        TestBw.close();


                        StringBuilder trainbuilder = new StringBuilder();
                        int trainNum = 0;
                        for(String resultTRAIN : trainList){
                            trainbuilder.append(resultTRAIN+"\n");
                            trainNum+=1;
                        }

                        String trainOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_train_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TrainBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trainOutput, true), "UTF-8"));
                        TrainBw.write(trainbuilder.toString());
                        TrainBw.flush();
                        TrainBw.close();

                    }
                }
            } else {
                String postKo = postpositions_ko[2];
                for(String function: forLoP){
                    String dirPath = "./Data/ByParticles/"+postposition+"/"+postposition+"_"+function+"_input.csv";
                    List<String> SBMlist = SBMs(dirPath,function,postKo);

                    StringBuilder builder = new StringBuilder();
                    builder.append("Number,Sentence"+"\n");

                    int fileNum = 0;
                    for(String result : SBMlist){
                        builder.append(fileNum+","+result+"\n");
                        fileNum+=1;
                    }

                    String outPut = "./Data/SimilarityBasedEstimate/"+postposition+"/"+postposition+"_"+function+".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPut, false), "UTF-8"));
                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();

                    int range = fileNum/numberK;

                    for (int i = 0; i < numberK; i++){
                        int start = i*range;
                        int end = (i+1)*range;

                        List<String> testList = new ArrayList<>();
                        List<String> trainList = new ArrayList<>();

                        int foldNum = 0;
                        for(String result : SBMlist){
                            if((start <= foldNum) && (foldNum < end)){  //범위안에 들어가면 테스트
                                testList.add(result);
                            } else {  //범위 밖이면 트레이닝
                                trainList.add(result);
                            }
                            foldNum+=1;
                        }

                        StringBuilder testbuilder = new StringBuilder();
                        int testNum = 0;
                        for(String resultTEST : testList){
                            testbuilder.append(resultTEST+"\n");
                            testNum+=1;
                        }

                        String testOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_test_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TestBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testOutput, true), "UTF-8"));
                        TestBw.write(testbuilder.toString());
                        TestBw.flush();
                        TestBw.close();


                        StringBuilder trainbuilder = new StringBuilder();
                        int trainNum = 0;
                        for(String resultTRAIN : trainList){
                            trainbuilder.append(resultTRAIN+"\n");
                            trainNum+=1;
                        }

                        String trainOutput = "./Data/SimilarityBasedEstimate/Fold/"+String.valueOf(i+1)+"Fold/"+postposition+"_train_"+String.valueOf(i+1)+".csv";
                        BufferedWriter TrainBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trainOutput, true), "UTF-8"));
                        TrainBw.write(trainbuilder.toString());
                        TrainBw.flush();
                        TrainBw.close();

                    }
                }
            }
        }
    };

}
