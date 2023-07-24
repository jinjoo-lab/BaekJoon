import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        long[] dp = new long[n+5];

        dp[1] = 0;
        dp[2] = 1;

        for(int i=3;i<=n;i++)
        {
            if(i%2==0)
            {
                dp[i] = ((dp[i-1] * i) % 1000000000 + 1) % 1000000000;
            }

            else
                dp[i] = ((dp[i-1] * i) % 1000000000 - 1) % 1000000000;
        }

        System.out.println(dp[n]);
        br.close();
    }
}
