import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static boolean[][] visit;

    static char[][] board;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int result  =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);
            }
        }

        visit = new boolean[n+1][m+1];
        visit[n][1] = true;
        travel(n,1,1);
        System.out.println(result);
        br.close();
    }

    static void travel(int x,int y,int num){

        if(x == 1 && y == m){
            if(num == k)
                result += 1;

            return;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || ny < 1 || nx > n || ny > m)
                continue;

            if(board[nx][ny] == 'T')
                continue;

            if(!visit[nx][ny]){
                visit[nx][ny] = true;
                travel(nx,ny,num+1);
                visit[nx][ny] = false;
            }
        }

    }
}
