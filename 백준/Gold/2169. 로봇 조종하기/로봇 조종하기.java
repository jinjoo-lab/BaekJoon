import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine()," ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=m;i++){
            dp[1][i] = dp[1][i-1] + board[1][i];
        }

        for(int i= 2; i <= n ; i++){
            int left[] = new int[m+1];
            int right[] = new int[m+1];

            left[1] = dp[i-1][1] + board[i][1];

            for(int j=2;j<=m;j++){
                left[j] = Math.max(left[j-1],dp[i-1][j]) + board[i][j];
            }

            right[m] = dp[i-1][m] + board[i][m];

            for(int j=m-1;j>=1;j--){
                right[j] = Math.max(right[j+1],dp[i-1][j]) + board[i][j];
            }

            for(int j=1;j<=m;j++){
                dp[i][j] = Math.max(left[j],right[j]);
            }
        }

        System.out.println(dp[n][m]);

        bf.close();
    }
}

