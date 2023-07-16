import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static long m = 0;
    static int[] board;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        board = new int[n+1];
        dp = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int[][] p = new int[n+1][n+1];

        for(int i = 1 ;i <=m ;i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            p[x][y] = 1;
            p[y][x] = 1;
        }


        for(int i=1;i<=n;i++)
        {
            System.out.println(search(i,p));
        }

        br.close();
    }

    static int search(int start , int[][] path)
    {
        if(dp[start] != 0)
            return dp[start];

        dp[start] = 1;

        int max = 0;
        for(int i=1;i<=n;i++)
        {
            int tmp = 0;
            if(path[start][i] == 1)
            {
                if(board[start] < board[i])
                {
                    tmp += search(i,path);
                }
            }

            max = Math.max(tmp,max);
        }

        return dp[start] += max;
    }
}
