import java.util.*;
import java.io.*;

public class Main {

    static int[][][] dp;
    static char[][] board;
    static char[] input;
    static int n,m,k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j ++) {
                board[i][j] = arr[j-1];

            }
        }

        input = br.readLine().toCharArray();

        dp = new int[n+1][m+1][input.length + 1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                for(int a = 0 ; a <= input.length ; a++) {
                    dp[i][j][a] = -1;
                }
            }
        }

        int result = 0;

        for(int i = 1 ; i <= n ; i++) {
            for(int j =1 ; j <= m ; j++) {
                if(board[i][j] == input[0]) {
                    result += go(i,j,0);
                }
            }
        }

        System.out.println(result);

        br.close();
    }


    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int go(int x,int y,int count) {

        if(dp[x][y][count] != -1)
            return dp[x][y][count];

        if(count == input.length - 1) {
            return 1;
        }

        dp[x][y][count] = 0;

        char next = input[count + 1];

        for(int i = 0 ; i < 4 ; i++) {
            for(int num = 1 ; num <= k ; num ++) {
                int nx = x + (dx[i] * num);
                int ny = y + (dy[i] * num);

                if (nx < 1 || nx > n || ny < 1 || ny > m)
                    break;

                if (board[nx][ny] == next) {
                    dp[x][y][count] += go(nx, ny, count + 1);
                }
            }
        }

        return dp[x][y][count];
    }
}
