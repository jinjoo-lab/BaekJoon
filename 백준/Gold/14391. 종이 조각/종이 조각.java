import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[][] board;
    static int[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();
            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = arr[j-1] - '0';
            }
        }

        v = new boolean[n+1][m+1];
        go(1,1);

        System.out.println(result);
        br.close();
    }

    static int result = 0;
    static boolean[][] v;

    static void go(int x,int y) {

        if(y > m) {
            y = 1;
            x += 1;
        }

        if(x > n) {
            result = Math.max(result,getSum());
            return;
        }

        v[x][y] = true;
        go(x,y + 1);
        v[x][y]  =false;
        go(x, y + 1);
    }

    static int getSum() {
        int result = 0;
        int tmp = 0;

        for(int i = 1 ; i <= n ; i++) {
            tmp = 0;

            for(int j = 1 ; j <= m ; j++) {
                if(v[i][j]) {
                    tmp *= 10;
                    tmp += board[i][j];
                }else {
                    result += tmp;
                    tmp = 0;
                }
            }

            result += tmp;
        }

        for(int j = 1 ; j <= m ; j++) {
            tmp = 0;

            for(int i = 1 ; i <= n ; i++) {
                if(!v[i][j]) {
                    tmp *= 10;
                    tmp += board[i][j];
                }else {
                    result += tmp;
                    tmp = 0;
                }
            }

            result += tmp;
        }

        return result;
    }
}

