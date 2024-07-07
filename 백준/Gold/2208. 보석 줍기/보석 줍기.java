import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n,m;

    static int[] board;
    static int[] sum;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        sum = new int[n+1];
        dp = new int[n+1];
        
        for(int i = 1 ; i <= n ; i++) {
            board[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i-1] + board[i];
        }

        dp[m] = sum[m];
        int result = dp[m];

        for(int i = m + 1 ; i <= n ; i++) {
            int tmpNum = sum[i] - sum[i - m];

            dp[i] = Math.max(dp[i-1] + board[i] , tmpNum);

            result = Math.max(result,dp[i]);
        }

        if(result < 0)
            result = 0;

        System.out.println(result);

        br.close();
    }
}
