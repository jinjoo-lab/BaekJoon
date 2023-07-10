import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;

    static int[][] board;

    static boolean[][] visit;
    static int[][] dp;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        visit = new boolean[n+1][n+1];


        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int result = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(dp[i][j]== -1)
                    dfs(i,j);
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result);
        br.close();
    }

    static int dfs(int x,int y)
    {
        if(dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 1;
        int tmp = 0;
        for(int i=0;i<4;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || nx > n || ny < 1 || ny > n)
                continue;

            if(board[nx][ny] > board[x][y]) {
                tmp = Math.max(tmp, dfs(nx, ny));
            }
        }

        return dp[x][y] += tmp;
    }
}
