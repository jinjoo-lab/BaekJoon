
import java.util.*;
import java.io.*;

public class Main {

    static int result = 26;
    static boolean[][] v = new boolean[11][11];
    static int[] count = new int[6];
    static int[][] board = new int[11][11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1 ; i <= 10 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= 10 ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ;  i <= 4 ; i++){
            count[i] = 5;
        }

        travel(1,1,0);

        if(result == 26){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

        br.close();
    }

    static void travel(int x,int y,int c){

        if(result < c){
            return;
        }

        if(y > 10){
            y = 1;
            x+= 1;
        }

        if(x > 10){
            result = Math.min(result,c);
            return;
        }

        if(board[x][y] == 1 && !v[x][y]){
            for(int i = 4 ; i >= 0 ; i--){
                if(count[i] >= 1 && isIt(x,y,i)){
                    count[i] -= 1;
                    go(x,y,i,true);
                    travel(x,y+1,c+1);
                    count[i] += 1;
                    go(x,y,i,false);
                }
            }
        }else{
            travel(x,y+1,c);
        }

    }

    static void go(int x,int y,int idx,boolean sig){
        for(int i = x; i <= x + idx; i++){
            for(int j = y ; j <= y + idx ; j++){
                v[i][j] = sig;
            }
        }
    }

    static boolean isIt(int x,int y,int idx){
        for(int i = x; i <= x + idx; i++){
            for(int j = y ; j <= y + idx ; j++){
                if(i < 1 || i > 10 || j < 1 || j > 10)
                    return false;

                if(board[i][j] == 0)
                    return false;
                
                if(v[i][j])
                    return false;
            }
            
        }
        return true;
    }
}
