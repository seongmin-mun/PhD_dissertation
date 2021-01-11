package Main;

import Dictionary.DataForJson;
import Processing.DataRefined;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static Processing.DataRefined.refinedData;


/**
 * Created by Seongmin_M on 2019. 12. 11..
 */
public class MainRun {
    public static void main (String [] args) throws IOException {

        //곂치는 토큰과 그렇지 않은 토큰확인
//        AnalysisEyToken ();
//        AnalysisEyseToken ();
//        AnalysisLoToken ();

        //데이터 생성하기
        //refinedData("Lo");

//        List outPut = Processing.AnnotationCorpus.generatod("./Data/AnnotationCorpus/Fleiss_Kappa_lo.csv","./Data/AnnotationCorpus/2(으로,로)_case_sentence.txt");
//        Processing.GSwithOriginal.ghCompare("./Data/GS/lo_GH.txt","./Data/original/2(으로,로)_case_sentence.txt");

        //boram
//        Processing.EyseBoramCompare.BoramCompare("./Data/Compare/eyseBoram.txt","./Data/AnnotationCorpus/Fleiss_Kappa_eyse.csv");
//        Processing.EyseBoramCompare.BoramCompare("./Data/original/2(으로,로)_case_sentence.txt","./Data/AnnotationCorpus/Fleiss_Kappa_eyse.csv");

        //GSwithKappa
//        Processing.AnnotationCorpus.test("./Data/AnnotationFinal/Lo_tag_sentence_re.txt");

        //IMPRS SBMs
//        Conference.IMPRS.AllCorpus();

        //Conference.IMPRS.KfoldCrossValidation(10);
//
//        List<String> outPut = Processing.DataRefined.SBMs("./Data/ByParticles/Lo/Lo_FNS_input.csv","FNS");
//        for(String what : outPut){
//            System.out.println(what);
//        }

        //Data generation(BERT)
        //LOC, GOL, EFF, CRT, THM, INS, AGT, FNS
        //Processing.DataGenerationForBERT.Ey_process("./Data/AnnotationFinal/Ey_tag_sentence.txt");
        //SRC, LOC
        //Processing.DataGenerationForBERT.Eyse_process("./Data/AnnotationFinal/Eyse_tag_sentence.txt");
        //FNS, INS, DIR, EFF, CRT, LOC
        //Processing.DataGenerationForBERT.Lo_process("./Data/AnnotationFinal/Lo_tag_sentence_re.txt");

        //SimilarityBasedEstimate
        //SBE.DataRefinement.KfoldCrossValidation(10);
        //SBE.DataRefinement.AllCorpus();

        //DataGenerationForJson
        //빈도에 따른 컨텐츠 단어 리스트
        //Processing.FrequencyWordList.WordList("./Data/AnnotationFinal/Lo_tag_sentence_re.txt");

        //SimilarityDescendingOrder
        //유사도에 따라 내림차순된 단어 리스트

//        for(String method: DataForJson.methods){
//            for(String post : DataForJson.postpositions){
//                if(post.equals("Ey")){
//                    for(String func:DataForJson.EyFuncList){
//                        for(int i = 1; i < 11; i++){
//                            System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
//                            Processing.SimilarityDescending.DescendingOrder("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Similarity/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",method,post,func,i);
//                        }
//                    }
//                } else if(post.equals("Eyse")){
//                    for(String func:DataForJson.EyseFuncList){
//                        for(int i = 1; i < 11; i++){
//                            System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
//                            Processing.SimilarityDescending.DescendingOrder("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Similarity/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",method,post,func,i);
//                        }
//                    }
//                } else if(post.equals("Lo")){
//                    for(String func:DataForJson.LoFuncList){
//                        for(int i = 1; i < 11; i++){
//                            System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
//                            Processing.SimilarityDescending.DescendingOrder("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Similarity/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",method,post,func,i);
//                        }
//                    }
//                }
//            }
//        }



        //단어 리스트 추출하기
        //Processing.WordRecomendation.WordListGenerator();

        //Json-wordnet

//        StringBuilder builder = new StringBuilder();
//
//        System.out.println("var DSMs_info = [");
//        builder.append("var DSMs_info = [");
//        for(String method: DataForJson.methods){
//            for(String post : DataForJson.postpositions){
//                for(int i = 1; i < 11; i++){
//                    if (post.equals("Lo")){
//                        System.out.println("{'method':'"+method.toLowerCase()+"','postposition':'(u)lo','window':'window"+String.valueOf(i)+"','wordnet':[");
//                        builder.append("{'method':'"+method.toLowerCase()+"','postposition':'(u)lo','window':'window"+String.valueOf(i)+"','wordnet':[");
//                    } else {
//                        System.out.println("{'method':'"+method.toLowerCase()+"','postposition':'"+post.toLowerCase()+"','window':'window"+String.valueOf(i)+"','wordnet':[");
//                        builder.append("{'method':'"+method.toLowerCase()+"','postposition':'"+post.toLowerCase()+"','window':'window"+String.valueOf(i)+"','wordnet':[");
//                    }
//                    List<String> FinalList = Processing.JsonDSM.Wordnet("./Data/Frequency/EmbededResult/"+method+"/"+post+"/t-SNE/"+post+"_tSNE_"+String.valueOf(i)+".csv", "./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs"+".csv", method, post, String.valueOf(i));
//                    for (String each : FinalList){
//                        builder.append(each);
//                    }
//                    if(method.equals("SGNS")&&post.equals("Lo")&&i==10){
//                        System.out.println("]}");
//                        builder.append("]}");
//                    } else {
//                        System.out.println("]},");
//                        builder.append("]},");
//                    }
//                }
//            }
//        }
//        System.out.println("];");
//        builder.append("];");
//
//        String OutputFile = "./Data/JsonFile/DSMsdatas.js";
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//        bw.write(builder.toString());
//        bw.flush();
//        bw.close();


        //Json-network

//        StringBuilder builder = new StringBuilder();
//
//        builder.append("var network_info = [");
//
//        for(String method: DataForJson.methods){
//            for(String post : DataForJson.postpositions){
//                if(post.equals("Ey")){
//                    for(String func:DataForJson.EyFuncList){
//                        for(int i = 1; i < 11; i++){
//                            builder.append("{'method':'"+method.toLowerCase()+"','postposition':'ey','function':'"+func.toLowerCase()+"','window':'window"+String.valueOf(i)+"','nodes':[");
//                            List<String> NodeList = Processing.JsonNetwork.NodeList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int fnum = 0;
//                            for (String each : NodeList){
//                                fnum += 1;
//                                if (fnum==30){
//                                    builder.append(each);
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            builder.append("],'links':[");
//                            List<String> LinkList = Processing.JsonNetwork.LinkList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int snum = 0;
//                            for (String each : LinkList){
//                                snum += 1;
//                                if (snum==30){
//                                    builder.append(each);
//                                } else if (snum==31) {
//                                    continue;
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            if(method.equals("SGNS")&&post.equals("Lo")&&i==10&&func.equals("FNS")){
//                                builder.append("]}");
//                            } else {
//                                builder.append("]},");
//                            }
//                        }
//                    }
//                } else if(post.equals("Eyse")){
//                    for(String func:DataForJson.EyseFuncList){
//                        for(int i = 1; i < 11; i++){
//                            builder.append("{'method':'"+method.toLowerCase()+"','postposition':'eyse','function':'"+func.toLowerCase()+"','window':'window"+String.valueOf(i)+"','nodes':[");
//                            List<String> NodeList = Processing.JsonNetwork.NodeList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int fnum = 0;
//                            for (String each : NodeList){
//                                fnum += 1;
//                                if (fnum==30){
//                                    builder.append(each);
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            builder.append("],'links':[");
//                            List<String> LinkList = Processing.JsonNetwork.LinkList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int snum = 0;
//                            for (String each : LinkList){
//                                snum += 1;
//                                if (snum==30){
//                                    builder.append(each);
//                                } else if (snum==31) {
//                                    continue;
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            if(method.equals("SGNS")&&post.equals("Lo")&&i==10&&func.equals("FNS")){
//                                builder.append("]}");
//                            } else {
//                                builder.append("]},");
//                            }
//                        }
//                    }
//                } else if(post.equals("Lo")){
//                    for(String func:DataForJson.LoFuncList){
//                        for(int i = 1; i < 11; i++){
//                            builder.append("{'method':'"+method.toLowerCase()+"','postposition':'(u)lo','function':'"+func.toLowerCase()+"','window':'window"+String.valueOf(i)+"','nodes':[");
//                            List<String> NodeList = Processing.JsonNetwork.NodeList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int fnum = 0;
//                            for (String each : NodeList){
//                                fnum += 1;
//                                if (fnum==30){
//                                    builder.append(each);
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            builder.append("],'links':[");
//                            List<String> LinkList = Processing.JsonNetwork.LinkList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv","./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs.csv",method,post,func,String.valueOf(i));
//                            int snum = 0;
//                            for (String each : LinkList){
//                                snum += 1;
//                                if (snum==30){
//                                    builder.append(each);
//                                } else if (snum==31) {
//                                    continue;
//                                } else {
//                                    builder.append(each+",");
//                                }
//                            }
//                            if(method.equals("SGNS")&&post.equals("Lo")&&i==10&&func.equals("FNS")){
//                                builder.append("]}");
//                            } else {
//                                builder.append("]},");
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        builder.append("];");
//
//        String OutputFile = "./Data/JsonFile/Networkdata.js";
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//        bw.write(builder.toString());
//        bw.flush();
//        bw.close();



//        //Json-BERTmap
//
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("var Map_info = [");
//
//        for(String post : DataForJson.postpositions){
//            if(post.equals("Ey")) {
//                for(int i = 0; i < 50; i++){
//                    builder.append("{'postposition':'ey','epoch':'epoch"+String.valueOf(i)+"','sentences':[");
//                    List<String> tSNEList = Processing.JsonBERTmap.tSNEList("./Data/FromBERT_V2/t-SNE/"+post+"_tSNE_epoch_"+String.valueOf(i)+".csv",post,String.valueOf(i));
//                    int snum = 0;
//                    for (String each : tSNEList){
//                        if (snum==466){
//                            builder.append(each);
//                        } else {
//                            builder.append(each+",");
//                        }
//                        snum += 1;
//                    }
//                    if(post.equals("Lo")&&i==49){
//                        builder.append("]}");
//                    } else {
//                        builder.append("]},");
//                    }
//                }
//            } else if(post.equals("Eyse")){
//                for(int i = 0; i < 50; i++){
//                    builder.append("{'postposition':'eyse','epoch':'epoch"+String.valueOf(i)+"','sentences':[");
//                    List<String> tSNEList = Processing.JsonBERTmap.tSNEList("./Data/FromBERT_V2/t-SNE/"+post+"_tSNE_epoch_"+String.valueOf(i)+".csv",post,String.valueOf(i));
//                    int snum = 0;
//                    for (String each : tSNEList){
//                        if (snum==483){
//                            builder.append(each);
//                        } else {
//                            builder.append(each+",");
//                        }
//                        snum += 1;
//                    }
//                    if(post.equals("Lo")&&i==49){
//                        builder.append("]}");
//                    } else {
//                        builder.append("]},");
//                    }
//                }
//            } else if(post.equals("Lo")){
//                for(int i = 0; i < 50; i++){
//                    builder.append("{'postposition':'(u)lo','epoch':'epoch"+String.valueOf(i)+"','sentences':[");
//                    List<String> tSNEList = Processing.JsonBERTmap.tSNEList("./Data/FromBERT_V2/t-SNE/"+post+"_tSNE_epoch_"+String.valueOf(i)+".csv",post,String.valueOf(i));
//                    int snum = 0;
//                    for (String each : tSNEList){
//                        if (snum==466){
//                            builder.append(each);
//                        } else {
//                            builder.append(each+",");
//                        }
//                        snum += 1;
//                    }
//                    if(post.equals("Lo")&&i==49){
//                        builder.append("]}");
//                    } else {
//                        builder.append("]},");
//                    }
//                }
//            }
//        }
//
//        builder.append("];");
//
//        String OutputFile = "./Data/JsonFile/PostBERT/Madpdata.js";
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//        bw.write(builder.toString());
//        bw.flush();
//        bw.close();


        //Json-BERTsentence

//        StringBuilder builder = new StringBuilder();
//
//        builder.append("var Sentence_info = [");
//
//        for(String post : DataForJson.postpositions) {
//            if (post.equals("Ey")) {
//                builder.append("{'postposition':'ey','sentences':[");
//                List<String> sentenceList = Processing.JsonBERTsentence.sentenceList("./Data/AnnotationFinal/" + post + "_tag_sentence.txt", "./Data/FromBERT_V2/test/test_" + post + ".csv", post);
//                int snum = 0;
//                for (String each : sentenceList) {
//                    if (snum == 466) {
//                        builder.append(each);
//                    } else {
//                        builder.append(each + ",");
//                    }
//                    snum += 1;
//                }
//                builder.append("]},");
//            } else if (post.equals("Eyse")) {
//                builder.append("{'postposition':'eyse','sentences':[");
//                List<String> sentenceList = Processing.JsonBERTsentence.sentenceList("./Data/AnnotationFinal/" + post + "_tag_sentence.txt", "./Data/FromBERT_V2/test/test_" + post + ".csv", post);
//                int snum = 0;
//                for (String each : sentenceList) {
//                    if (snum == 483) {
//                        builder.append(each);
//                    } else {
//                        builder.append(each + ",");
//                    }
//                    snum += 1;
//                }
//                builder.append("]},");
//            } else if (post.equals("Lo")) {
//                builder.append("{'postposition':'(u)lo','sentences':[");
//                List<String> sentenceList = Processing.JsonBERTsentence.sentenceList("./Data/AnnotationFinal/" + post + "_tag_sentence_re.txt", "./Data/FromBERT_V2/test/test_" + post + ".csv", post);
//                int snum = 0;
//                for (String each : sentenceList) {
//                    if (snum == 466) {
//                        builder.append(each);
//                    } else {
//                        builder.append(each + ",");
//                    }
//                    snum += 1;
//                }
//                builder.append("]}");
//            }
//
//            builder.append("];");
//
//            String OutputFile = "./Data/JsonFile/PostBERT/Sentencedata.js";
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//            bw.write(builder.toString());
//            bw.flush();
//            bw.close();
//
//        }


//        //Json to CSV for the clustering with R
//        DataRefine.JsonToCSV.DataProcessing("./Data/JsonFile/DSMsdatas.js");

        //Json to CSV for the clustering with R
        DataRefine.JsonToCSV_BERTAccuracy.DataProcessing("./Data/JsonFile/PostBERT/AccuracyBERT.js");


    }
}
