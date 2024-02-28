
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dp = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        int result  = 0;

        for(int i = 1 ; i <= n ; i++){
            int w = board[i][0];
            int h = board[i][1];

            for(int j = m ; j >= 0 ; j --){
                if(j - w >= 0){
                    dp[j] = Math.max(dp[j] , dp[j - w] + h);
                }

                result = Math.max(result,dp[j]);
            }
        }

        System.out.println(result);
        br.close();
    }

}
