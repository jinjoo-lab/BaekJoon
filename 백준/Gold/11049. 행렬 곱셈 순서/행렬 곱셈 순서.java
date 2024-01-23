import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][2];
        dp = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }

        for(int i=1;i<n;i++){
            for(int j= i + 1 ;j<=n;j++){

                dp[i][j] = Integer.MAX_VALUE;

                if(i + 1 == j)
                    dp[i][j] = cal(board[i][0],board[j][0],board[j][1]);
            }
        }

        for(int j=1;j<=n;j++){
            for(int i=1;i<n;i++){
                if(i + j > n)
                    continue;

                for(int k=i;k<i+j;k++){
                    dp[i][i+j] = Math.min(dp[i][i+j],dp[i][k] + dp[k+1][i+j] + cal(board[i][0],board[k][1],board[i+j][1]));
                }

            }
        }


        System.out.println(dp[1][n]);
        bf.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int cal(int x, int y, int z){
        return x * y * z;
    }
}