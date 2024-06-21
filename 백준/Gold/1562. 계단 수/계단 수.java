import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][][] dp;

    static int MOD = 1_000_000_000;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        dp = new int[n+1][11][1 << 10];

        for(int i = 1 ; i < 10 ; i++) {
            dp[1][i][1 << i] = 1;
        }

        for(int i = 2 ; i <= n ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                for(int m = 0; m < (1 << 10) ; m++) {
                    if(j > 0) {
                        dp[i][j][m | (1 << j)] += dp[i-1][j-1][m];
                        dp[i][j][m | (1 << j)] %= MOD;
                    }

                    if(j < 9) {
                        dp[i][j][m | (1 << j)] += dp[i-1][j+1][m];
                        dp[i][j][m | (1 << j)] %= MOD;
                    }
                }
            }
        }

        int result = 0;

        for(int i = 0 ; i < 10 ; i++) {
            result += (dp[n][i][(1 << 10) - 1]);
            result %= MOD;
        }

        System.out.println(result);
        br.close();
    }
}
