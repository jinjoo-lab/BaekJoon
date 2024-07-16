import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[2][n+1];

        for(int j = 0 ; j <= 1 ; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int i = 1 ; i <= n ; i++) {
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            dp[i][i] = board[0][i] * board[1][i];
        }


        for(int i = 1 ; i < n ; i++) {
            int tmpV1 = board[0][i] * board[1][i + 1];
            int tmpV2 = board[0][i + 1] * board[1][i];

            dp[i][i+1] = tmpV1 + tmpV2;
        }


        for(int k = 2 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                int j = i + k;

                if(j > n)
                    continue;

                dp[i][j] = dp[i+1][j-1] + ((board[0][i] * board[1][j]) + (board[1][i] * board[0][j]));
            }
        }


        int l = 1;
        int r = 1;
        int result = dp[1][1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = i ; j <= n ; j++) {
                 if(result < dp[i][j]) {
                     l = i;
                     r = j;
                     result = dp[i][j];
                 }
            }
        }

        System.out.println((l - 1)+" "+(n - r));
        System.out.println(result);


        br.close();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
