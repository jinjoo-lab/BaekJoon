import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int m = 0;

    static int[][] board;

    // U - R - D - L

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static boolean[][][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new boolean[n+1][m+1][4];


        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine()," ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(board[i][j] == 9){
                    for(int k=0;k<4;k++){
                        v[i][j][k] = true;
                        move(i,j,k);
                    }
                }

            }
        }

        int count = 0;
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if(v[i][j][0] || v[i][j][1] || v[i][j][2] || v[i][j][3]){
                    count += 1;
                }
            }
        }

        System.out.println(count);
        bf.close();
    }

    static void move(int x,int y,int dir){

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 1 || nx > n || ny < 1 || ny > m)
            return;


        if(board[nx][ny] == 1){
            dir = first(dir);

            if(v[nx][ny][dir]){
                return;
            }

            v[nx][ny][dir] = true;
        }else if(board[nx][ny] == 2){
            dir = second(dir);

            if(v[nx][ny][dir]){
                return;
            }

            v[nx][ny][dir] = true;
        }else if(board[nx][ny] == 3){
            dir = third(dir);

            if(v[nx][ny][dir]){
                return;
            }

            v[nx][ny][dir] = true;
        }else if(board[nx][ny] == 4){
            dir = four(dir);

            if(v[nx][ny][dir]){
                return;
            }

            v[nx][ny][dir] = true;
        }else if(board[nx][ny] == 0){

            if(v[nx][ny][dir]){
                return;
            }
            
            v[nx][ny][dir] = true;
        }

        move(nx,ny,dir);
    }

    // U - R - D - L

    static int first(int dir){

        if(dir == 0 || dir == 2)
            return dir;

        if(dir == 1)
            return 3;

        return 1;
    }

    // U - R - D - L

    static int second(int dir){

        if(dir == 1 || dir == 3)
            return dir;

        if(dir == 0)
            return 2;

        return 0;
    }

    // U - R - D - L

    static int third(int dir){
        if(dir == 0){
            return 1;
        }else if(dir == 1){
            return 0;
        }else if(dir == 2){
            return 3;
        }
        return 2;
    }

    // U - R - D - L

    static int four(int dir){
        if(dir == 0){
            return 3;
        }else if(dir == 1){
            return 2;
        }else if(dir == 2){
            return 1;
        }
        return 0;
    }
}
