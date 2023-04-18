import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[1001][1001];
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = board[1][1];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                int a = board[i][j];
                int b = board[i][j];
                int c = board[i][j];

                if(i-1>=1)
                    a = dp[i-1][j] + board[i][j];
                if(j-1>=1)
                    b = dp[i][j-1] + board[i][j];
                if(i-1>=1&&j-1>=1)
                    c = dp[i-1][j-1] + board[i][j];

                dp[i][j] = Math.max(Math.max(a,b),c);
            }
        }
        System.out.println(dp[n][m]);
        br.close();
    }
}