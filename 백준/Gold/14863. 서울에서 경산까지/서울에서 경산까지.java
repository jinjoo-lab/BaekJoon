import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[][] board;
    static int MIN = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][4];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int tw = Integer.parseInt(st.nextToken());
            int cw = Integer.parseInt(st.nextToken());
            int tb = Integer.parseInt(st.nextToken());
            int cb = Integer.parseInt(st.nextToken());

            board[i][0] = tw;
            board[i][1] = cw;
            board[i][2] = tb;
            board[i][3] = cb;
        }

        int[][] dp = new int[n+1][k + 1];

        dp[1][board[1][0]] = board[1][1];
        dp[1][board[1][2]] = Math.max(dp[1][board[1][2]], board[1][3]);

        for(int i = 2 ; i <= n ; i++) {
            for(int j = k ; j >= board[i][0] ; j--) {
                if(dp[i-1][j - board[i][0]] != 0) {
                    dp[i][j] = Math.max(dp[i][j] , dp[i-1][j - board[i][0]] + board[i][1]);
                }
            }

            for(int j = k ; j >= board[i][2] ; j--) {
                if(dp[i-1][j - board[i][2]] != 0) {
                    dp[i][j] = Math.max(dp[i][j] , dp[i-1][j - board[i][2]] + board[i][3]);
                }
            }
        }

        int result = 0;

        for(int i = 0 ; i <= k ; i++) {
            result = Math.max(result, dp[n][i]);
        }

        System.out.println(result);
        br.close();
    }

}
