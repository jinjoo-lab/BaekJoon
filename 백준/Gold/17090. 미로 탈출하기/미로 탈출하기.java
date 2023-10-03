import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static char[][] board;
    static int[][] dp;

    static boolean[][] v;

    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0}; // R,L,U,D

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        dp = new int[n+1][m+1];
        v = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();
            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);
                dp[i][j] = -1;
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i][j] == -1){
                    move(i,j);
                }
            }
        }

        int num = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i][j] == 1)
                    num += 1;
            }
        }

        System.out.println(num);
        br.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int dir(char cur){
        if(cur == 'R'){
            return 0;
        }else if(cur == 'L'){
            return 1;
        }else if(cur == 'U'){
            return 2;
        }else{
            return 3;
        }
    }

    static int move(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m) {
            return 1;
        }

        if(dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 0;

        int d = dir(board[x][y]);

        return dp[x][y] = move(x + dx[d],y + dy[d]);
    }
}
