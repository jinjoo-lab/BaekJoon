import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = m;
        board = new int[n+1][n+1];

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
        }

        int[][] dp = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + board[i][j];
            }
        }

        //print(dp);


        find(dp);

        System.out.println(result);

        br.close();
    }

    static void find(int[][] dp){
        int half = (int)Math.sqrt(m);

        for(int i = 1 ; i <= half ; i ++){
            if(m % i == 0){
                int f = i;
                int s = m / i;

                go(f,s,dp);
            }
        }
    }

    static int result;

    static void go(int f,int s,int[][] dp){
        for(int i = 0 ; i <= n ; i++){
            for(int j = 0 ; j <= n ; j++){
                if(i + f > n || j + s > n)
                    continue;

                int lx = i + f;
                int ly = j + s;

                int tmpNum = m - (dp[lx][ly] - dp[lx][j] - dp[i][ly] + dp[i][j]);


                result = Math.min(result,tmpNum);
            }
        }
    }


    static void print(int[][] dp){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
