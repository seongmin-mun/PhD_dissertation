package Processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Dictionary.PartOfSpeeches.Put_POS_tag;

/**
 * Created by Seongmin_M on 2019. 12. 25..
 */
public class DataRefined {
    public static String[] forEy = {
            "LOC",
            "GOL",
            "EFF",
            "CRT",
            "THM",
            "INS",
            "AGT",
            "FNS"
    };

    public static String[] forEyse = {
            "LOC",
            "SRC"
    };

    public static String[] forLo = {
            "LOC",
            "DIR",
            "EFF",
            "CRT",
            "INS",
            "FNS"
    };

    public static void refinedData (String particle) throws IOException {

        String filename = "";

        if(particle.equals("Ey")){
            filename = "Ey_tag_sentence.txt";
        } else if (particle.equals("Eyse")){
            filename = "Eyse_tag_sentence.txt";
        } else if (particle.equals("Lo")){
            filename = "Lo_tag_sentence_re.txt";
        }

        BufferedReader inputFile = new BufferedReader(new FileReader("./Data/AnnotationFinal/"+filename));
        String fileLine = null;

        List<String> fileData = new ArrayList<String>();


        while ((fileLine = inputFile.readLine()) != null) {
            fileData.add(fileLine);
            System.out.println(fileLine);
        }

        if(particle.equals("Ey")){
            for(String function : forEy){
                StringBuilder builder = new StringBuilder();
                builder.append("Number,Sentence\n");
                int number = 1;
                for(String input : fileData){
                    String [] each = input.split(" ### ");
                    if(each[0].equals(function) && each[1].equals(function) && each[2].equals(function)){

                        //contentword만 포함시키기 위한 부분
                        String [] eachword = each[3].split(" ");
                        String outsentence = "";
                        for(String word : eachword){
                            boolean checked = false;
                            for(String pos : Put_POS_tag){
                                if(word.contains(pos) || word.equals("에/JKB")){
                                    checked = true;
                                }
                            }
                            if(checked == true){
                                outsentence = outsentence + word + " ";
                            }
                        }
                        outsentence = outsentence.trim();

                        builder.append(number+","+outsentence+"\n");
                        number++;
                    }
                }
//                String OutputFile = "./Data/ByParticles/"+particle+"/"+particle+"_"+function+"_contentOnly.csv";
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//                bw.write(builder.toString());
//                bw.flush();
//                bw.close();
            }
        } else if (particle.equals("Eyse")){
            for(String function : forEyse){
                StringBuilder builder = new StringBuilder();
                builder.append("Number,Sentence\n");
                int number = 1;
                for(String input : fileData){
                    String [] each = input.split(" ### ");
                    if(each[0].equals(function) && each[1].equals(function) && each[2].equals(function)){

                        //contentword만 포함시키기 위한 부분
                        String [] eachword = each[3].split(" ");
                        String outsentence = "";
                        for(String word : eachword){
                            boolean checked = false;
                            for(String pos : Put_POS_tag){
                                if(word.contains(pos) || word.equals("에서/JKB")){
                                    checked = true;
                                }
                            }
                            if(checked == true){
                                outsentence = outsentence + word + " ";
                            }
                        }
                        outsentence = outsentence.trim();

                        builder.append(number+","+outsentence+"\n");
                        number++;
                    }
                }
//                String OutputFile = "./Data/ByParticles/"+particle+"/"+particle+"_"+function+"_contentOnly.csv";
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//                bw.write(builder.toString());
//                bw.flush();
//                bw.close();
            }
        } else if (particle.equals("Lo")){
            for(String function : forLo){
                StringBuilder builder = new StringBuilder();
                builder.append("Number,Sentence\n");
                int number = 1;
                for(String input : fileData){
                    String [] each = input.split(" ### ");
                    if(each[0].equals(function) && each[1].equals(function) && each[2].equals(function)){

                        //contentword만 포함시키기 위한 부분
                        String [] eachword = each[3].split(" ");
                        String outsentence = "";
                        for(String word : eachword){
                            boolean checked = false;
                            for(String pos : Put_POS_tag){
                                if(word.contains(pos) || word.equals("(으)로/JKB")){
                                    checked = true;
                                }
                            }
                            if(checked == true){
                                outsentence = outsentence + word + " ";
                            }
                        }
                        outsentence = outsentence.trim();

                        builder.append(number+","+outsentence+"\n");
                        number++;
                    }
                }
//                String OutputFile = "./Data/ByParticles/"+particle+"/"+particle+"_"+function+"_contentOnly.csv";
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile, false), "UTF-8"));
//                bw.write(builder.toString());
//                bw.flush();
//                bw.close();
            }
        }

    }

}
