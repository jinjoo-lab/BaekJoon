import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[] dp = new long[50001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i=4;i<=n;i++){
            dp[i] = i;

            for(int j=1;j*j<=i;j++){
                if(dp[i] > dp[i - j*j] + 1){
                    dp[i] = dp[i-j*j] + 1;
                }
            }
        }
        System.out.println(dp[n]);
        br.close();
    }
}
