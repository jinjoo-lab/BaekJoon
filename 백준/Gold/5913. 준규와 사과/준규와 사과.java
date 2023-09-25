import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] board = new int[6][6];
    static boolean[][] v = new boolean[6][6];

    static int result = 0;

    static int all = 25;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        all -= n +1;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
        }

        v[1][1] = true;
        board[1][1] = 3;
        v[5][5] = true;
        board[5][5] = 4;

        travel(1,1,5,5,1,1);
        System.out.println(result);
        br.close();
    }

    static boolean last(int fx,int fy,int sx,int sy){
        for(int i=0;i<4;i++){
            int nx = fx + dx[i];
            int ny = fy + dy[i];

            if(nx < 1 || nx > 5 || ny < 1 || ny > 5)
                continue;

            if(board[nx][ny] != 0)
                continue;

            if(board[nx][ny] == 0){
                for(int j=0;j<4;j++){
                    int n2x = sx + dx[j];
                    int n2y = sy + dy[j];

                    if(n2x < 1 || n2x > 5 || n2y < 1 || n2y > 5)
                        continue;

                    if(board[n2x][n2y] != 0)
                        continue;

                    if(board[n2x][n2y] == 0){
                        if(nx == n2x && ny == n2y)
                            return true;
                    }
                }
            }
        }

        return false;
    }

    static void travel(int fx,int fy,int sx,int sy,int fnum,int snum){
        if(fnum + snum == all) {
            if(last(fx,fy,sx,sy)) {
                result = result + 1;
            }
            return;
        }

        for(int i=0;i<4;i++){
            int nx = fx + dx[i];
            int ny = fy + dy[i];

            if(nx < 1 || nx > 5 || ny < 1 || ny > 5)
                continue;

            if(board[nx][ny] != 0)
                continue;

            if(!v[nx][ny]) {
                v[nx][ny] = true;
                board[nx][ny] = 3;
                for (int j = 0; j < 4; j++) {
                    int n2x = sx + dx[j];
                    int n2y = sy + dy[j];

                    if (n2x < 1 || n2x > 5 || n2y < 1 || n2y > 5)
                        continue;

                    if(board[n2x][n2y] != 0)
                        continue;

                    if(!v[n2x][n2y]){
                        v[n2x][n2y] = true;
                        board[n2x][n2y] = 4;
                        travel(nx,ny,n2x,n2y,fnum+1,snum+1);
                        v[n2x][n2y] = false;
                        board[n2x][n2y] = 0;
                    }
                }
                board[nx][ny] = 0;
                v[nx][ny] = false;
            }
        }
    }

    static void print(){
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}