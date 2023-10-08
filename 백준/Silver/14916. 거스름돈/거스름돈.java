import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 100000;
        dp[2] = 1;
        dp[3] = 100000;
        dp[4] = 2;
        dp[5] = 1;

        for(int i=6;i<=n;i++){
            int tmp = 100000;

            if(dp[i-5] != 100000){
                tmp = Math.min(tmp,dp[i-5] + 1);
            }

            if(dp[i-2] != -1){
                tmp = Math.min(tmp,dp[i-2] + 1);
            }

            dp[i] = tmp;
        }

        if(dp[n] == 100000){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[n]);
        }
        br.close();
    }
}
