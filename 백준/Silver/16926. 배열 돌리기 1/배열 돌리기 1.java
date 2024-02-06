import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row = n;
        int cal = m;

        for(int t=1;t <= Math.min(n,m)/ 2;t++) {

            int len = k % (((row + cal) * 2) - 4);

            turn(t,len);

            row = row -2;
            cal = cal -2;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                sb.append(board[i][j]+" ");
            }sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void turn(int t,int len){

        for(int i=1;i<=len;i++){

            int sx = t;
            int sy = t;

            int tmp = board[sx][sy];

            int idx = 0;

            while(idx < 4){
                int nx = sx + dx[idx];
                int ny = sy + dy[idx];

                if(nx >= t && ny >= t && nx <= n - t + 1 && ny <= m - t + 1){
                    board[sx][sy] = board[nx][ny];
                    sx = nx;
                    sy = ny;
                }else{
                    idx++;
                }
            }
            board[t+1][t] = tmp;
        }

    }
}
