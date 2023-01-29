import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] t = new int[16];
    static int[] p = new int[16];
    static int[] dp = new int[17];
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++)
        {
            st =  new StringTokenizer(br.readLine()," ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<n;i++)
        {
            if(i+t[i]<=n)
            {
                dp[i+t[i]] = Math.max(dp[i+t[i]],dp[i]+p[i]);
            }
            dp[i+1] = Math.max(dp[i],dp[i+1]);
        }

        System.out.println(dp[n]);
        br.close();
    }

}
