package Processing;

public class DensityNumbertJson {
    public static void main (String [] args){
        int [] clusterNumberEy = {2,1,1,2,1,1,3,2,3,3,3,4,2,2,5,1,3,2,3,3,4,4,3,3,4,4,4,4,4,5,3,3,4,5,4,5,5,4,4,5,5,5,5,4,4,5,5,5,4,4};
        int [] clusterNumberEyse = {1,1,1,1,1,1,1,1,2,1,2,2,2,1,1,1,2,2,1,2,2,2,1,2,2,1,2,2,1,1,2,1,2,2,2,2,2,1,2,2,1,1,2,2,2,2,2,2,2,2};
        int [] clusterNumberLo = {1,1,1,3,2,3,3,3,2,3,3,5,5,5,3,3,4,4,4,4,4,4,4,3,5,4,4,5,4,4,3,4,5,3,4,5,4,5,5,5,5,5,3,4,4,6,6,6,4,4};

        String [] postposition = {"ey", "eyse", "(u)lo"};

        System.out.print("var Density_info = [");

        for (String post : postposition){
            System.out.print("{'postposition': '"+post+"', 'cluster': [");
            if(post.equals("ey")){
                for(int i = 0; i < 50; i++){
                    if(i==49){
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberEy[i]+"}");
                    } else {
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberEy[i]+"},");
                    }
                }
            } else if (post.equals("eyse")){
                for(int i = 0; i < 50; i++){
                    if(i==49){
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberEyse[i]+"}");
                    } else {
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberEyse[i]+"},");
                    }
                }
            } else {
                for(int i = 0; i < 50; i++){
                    if(i==49){
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberLo[i]+"}");
                    } else {
                        System.out.print("{'epoch': 'epoch"+i+"', 'clusterNumber': "+clusterNumberLo[i]+"},");
                    }
                }
            }

            System.out.print("]},");
        }

        System.out.print("]");


    }
}
