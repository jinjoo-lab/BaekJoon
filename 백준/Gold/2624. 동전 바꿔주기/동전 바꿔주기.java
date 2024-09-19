import java.util.*;
import java.io.*;

public class Main {

    static int t,n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        int[] dp = new int[t + 1];

        dp[0] = 1;

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for(int curT = t ; curT >= 0 ; curT--) {
                for(int j = 1 ; j <= c ; j++) {
                    if(curT - (v * j) >= 0 && dp[curT - (v * j)] > 0) {
                        dp[curT] += dp[curT - (v * j)];
                    }
                }
            }
        }

        System.out.println(dp[t]);

        br.close();
    }
}
