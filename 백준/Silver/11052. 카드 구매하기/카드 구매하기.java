import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] board = new int[n+1];

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1];
        dp[1] = board[1];

        for(int i=2;i<=n;i++){
            dp[i] = Math.max(dp[i],board[i]);

            for(int j=i-1;j>=1;j--){
                dp[i] = Math.max(dp[i],dp[i-j] + dp[j]);
            }
        }

        System.out.println(dp[n]);
        br.close();
    }
}
