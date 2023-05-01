import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;
    static int[] dp = new int[100001];
    static int[] num = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1;i<=k;i++)
        {
            dp[i] = 10000000;
        }

        for(int i=1;i<=n;i++)
        {
            dp[num[i]] = 1;
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=num[i];j<=k;j++)
            {
                dp[j] = Math.min(dp[j] , dp[j - num[i]] + 1);
            }
        }

        if(dp[k]==10000000)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
        br.close();
    }
}