import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        board = new int[n + 1];

        for(int i = 1; i <= n; i++){
            int cur = Integer.parseInt(st.nextToken()) == 1 ? 1 : -1;
            board[i] = cur;
        }

        int result = 0;
        int[][] dp = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            int v1 = dp[i - 1][0] + board[i];
            int v2 = dp[i - 1][1] + board[i];
            dp[i][0] = Math.max(board[i], v1);
            dp[i][1] = Math.min(board[i], v2);

            result = Math.max(result, Math.max(Math.abs(dp[i][0]),Math.abs(dp[i][1])));
        }
        System.out.println(result);
        br.close();
    }

    static void print(int[] board) {
        for(int i = 1; i <= n; i++){
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
}
