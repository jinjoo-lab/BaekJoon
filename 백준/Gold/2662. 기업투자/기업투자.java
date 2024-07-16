import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[m+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int money = Integer.parseInt(st.nextToken());

            for(int j = 1 ; j <= m ; j++) {
                board[j][money] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m+1];
        ArrayList<int[]>[] list = new ArrayList[n+1];
        for(int i = 0 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }

        int[][] path = new int[n+1][m+1];

        for(int i = 1 ; i <= m ; i++) {
            for(int j = 0 ; j <= n ; j++) {
                for(int k = n - j ; k >= 0 ; k--) {
                   if(dp[k][i-1] + board[i][j] > dp[k+j][i]) {
                       dp[k+j][i] = dp[k][i-1] + board[i][j];

                       path[k+j][i] = j;
                   }
                }
            }
        }


        int[] result = new int[m + 1];

        int start = n;

        for(int i = m ; i >= 1 ; i--) {
            result[i] = path[start][i];

            start -= result[i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n][m]+"\n");
        for(int i = 1 ; i <= m ; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }


}
