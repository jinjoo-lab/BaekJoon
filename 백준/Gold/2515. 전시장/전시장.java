import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[][] board;

    static long[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());

            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board,1,n+1,(x,y) -> {
            if(x[0] == y[0])
                return y[1] - x[1];

            return x[0] - y[0];
        });

        dp = new long[n + 1][2];
        // 잡을 때
        // 안잡을 때

        dp[1][0] = board[1][1];
        int tmpIdx = 1;


        for(int i = 2 ; i <= n ; i++) {
            int curLen = board[i][0];
            int curV = board[i][1];

            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = dp[i][0];

            while(board[tmpIdx][0] <= curLen - m) {
                tmpIdx++;
            }
            tmpIdx -= 1;

            dp[i][0] = Math.max(dp[i][0],Math.max(dp[tmpIdx][0],dp[tmpIdx][1]) + curV);
        }

        System.out.println(Math.max(dp[n][0],dp[n][1]));

        br.close();
    }
}

