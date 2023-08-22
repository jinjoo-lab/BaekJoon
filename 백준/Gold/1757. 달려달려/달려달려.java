import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] board = new int[n+1];

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[n+2][m+2][2];

        dp[1][1][1] = board[1];

        for(int i=2;i<n;i++) {
            for(int j=0;j<m;j++){
                dp[i][j][0] = Math.max(dp[i-1][j+1][1],dp[i-1][j+1][0]);
            }

            dp[i][0][0] = Math.max(dp[i][0][0],dp[i-1][0][0]);

            dp[i][1][1] = dp[i-1][0][0] + board[i];

            for(int j=2;j<=m;j++){
                dp[i][j][1] = dp[i-1][j-1][1] + board[i];
            }
        }

        int max = Math.max(Math.max(dp[n-1][1][0],dp[n-1][1][1]),dp[n-1][0][0]);
        System.out.println(max);
        br.close();
    }

    static void print(int[][][] dp){
        for(int j=1;j<=n;j++) {
            for (int i = 0; i <= m; i++) {
                System.out.print(dp[j][i][0]+" "+dp[j][i][1]+" , ");
            }
            System.out.println();
        }
    }
}