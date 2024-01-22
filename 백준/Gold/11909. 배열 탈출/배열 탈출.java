import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] board;
    static int[][] dp;

    static int[] dx = {-1,0};
    static int[] dy = {0,-1};

    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        v = new boolean[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[1][1] = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                go(i,j);
            }
        }


        System.out.println(dp[n][n]);

        bf.close();
    }

    static void go(int curX,int curY){

        v[curX][curY] = true;

        for(int i=0;i<2;i++){
            int nx = curX + dx[i];
            int ny = curY + dy[i];

            if(isOut(nx,ny))
                continue;

            if(board[curX][curY] < board[nx][ny]){
                dp[curX][curY] = Math.min(dp[curX][curY],dp[nx][ny]);
            }else{
                int dif = Math.abs(board[curX][curY] - board[nx][ny]) + 1;
                dp[curX][curY] = Math.min(dp[curX][curY],dp[nx][ny] + dif);
            }
        }
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }
}
