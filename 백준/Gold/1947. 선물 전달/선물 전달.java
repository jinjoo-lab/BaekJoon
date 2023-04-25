import java.io.*;
import java.util.*;

public class Main {

    static long[] dp = new long[10000000];

    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 0;
        dp[2] = 1;

        for(int i = 3;i<=n;i++)
        {
            dp[i] = ((dp[i-1]+ dp[i-2])  * (i-1)) % 1000000000;
        }

        System.out.println(dp[n]);
        br.close();
    }
}