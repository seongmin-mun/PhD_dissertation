package Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static translator.Romanization.yale_romanization;

public class JsonNetwork {
    public static List NodeList (String similarFile, String totalWordFile, String method, String post, String func, String window) throws IOException {

        BufferedReader totalWordBr = new BufferedReader(new FileReader(totalWordFile));
        String totalWordLine = null;

        HashMap<String, Integer> totalWordHash = new HashMap<String, Integer>();

        while ((totalWordLine = totalWordBr.readLine()) != null) {
            String [] eachLine = totalWordLine.split(",");
            totalWordHash.put(eachLine[1],Integer.parseInt(eachLine[2]));
        }


        BufferedReader similarBr = new BufferedReader(new FileReader(similarFile));
        String similarLine = null;

        List<String> similarList = new ArrayList<>();

        while ((similarLine = similarBr.readLine()) != null) {
            String [] eachLine = similarLine.split(",");
            if(eachLine[1].contains("JKB")){
                continue;
            } else {
                similarList.add(eachLine[1]+","+eachLine[2]);
            }
        }

        List<String> NodeList = new ArrayList<>();
        List<String> LinkList = new ArrayList<>();

        if (post.equals("Ey")){//"LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"
            if(func.equals("LOC")){
                NodeList.add("{'id':'에/ey/JKB_LOC','pos':'JKB','frequency':1780}");
                LinkList.add("{'source':'에/ey/JKB_LOC','target':'에/ey/JKB_LOC','value':1}");
            } else if(func.equals("GOL")){
                NodeList.add("{'id':'에/ey/JKB_GOL','pos':'JKB','frequency':441}");
                LinkList.add("{'source':'에/ey/JKB_GOL','target':'에/ey/JKB_GOL','value':1}");
            } else if(func.equals("EFF")){
                NodeList.add("{'id':'에/ey/JKB_EFF','pos':'JKB','frequency':198}");
                LinkList.add("{'source':'에/ey/JKB_EFF','target':'에/ey/JKB_EFF','value':1}");
            } else if(func.equals("CRT")){
                NodeList.add("{'id':'에/ey/JKB_CRT','pos':'JKB','frequency':1516}");
                LinkList.add("{'source':'에/ey/JKB_CRT','target':'에/ey/JKB_CRT','value':1}");
            } else if(func.equals("THM")){
                NodeList.add("{'id':'에/ey/JKB_THM','pos':'JKB','frequency':448}");
                LinkList.add("{'source':'에/ey/JKB_THM','target':'에/ey/JKB_THM','value':1}");
            } else if(func.equals("INS")){
                NodeList.add("{'id':'에/ey/JKB_INS','pos':'JKB','frequency':69}");
                LinkList.add("{'source':'에/ey/JKB_INS','target':'에/ey/JKB_INS','value':1}");
            } else if(func.equals("AGT")){
                NodeList.add("{'id':'에/ey/JKB_AGT','pos':'JKB','frequency':47}");
                LinkList.add("{'source':'에/ey/JKB_AGT','target':'에/ey/JKB_AGT','value':1}");
            } else if(func.equals("FNS")){
                NodeList.add("{'id':'에/ey/JKB_FNS','pos':'JKB','frequency':216}");
                LinkList.add("{'source':'에/ey/JKB_FNS','target':'에/ey/JKB_FNS','value':1}");
            }
        } else if (post.equals("Eyse")){//"SRC","LOC"
            if(func.equals("SRC")){
                NodeList.add("{'id':'에서/eyse/JKB_SRC','pos':'JKB','frequency':647}");
                LinkList.add("{'source':'에서/eyse/JKB_SRC','target':'에서/eyse/JKB_SRC','value':1}");
            } else if(func.equals("LOC")){
                NodeList.add("{'id':'에서/eyse/JKB_LOC','pos':'JKB','frequency':4206}");
                LinkList.add("{'source':'에서/eyse/JKB_LOC','target':'에서/eyse/JKB_LOC','value':1}");
            }
        } else if (post.equals("Lo")){//"LOC","DIR","EFF","CRT","INS","FNS"
            if(func.equals("LOC")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_LOC','pos':'JKB','frequency':158}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_LOC','target':'(으)로/(u)lo/JKB_LOC','value':1}");
            } else if(func.equals("DIR")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_DIR','pos':'JKB','frequency':1449}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_DIR','target':'(으)로/(u)lo/JKB_DIR','value':1}");
            } else if(func.equals("EFF")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_EFF','pos':'JKB','frequency':88}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_EFF','target':'(으)로/(u)lo/JKB_EFF','value':1}");
            } else if(func.equals("CRT")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_CRT','pos':'JKB','frequency':593}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_CRT','target':'(으)로/(u)lo/JKB_CRT','value':1}");
            } else if(func.equals("INS")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_INS','pos':'JKB','frequency':739}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_INS','target':'(으)로/(u)lo/JKB_INS','value':1}");
            } else if(func.equals("FNS")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_FNS','pos':'JKB','frequency':1681}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_FNS','target':'(으)로/(u)lo/JKB_FNS','value':1}");
            }
        }



        int num = 0;
        for (String similarWord : similarList){
            String [] info = similarWord.split(",");
            String word = info[0];
            String word_ko = word.split("/")[0];
            String word_eng = yale_romanization(word_ko);
            String word_pos = word.split("/")[1];
            Float similarity = Float.parseFloat(info[1]);
            int frequency = 0;
            if(totalWordHash.containsKey(word)) {
                frequency = frequency + totalWordHash.get(word);
                String id = word_ko+"/"+word_eng+"/"+word_pos;
                String pos = word_pos;

                String node = "{'id':'"+id+"','pos':'"+pos+"','frequency':"+frequency+"}";
                NodeList.add(node);

                String source = "";

                if (post.equals("Ey")){
                    source = "에/ey/JKB_"+func;
                } else if (post.equals("Eyse")){
                    source = "에서/eyse/JKB_"+func;
                } else if (post.equals("Lo")){
                    source = "(으)로/(u)lo/JKB_"+func;
                }

                String target = id;
                Float value = similarity;

                String link = "{'source':'"+source+"','target':'"+target+"','value':"+value+"}";
                LinkList.add(link);
                num += 1;
            }
            if (num == 29){
                break;
            }

        }

