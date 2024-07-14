import java.util.*;
import java.io.*;

public class Main {

    static int tt,n,m;
    static int[] coin = new int[21];
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        tt = Integer.parseInt(st.nextToken());

        for(int t = 1 ; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");
            for(int i = 1 ; i <= n ; i++) {
                coin[i] =  Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine()," ");
            m = Integer.parseInt(st.nextToken());

            dp = new int[m+1];
            dp[0] = 1;

            for(int i = 1 ; i<= n ; i++) {
                int curCoin = coin[i];
                for(int j = 0 ; j <= m ; j++) {

                    if(j + curCoin <= m && j + curCoin >= 0) {
                        dp[j + curCoin] += dp[j];
                    }
                }
            }

            sb.append(dp[m]).append("\n");
        }

        System.out.print(sb);

        br.close();
    }
}
