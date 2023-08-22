import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0)
                break;

            board = new int[n + 1][m + 1];
            int[][] dp = new int[n + 2][m + 2];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                for (int j = 1; j <= m; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (board[i][j] == 1) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;

                        max = Math.max(max, dp[i][j]);
                    }
                }
            }

            sb.append(max+"\n");

        }
        System.out.println(sb);
        br.close();
    }
}