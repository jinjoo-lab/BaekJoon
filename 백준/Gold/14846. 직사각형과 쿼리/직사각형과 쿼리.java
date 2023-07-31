import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[][] board;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];
        dp = new int[n+1][n+1][11];

        for(int i=1;i<=n;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j][board[i][j]] +=1;

                for(int k=1;k<=10;k++)
                {
                    dp[i][j][k] += dp[i][j-1][k];
                }
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                for(int k=1;k<=10;k++)
                {
                    dp[i][j][k] +=dp[i-1][j][k];
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());

            int count = 0;
            for(int j=1;j<=10;j++)
            {
                if(dp[lx][ly][j] - dp[sx -1][ly][j] - dp[lx][sy -1][j] + dp[sx-1][sy-1][j] >=1)
                {
                    count +=1;
                }
            }

            sb.append(count+"\n");
        }


        System.out.print(sb);
        br.close();
    }
}