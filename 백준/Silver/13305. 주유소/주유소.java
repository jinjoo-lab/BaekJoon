import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[]city = new long[100010];
    static long[]cost = new long[100010];

    static long[] dp = new long[100010];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n-1;i++)
        {
            cost[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            city[i] = Long.parseLong(st.nextToken());
        }

        int idx = 1;
        dp[1] = city[1] * cost[1];

        for(int i=2;i<=n-1;i++)
        {
            if(city[idx] > city[i])
            {
                dp[i] = dp[i-1] + (city[i] * cost[i]);
                idx = i;
            }

            else{
                dp[i] = dp[i-1] + (city[idx] * cost[i]);
            }
        }
        System.out.println(dp[n-1]);

        br.close();
    }
}