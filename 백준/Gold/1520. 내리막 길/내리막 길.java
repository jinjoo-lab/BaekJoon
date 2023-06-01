import java.io.*;
import java.util.*;
public class Main {

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int n = 0;
    static int m = 0;

    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][m+1];
        dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1,1));
        br.close();
    }

    static int dfs(int x,int y)
    {
        if(x==n&&y==m)
            return 1;

        if(dp[x][y]!=-1)
            return dp[x][y];

        dp[x][y] = 0;

        for(int i=0;i<4;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<1||nx>n||ny<1||ny>m)
                continue;

            if(board[x][y] > board[nx][ny])
                dp[x][y] += dfs(nx,ny);
        }

        return dp[x][y];
    }

}