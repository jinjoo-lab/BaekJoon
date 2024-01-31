import java.util.*;
import java.io.*;
public class Solution {

    static int n = 0;
    static int m = 0;

    static int t = 0;

    static int[][] board;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a <= t ; a++){
            st = new StringTokenizer(bf.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];

            int sx = 1;
            int sy = 1;
            int dir = 0;
            int nx = 0;
            int ny = 0;
            int idx = 2;
            board[sx][sy] = 1;

            while(idx <= n * n){
                nx = sx + dx[dir];
                ny = sy + dy[dir];

                if(nx < 1 || nx > n || ny < 1 || ny > n) {
                    dir = nextDir(dir);
                    continue;
                }

                if(board[nx][ny] != 0) {
                    dir = nextDir(dir);
                    continue;
                }


                sx = nx;
                sy = ny;
                board[nx][ny] = idx++;

            }

            sb.append("#"+a+"\n");
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    sb.append(board[i][j]+" ");
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
        bf.close();
    }

    static int nextDir(int cur){
        if(cur == 3)
            return 0;

        return cur + 1;
    }
}

