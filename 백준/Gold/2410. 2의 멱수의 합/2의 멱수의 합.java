import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 4;

        for(int i=5;i<=n;i++)
        {
            if(i%2==0)
            {
                dp[i] = (dp[i-1] + dp[i/2]) % 1000000000;
            }

            else{
                dp[i] = dp[i-1];
            }
        }

        System.out.println(dp[n]);
        br.close();
    }
}
