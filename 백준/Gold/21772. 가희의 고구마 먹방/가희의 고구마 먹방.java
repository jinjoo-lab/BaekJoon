import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int t = 0;

    static int[][] board;
    static boolean[][][] v;

    static int result = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        v = new boolean[n][m][n*m];

        int sx = 0;
        int sy = 0;

        for(int i=0;i<n;i++){
            String line = br.readLine();

            for(int j=0;j<m;j++){
                char tmp = line.charAt(j);

                if(tmp == 'G'){
                    sx = i;
                    sy = j;
                }else if(tmp == 'S'){
                    board[i][j] = 1;
                }else if(tmp == '#'){
                    board[i][j] = -1;
                }
            }
        }

        v[sx][sy][0] = true;
        go(sx,sy,0,0);
        System.out.println(result);
        br.close();
    }

    static void go(int x,int y,int num,int cur){

        if(cur == t){
            result = Math.max(result,num);
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            if(board[nx][ny] == -1)
                continue;

            if(!v[nx][ny][num]){
                if(board[nx][ny] == 0){
                    v[nx][ny][num] = true;
                    go(nx,ny,num,cur+1);
                    v[nx][ny][num] = false;
                }else if(board[nx][ny] == 1){
                    v[nx][ny][num+1] = true;
                    board[nx][ny] = 0;
                    go(nx,ny,num+1,cur+1);
                    board[nx][ny] = 1;
                    v[nx][ny][num+1] = false;
                }
            }
        }
    }
}

