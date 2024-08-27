import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                dp[i][j] = -1;
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(dp[i][j] == -1) {
                    go(i,j);
                }
            }
        }

        int result = getMax();

        System.out.println(result);

        br.close();
    }

    static int getMax() {

        int max = 0;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                max = Math.max(max,dp[i][j]);
            }
        }

        return max;
    }

    static int go(int x,int y) {
        if(dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 1;

        int max = 0;

        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOut(nx,ny))
                continue;

            if (board[nx][ny] > board[x][y]) {
                max = Math.max(max,go(nx,ny));
            }
        }

        return dp[x][y] += max;
    }

    static int[][] dp;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n) {
            return true;
        }

        return false;
    }
}
