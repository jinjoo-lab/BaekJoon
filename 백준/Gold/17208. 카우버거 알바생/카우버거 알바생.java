import java.util.*;
import java.io.*;

public class Main {

    static int n,a,b;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];
        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine());

            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[n+1][a+1][b+1];


        int result = 0;

        for(int i = 1 ; i <= n ; i++) {
            int curA = board[i][0];
            int curB = board[i][1];

            for(int aa = 0 ; aa <= a ; aa++){
                for(int bb = 0 ; bb <= b ; bb++){
                    dp[i][aa][bb] = dp[i-1][aa][bb];

                    result = Math.max(result, dp[i][aa][bb]);
                }
            }

            for(int tmpA = curA ; tmpA <= a ; tmpA++) {
                for(int tmpB = curB ; tmpB <= b ; tmpB++) {
                    dp[i][tmpA - curA][tmpB - curB] = Math.max(dp[i][tmpA - curA][tmpB - curB],dp[i-1][tmpA][tmpB] + 1);

                    result = Math.max(result,dp[i][tmpA - curA][tmpB - curB]);
                }
            }
        }


        System.out.println(result);
        br.close();
    }
}
