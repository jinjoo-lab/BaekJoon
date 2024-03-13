
import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] dx = {0,0,-1,1,1,1,-1,-1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};
    static int[][] board;

    static int[][][] dp;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];
        dp = new int[n+1][m+1][2];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(!visit[i][j]){
                    dfs(i,j);
                }
            }
        }

        int[][] result = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                result[dp[i][j][0]][dp[i][j][1]] += 1;
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                sb.append(result[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int[] dfs(int x,int y){

        if(visit[x][y]){
            return dp[x][y];
        }

        visit[x][y] = true;

        int idx = find(x,y);

        if(idx == -1){
            dp[x][y][0] = x;
            dp[x][y][1] = y;
        }else{
            int[] tmp = dfs(x + dx[idx],y + dy[idx]);
            dp[x][y][0] = tmp[0];
            dp[x][y][1] = tmp[1];
        }

        return dp[x][y];
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static int find(int x,int y){
        int idx = - 1;
        int v = board[x][y];

        for(int i = 0 ; i < 8 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];


            if(isOut(nx,ny))
                continue;

            if(board[nx][ny] < v){
                v = board[nx][ny];
                idx = i;
            }
        }

        return idx;
    }
}
