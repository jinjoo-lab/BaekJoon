import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n+2];

        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        int l = 1;
        int r = 1;

        long tmpV = 0;

        long[] dp = new long[n+2];

        while(l <= r && r <= n) {
            tmpV += arr[r];

            if(m <= tmpV) {
                while(m <= tmpV) {
                    dp[r] = Math.max(dp[r], dp[l - 1] + (tmpV - m));
                    tmpV -= arr[l];
                    l++;
                }
            }

            dp[r] = Math.max(dp[r],dp[r-1]);
            r++;
        }

        System.out.println(dp[n]);

        br.close();
    }

    static void print(long[] dp) {
        for(int i=1;i<=n + 1;i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }
}
