import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][3];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0 ; j < 3 ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n+1][3];

        dp[1][0] = board[1][0];
        dp[1][1] = board[1][1];
        dp[1][2] = board[1][2];

        for(int i = 2 ; i <= n ; i++){
            dp[i][0] = board[i][0] + Math.min(dp[i-1][1] , dp[i-1][2]);
            dp[i][1] = board[i][1] + Math.min(dp[i-1][0] , dp[i-1][2]);
            dp[i][2] = board[i][2] + Math.min(dp[i-1][0] , dp[i-1][1]);
        }

        System.out.println(Math.min(Math.min(dp[n][0],dp[n][1]),dp[n][2]));
        br.close();
    }

}
