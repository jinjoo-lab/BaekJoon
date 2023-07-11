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

        int[][] board = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();

            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(line.charAt(j-1)+"");
                if(board[i][j] == 1)
                    dp[i][j] = 1;
            }
        }

        int[] dx = {-1,-1,0};
        int[] dy = {0,-1,-1};

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]==1)
                {
                    boolean keep = true;

                    int min = Integer.MAX_VALUE;

                    for(int k=0;k<=2;k++)
                    {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 1 || nx > n || ny < 1 || ny > m) {
                            keep = false;
                            break;
                        }

                        if(board[nx][ny]==0)
                        {
                            keep = false;
                            break;
                        }

                        min = Math.min(min, dp[nx][ny]);
                    }

                    if(keep)
                    {
                        dp[i][j] = min + 1;
                    }
                }
            }
        }


        int max = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                max = Math.max(max , dp[i][j] * dp[i][j]);
            }
        }

        System.out.println(max);
        br.close();
    }
}