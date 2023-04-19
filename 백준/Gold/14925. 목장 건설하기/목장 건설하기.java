import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[1001][1001];
    static int[][] dp = new int[1001][1001];

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j]==0)
                    dp[i][j] = 1;
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]==0)
                {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j] , dp[i][j-1]),dp[i-1][j-1]) +1;

                    result = Math.max(result,dp[i][j]);
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static void print()
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}