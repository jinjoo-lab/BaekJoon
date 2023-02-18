import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board = new int[500][500];
    static double[][] sand = {{0, 0, 0.02, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0.05, 0, 0, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0, 0, 0.02, 0, 0}};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int result = 0;
    static int sx = 0;
    static int sy = 0;
    static double[][] tmp = new double[7][7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sd = 0;
        if(n%2==0)
        {
            sx = n/2;sy = n/2;
        }
        else{
            sx = n/2 + 1;sy = n/2 + 1;
        }

        int fc = 1;
        int sc = 2;
        boolean tmp = true;
        while(true)
        {
            tmp= sand(0,fc);
            if(!tmp)
                break;
            tmp=sand(1,fc);
            if(!tmp)
                break;
            tmp=sand(2,sc);
            if(!tmp)
                break;
            tmp=sand(3,sc);
            if(!tmp)
                break;
            fc= fc+2;
            sc = sc+2;
        }
        System.out.println(result);
        br.close();
    }


    static int change(int dir)
    {
        if(dir==3)
            return 0;
        else
            return dir+1;
    }

    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    static boolean sand(int dir,int count)
    {
        turn(dir);
        for(int k=1;k<=count;k++) {
            sx = sx + dx[dir];
            sy = sy + dy[dir];
            int cur = board[sx][sy];
            int rest = cur;
            board[sx][sy] = 0;
            int nx = sx - 2;
            int ny = sy - 2;
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int s = (int) (cur * tmp[i][j]);
                    rest = rest - s;
                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                        board[nx][ny] = board[nx][ny] + s;
                    } else {
                        result += s;
                    }
                    ny++;
                }
                ny = sy - 2;
                nx++;
            }

            int rx = sx + dx[dir];
            int ry = sy + dy[dir];
            if (rx >= 1 && rx <= n && ry >= 1 && ry <= n) {
                board[rx][ry] = board[rx][ry] + rest;
            } else {
                result += rest;
            }

            if(sx==1&&sy==1)
                return false;

        }

        return true;
    }

    static void turn(int dir) {
        if (dir == 0) {
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    tmp[i][j] = sand[i - 1][j - 1];
                }
            }
        }

        if (dir == 1) {
            int nx = 0;
            int ny = 4;
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    tmp[i][j] = sand[nx][ny];
                    nx++;
                }
                nx = 0;
                ny--;
            }
        }

        if(dir==2)
        {
            for (int i = 1; i <= 5; i++) {
                int ny = 4;
                for (int j = 1; j <= 5; j++) {
                    tmp[i][j] = sand[i-1][ny];
                    ny--;
                }
            }
        }

        if(dir==3)
        {
            int nx = 0;
            int ny = 0;
            for (int i = 1; i <= 5; i++) {

                for (int j = 1; j <= 5; j++) {
                    tmp[i][j] = sand[nx][ny];
                    nx++;
                }
                nx=0;
                ny++;
            }
        }
    }
}