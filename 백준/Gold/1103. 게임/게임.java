import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;
    static int[][] dp;

    static boolean[][] visit;

    static int[] dx = {0, 0,1,-1};
    static int[] dy = {1, -1,0,0};

    static int result  =0 ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                if(line.charAt(j-1)=='H')
                    board[i][j] = -1;
                else
                    board[i][j] = Integer.parseInt(line.charAt(j-1)+"");
            }
        }

        visit[1][1] = true;
        dfs(1,1);

        if(result == 0)
        {
            for(int i=1;i<=n;i++)
            {
                for(int j=1;j<=m;j++)
                {
                    result = Math.max(result , dp[i][j]);
                }
            }

            result = result + 1;
        }

        System.out.println(result);
        br.close();
    }

    static void dfs(int x,int y)
    {

        for(int i=0;i<4;i++)
        {
            int nx = x + board[x][y] * dx[i];
            int ny = y + board[x][y] * dy[i];

            if(nx <1 || nx > n || ny < 1 || ny > m)
                continue;

            if(board[nx][ny]== -1)
                continue;

            if(dp[nx][ny] >= dp[x][y] + 1)
                continue;

            if(visit[nx][ny])
            {
                result = -1;
                return;
            }

            else{
                visit[nx][ny] = true;
                dp[nx][ny] = dp[x][y] + 1;
                dfs(nx,ny);
                visit[nx][ny] = false;
            }
        }
    }
}