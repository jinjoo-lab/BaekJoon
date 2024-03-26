import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int k = 0;

    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        dp = new long[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        dp[1][1] = 1;

        for(int i = 3 ; i <= n ; i++){
            for(int j = 2 ; j <= k ; j++){
                if(i / 2 < j)
                    continue;

                dp[i][j] = (dp[i - 1][j] + dp[i -2][j -1]) % 1_000_000_003;
            }
        }

        System.out.println(dp[n][k]);

        br.close();
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= k ; j++){
                System.out.print(dp[i][j]+" ");
            } System.out.println();
        }
        System.out.println();
    }
}
