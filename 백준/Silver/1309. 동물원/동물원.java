import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 3;
        dp[2] = 7;
        dp[3] = 17;
        dp[4] = 41;

        for(int i=5;i<=n;i++)
        {
            dp[i] = ((dp[i-1]*2) + dp[i-2]) % 9901;
        }

        System.out.println(dp[n]);
        br.close();
    }
}