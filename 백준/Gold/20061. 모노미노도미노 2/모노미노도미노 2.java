import java.util.*;
import java.io.*;

public class Main {

    static int result;
    static int n;
    static int[][] query;

    static int[][] blue = new int[4][7];
    static int[][] green= new int[7][4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        query = new int[n][3];

        for(int a = 0 ; a < n ; a++){
            st = new StringTokenizer(br.readLine()," ");

            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            query[a][0] = num;
            query[a][1] = x;
            query[a][2] = y;
        }

        for(int i = 0 ; i < n ; i++){
            int curNum = query[i][0];
            int curX = query[i][1];
            int curY = query[i][2];

            if(curNum == 1){
                moveBlue(curX,curX,1);
                moveGreen(curY,curY,1);
            }else if(curNum == 2){
                moveBlue(curX,curX,2);
                moveGreen(curY,curY+1,1);
            }else{
                moveBlue(curX,curX+1,1);
                moveGreen(curY,curY,2);
            }

            breakBlue();
            breakGreen();
            isOutBlue();
            isOutGreen();

            //print();
        }

        //print();
        int count = count();
        System.out.println(result);
        System.out.println(count);

        br.close();
    }

    static int count(){

        int count = 0;

        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 6 ; j++){
                if(blue[i][j] == 1)
                    count++;
            }
        }


        for(int i = 0 ; i < 6 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                if(green[i][j] == 1)
                    count++;
            }
        }


        return count;
    }

    static void print(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 6 ; j++){
                System.out.print(blue[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i = 0 ; i < 6 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                System.out.print(green[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void moveBlue(int sr,int er,int len){

        int idx = 0;

        for(int j = 0 ; j < 6; j++){
            if(blue[sr][j] == 0 && blue[er][j] == 0){
                idx++;
                continue;
            }

            break;
        }

        idx--;

        for(int j = idx - len + 1 ; j <= idx; j++){
            blue[sr][j] = 1;
            blue[er][j] = 1;
        }
    }

    static void moveGreen(int sc,int ec,int len){
        int idx = 0;

        for(int i = 0 ; i < 6; i++){
            if(green[i][sc] == 0 && green[i][ec] == 0){
                idx++;
                continue;
            }

            break;
        }

        idx--;

        for(int i = idx - len + 1 ; i <= idx; i++){
            green[i][sc] = 1;
            green[i][ec] = 1;
        }
    }

    static void breakBlue(){

        boolean[] isBreak = new boolean[7];

        for(int j = 5 ; j >= 0 ; j--){

            int count = 0;

            for(int i = 0 ; i < 4 ; i++){
                if(blue[i][j] == 1)
                    count++;
            }

            if(count == 4){
                for(int i = 0 ; i < 4 ; i++) {
                    blue[i][j] = 0;
                }

                isBreak[j] = true;
                result++;
            }
        }

        for(int j = 0 ; j < 6; j++){
            if(isBreak[j]){
                for(int k = j - 1 ; k >= 0 ; k--){
                    for(int i = 0 ; i < 4 ; i++){
                        blue[i][k+1] = blue[i][k];
                    }
                }
            }
        }
    }

    static void breakGreen(){

        boolean[] isBreak = new boolean[7];

        for(int i = 6 ; i >= 0 ; i--){

            int count = 0;

            for(int j = 0 ; j < 4 ; j++){
                if(green[i][j] == 1)
                    count++;
            }

            if(count == 4){
                for(int j = 0 ; j < 4 ; j++){
                    green[i][j] = 0;
                }

                isBreak[i] = true;
                result++;
            }
        }

        for(int i = 0 ; i < 6; i++){
            if(isBreak[i]){
                for(int k = i - 1 ; k >= 0 ; k--){
                    for(int j = 0 ; j < 4 ; j++){
                        green[k+1][j] = green[k][j];
                    }
                }
            }
        }
    }

    static void isOutBlue(){
        boolean[] isBreak = new boolean[7];

        for(int j = 0 ; j <= 1 ; j++){
            for(int i = 0 ; i < 4 ; i++){
                if(blue[i][j] == 1){
                    isBreak[j + 4] = true;
                }
            }
        }

        for(int j = 0 ; j < 6; j++){
            if(isBreak[j]){
                for(int k = j - 1 ; k >= 0 ; k--){
                    for(int i = 0 ; i < 4 ; i++){
                        blue[i][k+1] = blue[i][k];
                        blue[i][k] = 0;
                    }
                }
            }
        }
    }

    static void isOutGreen(){
        boolean[] isBreak = new boolean[7];

        for(int i = 0 ; i <= 1 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                if(green[i][j] == 1){
                    isBreak[i + 4] = true;
                }
            }
        }

        for(int i = 0 ; i < 6; i++){
            if(isBreak[i]){
                for(int k = i - 1 ; k >= 0 ; k--){
                    for(int j = 0 ; j < 4 ; j++){
                        green[k+1][j] = green[k][j];
                        green[k][j] = 0;
                    }
                }
            }
        }
    }
}
