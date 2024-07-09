import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int count1 = 0;
    static int n,v,result;

    static int[] board;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        result = n;

        int[][] dp = new int[n+1][3];

        dp[1][0] = 1;
        dp[1][1] = board[1];
        dp[1][2] = board[1];

        for(int i =2 ; i <= n ; i++) {
            dp[i][0] = 101;
        }

        for(int i = 2 ; i <= n ; i++) {
            if(i - 1 >= 1) {
                if(dp[i][0] > dp[i-1][0] + 1) {
                    dp[i][0] = dp[i-1][0] + 1;
                    dp[i][1] = Math.max(dp[i - 1][1], board[i]);
                    dp[i][2] = Math.min(dp[i-1][2],board[i]);

                    if(dp[i][1] - dp[i][2] >= v) {
                        result = Math.min(result,dp[i][0]);
                    }
                }else if(dp[i][0] == dp[i-1][0] + 1) {
                    dp[i][1] = Math.max(dp[i - 1][1], board[i]);
                    dp[i][2] = Math.min(dp[i-1][2],board[i]);

                    if(dp[i][1] - dp[i][2] >= v) {
                        result = Math.min(result,dp[i][0]);
                    }
                }
            }

            if(i - 2 >= 1) {
                if(dp[i][0] > dp[i-2][0] + 1) {
                    dp[i][0] = dp[i-2][0] + 1;
                    dp[i][1] = Math.max(dp[i - 2][1], board[i]);
                    dp[i][2] = Math.min(dp[i-2][2],board[i]);

                    if(dp[i][1] - dp[i][2] >= v) {
                        result = Math.min(result,dp[i][0]);
                    }
                }else if(dp[i][0] == dp[i-2][0] + 1) {
                    dp[i][1] = Math.max(dp[i - 2][1], board[i]);
                    dp[i][2] = Math.min(dp[i-2][2],board[i]);

                    if(dp[i][1] - dp[i][2] >= v) {
                        result = Math.min(result,dp[i][0]);
                    }
                }
            }
        }


        System.out.println(result);

        br.close();
    }



}
