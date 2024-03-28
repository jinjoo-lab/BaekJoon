import java.util.*;
import java.io.*;

public class Main {

    static int[] board;
    static int[][][] dp;
    static int size;

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        size = st.countTokens() - 1;

        board = new int[size + 2];
        dp = new int[size + 2][5][5];

        for(int i = 1; i <= size + 1 ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= size; i ++){
            for(int j = 0 ; j < 5 ; j++){
               for(int k = 0 ; k < 5 ; k++){
                   dp[i][j][k] = -1;
               }
            }
        }

        dp[0][0][0] = -1;

        System.out.println(go(0,0,0));

        br.close();
    }

    static int go(int idx,int left,int right){

        if(idx == size){
            return 0;
        }

        if(dp[idx][left][right] != -1)
            return dp[idx][left][right];

        dp[idx][left][right] = 0;

        int Lm = go(idx + 1 ,board[idx + 1], right) + getCount(left,board[idx + 1]);
        int Rm = go(idx + 1,left,board[idx + 1]) + getCount(right,board[idx + 1]);

        return dp[idx][left][right] = Math.min(Lm,Rm);
    }


    static int getCount(int cur,int next){

        if(cur == 0)
            return 2;

        if(cur == next)
            return 1;

        if(cur == 1 || cur == 3){

            if(next == 2 || next == 4)
                return 3;

            else
                return 4;
        }

        if(cur == 2 || cur == 4){
            if(next == 1 || next == 3)
                return 3;

            return 4;
        }

        return 0;
    }
}
