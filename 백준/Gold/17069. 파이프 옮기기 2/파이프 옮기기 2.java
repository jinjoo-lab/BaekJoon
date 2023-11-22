import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;

    static int[][] board;
    static long[][][] dp;

    static int[] dx = {0, -1, -1};
    static int[] dy = {-1, 0, -1};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[n + 1][n + 1][3];
        dp[1][2][0] = 1;



        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                garo(i,j);
                sero(i,j);
                crossGo(i,j);

            }
        }

        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
        br.close();
    }


    static boolean cross(int x, int y) {

        if(board[x][y] == 1)
            return false;

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > n)
                return false;

            if (board[nx][ny] == 1)
                return false;
        }


        return true;
    }

    static void garo(int i,int j){
        int nx = i + dx[0];
        int ny = j + dy[0];

        if (nx < 1 || nx > n || ny < 1 || ny > n)
            return;

        if (board[nx][ny] == 0 && board[i][j] == 0) {
            dp[i][j][0] += dp[nx][ny][0];
        }


        if (cross(i, j)) {
            dp[i][j][2] += dp[i+dx[2]][j+dy[2]][0];
        }
    }

    static void sero(int i,int j){
        int nx = i + dx[1];
        int ny = j + dy[1];

        if (nx < 1 || nx > n || ny < 1 || ny > n)
            return;

        if (board[nx][ny] == 0 && board[i][j] == 0)
            dp[i][j][1] += dp[nx][ny][1];


        if (cross(i, j)) {
            dp[i][j][2] += dp[i+dx[2]][j+dy[2]][1];
        }
    }

    static void crossGo(int i,int j){
        for (int k = 0; k <= 1; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 1 || nx > n || ny < 1 || ny > n)
                continue;

            if (board[nx][ny] == 0 && board[i][j] == 0)
                dp[i][j][k] += dp[nx][ny][2];
        }

        if (cross(i, j)) {
            dp[i][j][2] += dp[i+dx[2]][j+dy[2]][2];
        }
    }
}

