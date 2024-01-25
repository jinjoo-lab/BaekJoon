import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int t = 0;

    static int[] board;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a <= t ; a++){
            st = new StringTokenizer(bf.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1];
            dp = new int[n+1][n+1];

            st = new StringTokenizer(bf.readLine(), " ");
            for(int i = 1; i <= n ;i++){
                board[i] = board[i-1] + Integer.parseInt(st.nextToken());
            }


            for(int des = 1 ; des < n ; des ++){
                for(int start = 1; start + des <= n ; start ++){
                    int end = start + des;

                    dp[start][end] = Integer.MAX_VALUE;

                    for(int tmp = start; tmp < end; tmp++){
                        dp[start][end] = Math.min(dp[start][end],dp[start][tmp] + dp[tmp+1][end] + (board[end] - board[start -1]));
                    }
                }
            }

            sb.append(dp[1][n]+"\n");
        }

        System.out.print(sb);
        bf.close();
    }
}




