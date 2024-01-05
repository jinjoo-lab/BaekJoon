import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int m = 0;
    static int[] board;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        sum = new int[n+1];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + board[i];
        }

        st = new StringTokenizer(bf.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][4];
        int result = 0;

        for(int j=1;j<=3;j++) {

            int start = j * m;

            for (int i = start; i <= n; i++) {
                dp[i][j] = Math.max(dp[i-1][j] , dp[i-m][j-1] + (sum[i] - sum[i - m]));

                if(j == 3){
                    result = Math.max(result,dp[i][j]);
                }
            }
        }

        System.out.println(result);

        bf.close();
    }

}
