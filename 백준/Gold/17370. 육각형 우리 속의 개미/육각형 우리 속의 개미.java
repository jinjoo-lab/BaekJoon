import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int result;

    static boolean[][] board = new boolean[55][55];

    static int[] dx = {-1,1,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if(n <= 4) {
            System.out.println(0);
        }else {
            board[23][23] = true;
            go(23,23,-1,0);
            System.out.println(result);
        }

        br.close();
    }

    static int[] startDir = {0,2,3};
    static void go(int x,int y,int dir,int count) {


        if(count >= n) {
            return;
        }
        if(dir == -1) {
            for(int i = 0 ; i<=0 ; i++) {
                int nx = x + dx[startDir[i]];
                int ny = y + dy[startDir[i]];

                if(!board[nx][ny]) {
                    board[nx][ny] = true;
                    go(nx,ny,startDir[i],count);
                    board[nx][ny] = false;
                }
            }
        } else {
            int[] tmpDir = getNextDir(dir);

            for (int i = 0; i <= 1; i++) {
                int nx = x + dx[tmpDir[i]];
                int ny = y + dy[tmpDir[i]];

                if (!board[nx][ny]) {
                    board[nx][ny] = true;
                    go(nx, ny, tmpDir[i], count + 1);
                    board[nx][ny] = false;
                }else if(count == n - 1) {
                    result++;
                }
            }
        }

    }

    static void print() {
        for(int i = 20 ; i <= 25 ; i++) {
            for(int j = 20 ; j<= 25 ; j++) {
                int tmp = board[i][j] ? 1 : 0;
                System.out.print(tmp+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] getNextDir(int cur) {
        int[] dir = new int[2];

        if(cur == 0) {
            dir[0] = 5;
            dir[1] = 4;
        } else if(cur == 1) {
            dir[0] = 3;
            dir[1] = 2;
        } else if(cur == 2) {
            dir[0] = 1;
            dir[1] = 4;
        } else if(cur == 3) {
            dir[0] = 1;
            dir[1] = 5;
        } else if(cur == 4) {
            dir[0] = 0;
            dir[1] = 2;
        } else {
            dir[0] = 0;
            dir[1] = 3;
        }

        return dir;
    }
}
