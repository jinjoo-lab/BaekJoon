import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new long[10001][4];
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[1][3] = 0;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[2][3] = 0;
        dp[3][1] = 2;
        dp[3][2] = 0;
        dp[3][3] = 1;
        int max = 3;

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(br.readLine());

            if(max < tmp){
                max = tmp;
                find(tmp);
            }

            long sum = dp[tmp][1] + dp[tmp][2] + dp[tmp][3];
            sb.append(sum+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void find(int n){
        for(int i=4;i<=n;i++) {
            dp[i][1] = dp[i-1][1] + dp[i-1][2] + dp[i-1][3];
            dp[i][2] = dp[i-2][2] + dp[i-2][3];
            dp[i][3] = dp[i-3][3];
        }
    }
}
