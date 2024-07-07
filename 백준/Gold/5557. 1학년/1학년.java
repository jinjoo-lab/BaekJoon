import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int[] board;

    static long[][][] dp = new long[101][101][21];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= n ; i++) {
            int next = Integer.parseInt(st.nextToken());

            board[i] = next;
        }

        dp[1][1][board[1]] = 1;

        for(int i = 2 ; i <= n ; i++) {
            int nextNum = board[i];
            for(int j = 0 ; j <= 20; j++) {
                if(dp[1][i-1][j] > 0) {

                    if(j + nextNum <= 20) {
                        dp[1][i][j + nextNum] += dp[1][i-1][j];
                    }

                    if(j - nextNum >= 0) {
                        dp[1][i][j - nextNum] += dp[1][i-1][j];
                    }
                }
            }
        }

        System.out.println(dp[1][n-1][board[n]]);

        br.close();
    }

    static void print(int l,int r) {
        for (int i = 0; i <= 20; i++) {
            System.out.print(dp[l][r][i] + " ");
        }
        System.out.println();
    }
}