//        for(String nodes : NodeList){
//            System.out.println(nodes);
//        }
//
//        for(String links : LinkList){
//            System.out.println(links);
//        }


        String FinalString = "";


        return NodeList;
    };


    public static List LinkList (String similarFile, String totalWordFile, String method, String post, String func, String window) throws IOException {

        BufferedReader totalWordBr = new BufferedReader(new FileReader(totalWordFile));
        String totalWordLine = null;

        HashMap<String, Integer> totalWordHash = new HashMap<String, Integer>();

        while ((totalWordLine = totalWordBr.readLine()) != null) {
            String [] eachLine = totalWordLine.split(",");
            totalWordHash.put(eachLine[1],Integer.parseInt(eachLine[2]));
        }


        BufferedReader similarBr = new BufferedReader(new FileReader(similarFile));
        String similarLine = null;

        List<String> similarList = new ArrayList<>();

        while ((similarLine = similarBr.readLine()) != null) {
            String [] eachLine = similarLine.split(",");
            if(eachLine[1].contains("JKB")){
                continue;
            } else {
                similarList.add(eachLine[1]+","+eachLine[2]);
            }
        }

        List<String> NodeList = new ArrayList<>();
        List<String> LinkList = new ArrayList<>();

        if (post.equals("Ey")){//"LOC","GOL","EFF","CRT","THM","INS","AGT","FNS"
            if(func.equals("LOC")){
                NodeList.add("{'id':'에/ey/JKB_LOC','pos':'JKB','frequency':1780}");
                LinkList.add("{'source':'에/ey/JKB_LOC','target':'에/ey/JKB_LOC','value':1}");
            } else if(func.equals("GOL")){
                NodeList.add("{'id':'에/ey/JKB_GOL','pos':'JKB','frequency':441}");
                LinkList.add("{'source':'에/ey/JKB_GOL','target':'에/ey/JKB_GOL','value':1}");
            } else if(func.equals("EFF")){
                NodeList.add("{'id':'에/ey/JKB_EFF','pos':'JKB','frequency':198}");
                LinkList.add("{'source':'에/ey/JKB_EFF','target':'에/ey/JKB_EFF','value':1}");
            } else if(func.equals("CRT")){
                NodeList.add("{'id':'에/ey/JKB_CRT','pos':'JKB','frequency':1516}");
                LinkList.add("{'source':'에/ey/JKB_CRT','target':'에/ey/JKB_CRT','value':1}");
            } else if(func.equals("THM")){
                NodeList.add("{'id':'에/ey/JKB_THM','pos':'JKB','frequency':448}");
                LinkList.add("{'source':'에/ey/JKB_THM','target':'에/ey/JKB_THM','value':1}");
            } else if(func.equals("INS")){
                NodeList.add("{'id':'에/ey/JKB_INS','pos':'JKB','frequency':69}");
                LinkList.add("{'source':'에/ey/JKB_INS','target':'에/ey/JKB_INS','value':1}");
            } else if(func.equals("AGT")){
                NodeList.add("{'id':'에/ey/JKB_AGT','pos':'JKB','frequency':47}");
                LinkList.add("{'source':'에/ey/JKB_AGT','target':'에/ey/JKB_AGT','value':1}");
            } else if(func.equals("FNS")){
                NodeList.add("{'id':'에/ey/JKB_FNS','pos':'JKB','frequency':216}");
                LinkList.add("{'source':'에/ey/JKB_FNS','target':'에/ey/JKB_FNS','value':1}");
            }
        } else if (post.equals("Eyse")){//"SRC","LOC"
            if(func.equals("SRC")){
                NodeList.add("{'id':'에서/eyse/JKB_SRC','pos':'JKB','frequency':647}");
                LinkList.add("{'source':'에서/eyse/JKB_SRC','target':'에서/eyse/JKB_SRC','value':1}");
            } else if(func.equals("LOC")){
                NodeList.add("{'id':'에서/eyse/JKB_LOC','pos':'JKB','frequency':4206}");
                LinkList.add("{'source':'에서/eyse/JKB_LOC','target':'에서/eyse/JKB_LOC','value':1}");
            }
        } else if (post.equals("Lo")){//"LOC","DIR","EFF","CRT","INS","FNS"
            if(func.equals("LOC")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_LOC','pos':'JKB','frequency':158}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_LOC','target':'(으)로/(u)lo/JKB_LOC','value':1}");
            } else if(func.equals("DIR")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_DIR','pos':'JKB','frequency':1449}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_DIR','target':'(으)로/(u)lo/JKB_DIR','value':1}");
            } else if(func.equals("EFF")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_EFF','pos':'JKB','frequency':88}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_EFF','target':'(으)로/(u)lo/JKB_EFF','value':1}");
            } else if(func.equals("CRT")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_CRT','pos':'JKB','frequency':593}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_CRT','target':'(으)로/(u)lo/JKB_CRT','value':1}");
            } else if(func.equals("INS")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_INS','pos':'JKB','frequency':739}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_INS','target':'(으)로/(u)lo/JKB_INS','value':1}");
            } else if(func.equals("FNS")){
                NodeList.add("{'id':'(으)로/(u)lo/JKB_FNS','pos':'JKB','frequency':1681}");
                LinkList.add("{'source':'(으)로/(u)lo/JKB_FNS','target':'(으)로/(u)lo/JKB_FNS','value':1}");
            }
        }



        int num = 0;
        for (String similarWord : similarList){
            String [] info = similarWord.split(",");
            String word = info[0];
            String word_ko = word.split("/")[0];
            String word_eng = yale_romanization(word_ko);
            String word_pos = word.split("/")[1];
            Float similarity = Float.parseFloat(info[1]);
            int frequency = 0;
            if(totalWordHash.containsKey(word)) {
                frequency = frequency + totalWordHash.get(word);
                String id = word_ko+"/"+word_eng+"/"+word_pos;
                String pos = word_pos;

                String node = "{'id':'"+id+"','pos':'"+pos+"','frequency':"+frequency+"}";
                NodeList.add(node);

                String source = "";

                if (post.equals("Ey")){
                    source = "에/ey/JKB_"+func;
                } else if (post.equals("Eyse")){
                    source = "에서/eyse/JKB_"+func;
                } else if (post.equals("Lo")){
                    source = "(으)로/(u)lo/JKB_"+func;
                }

                String target = id;
                Float value = similarity;

                String link = "{'source':'"+source+"','target':'"+target+"','value':"+value+"}";
                LinkList.add(link);
                num += 1;
            }
            if (num == 29){
                break;
            }

        }

//        for(String nodes : NodeList){
//            System.out.println(nodes);
//        }
//
//        for(String links : LinkList){
//            System.out.println(links);
//        }


        String FinalString = "";


        return LinkList;
    };
}
