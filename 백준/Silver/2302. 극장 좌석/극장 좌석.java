import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dp = new int[42];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3;i<=n;i++)
        {
            dp[i] = dp[i-1] + dp[i-2];
        }

        int result = 1;

        int idx = 1;
        for(int i=1;i<=m;i++)
        {
            int pt = Integer.parseInt(br.readLine());

            result *= dp[pt - idx];
            idx = pt+1;
        }

        result *= dp[n+1 - idx];

        if(m==0)
            System.out.println(dp[n]);
        else
            System.out.println(result);
        br.close();
    }

}