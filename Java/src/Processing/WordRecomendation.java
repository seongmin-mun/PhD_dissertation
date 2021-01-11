package Processing;

import Dictionary.DataForJson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordRecomendation {
    public static List SimilarWordList (String dirFile, int wordNum) throws IOException {

        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        String tagLine = null;

        List<String> WordList = new ArrayList<>();

        int num = 0;

        while ((tagLine = tagBr.readLine()) != null) {
            String [] eachLine = tagLine.split(",");
            if (eachLine[1].contains("JKB")){
                continue;
            } else {
                //System.out.println(eachLine[1]);
                WordList.add(eachLine[1]);
                num += 1;
            }

            if(wordNum==num){
                break;
            }
        }

        return WordList;
    };

    public static List EachTokenList (String dirFile, int wordNum) throws IOException {

        BufferedReader tagBr = new BufferedReader(new FileReader(dirFile));
        String tagLine = null;

        List<String> WordList = new ArrayList<>();

        int num = 0;

        while ((tagLine = tagBr.readLine()) != null) {
            String [] eachLine = tagLine.split(",");
            if (eachLine[1].contains("JKB")){
                continue;
            } else {
                WordList.add(eachLine[1]);
                num += 1;
            }

            if(wordNum==num){
                break;
            }
        }

        return WordList;
    };

    public static void WordListGenerator () throws IOException {

        for(String method: DataForJson.methods){
            for(String post : DataForJson.postpositions){
                BufferedReader tagBr = new BufferedReader(new FileReader("./Data/Frequency/WordList/WordList_"+post+".txt"));
                String tagLine = null;

                HashMap<String, Integer> FinalWordHash = new HashMap<String, Integer>();


                //각 조사별 단어의 등장 빈도 저장
                HashMap<String, Integer> FreWordHash = new HashMap<String, Integer>();

                while ((tagLine = tagBr.readLine()) != null) {
                    String [] eachLine = tagLine.split(",");
                    if (eachLine[1].contains("JKB")){
                        continue;
                    } else {
                        FreWordHash.put(eachLine[1],Integer.parseInt(eachLine[2]));
                    }
                }


                //조사별 유사도 값 해쉬맵에 넣어 리스트화하
                if(post.equals("Ey")){
                    for(String func:DataForJson.EyFuncList){
                        for(int i = 1; i < 11; i++){
                            //System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
                            List<String> WordList = SimilarWordList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",3);
                            for (String word:WordList){
                                FinalWordHash.put(word,1);
                            }
                        }
                    }
                } else if(post.equals("Eyse")){
                    for(String func:DataForJson.EyseFuncList){
                        for(int i = 1; i < 11; i++){
                            //System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
                            List<String> WordList = SimilarWordList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",3);
                            for (String word:WordList){
                                FinalWordHash.put(word,1);
                            }
                        }
                    }
                } else if(post.equals("Lo")){
                    for(String func:DataForJson.LoFuncList){
                        for(int i = 1; i < 11; i++){
                            //System.out.println("\n"+method+" : "+post+" : "+func+" : window"+String.valueOf(i)+" : "+"\n");
                            List<String> WordList = SimilarWordList("./Data/Frequency/EmbededResult/"+method+"/"+post+"/Ordered/"+post+"_"+func+"_Similarity_"+String.valueOf(i)+".csv",3);
                            for (String word:WordList){
                                FinalWordHash.put(word,1);
                            }
                        }
                    }
                }


                //각 조사들만 혹은 공통적으로 가지는 단어들 포함하기
                if(post.equals("Ey")){
                    for(String func:DataForJson.addEyFuncList){
                        List<String> WordList = EachTokenList("./Data/Token/"+post+"/"+func+"_token.csv",8);

                        for (String word:WordList){
                            //System.out.println(word);
                            FinalWordHash.put(word,1);
                        }
                    }
                } else if(post.equals("Eyse")){
                    for(String func:DataForJson.addEyseFuncList){
                        List<String> WordList = EachTokenList("./Data/Token/"+post+"/"+func+"_token.csv",8);
                        for (String word:WordList){
                            FinalWordHash.put(word,1);
                        }
                    }
                } else if(post.equals("Lo")){
                    for(String func:DataForJson.addLoFuncList){
                        List<String> WordList = EachTokenList("./Data/Token/"+post+"/"+func+"_token.csv",8);
                        int nnum = 0;
                        for (String word:WordList){
                            //System.out.println(nnum +" , "+word);
                            FinalWordHash.put(word,1);
                            nnum += 1;
                        }
                    }
                }


                //빈도에 따른 단어 리스트와 개별 단어 토큰+ 유사도 단어 매칭하여 저장하기
                HashMap<String, Integer> FinalWordHashMap = new HashMap<String, Integer>();

                int matchNum = 0;
                for (Map.Entry<String, Integer> entry : FinalWordHash.entrySet()) {
                    if(FreWordHash.containsKey(entry.getKey())) {
                        FinalWordHashMap.put(entry.getKey(),FreWordHash.get(entry.getKey()));
                        matchNum += 1;
                    }
                    if(matchNum == 300){
                        break;
                    }
                }


                //각 조사별 단어의 등장 빈도 리스트로 정렬하기
                List<Module.SortingInt> FreWordHash_result = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : FreWordHash.entrySet()) {
                    Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
                    FreWordHash_result.add(result);
                }

                //정렬하는 부분
                for (int i = 0; i < FreWordHash_result.size(); i++) {
                    for (int j = i + 1; j < FreWordHash_result.size(); j++) {
                        Module.SortingInt source = FreWordHash_result.get(i), dest = FreWordHash_result.get(j);
                        if (source.count < dest.count) {
                            FreWordHash_result.set(i, dest);
                            FreWordHash_result.set(j, source);
                        }
                    }
                }


                System.out.println(matchNum);
                if(matchNum<300){
                    int loopNum = 300-matchNum;
                    int putnum = 0;
                    for (int i = 0; i < 300; i++){
                        //System.out.println(FreWordHash_result.get(i).text);
                        if(FinalWordHashMap.containsKey(FreWordHash_result.get(i).text)) {
                            continue;
                        } else {
                            FinalWordHashMap.put(FreWordHash_result.get(i).text,FreWordHash.get(FreWordHash_result.get(i).text));
                            putnum+=1;
                        }
                        if(putnum==loopNum){
                            break;
                        }
                    }
                }


                List<Module.SortingInt> Rank_result = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : FinalWordHashMap.entrySet()) {
                    Module.SortingInt result = new Module.SortingInt(entry.getKey(),entry.getValue());
                    Rank_result.add(result);
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

                StringBuilder builder = new StringBuilder();

                System.out.println("\n"+method+" : "+post+"\n");

                int num = 0;
                for (int i = 0; i < Rank_result.size(); i++) {
                    num++;
                    System.out.println(num + "," + Rank_result.get(i));
                    builder.append(num + "," + Rank_result.get(i)+"\n");

                }

                String OutputFile = "./Data/JsonFile/DSMlist/"+method+"_"+post+"_DSMs"+".csv";
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
                bw.write(builder.toString());
                bw.flush();
                bw.close();


            }
        }


    }
}
