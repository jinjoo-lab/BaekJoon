import java.util.*;
import java.io.*;

public class Main {

    static char[] chars;
    static int n;
    static String arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine();
        chars = arr.toCharArray();
        n = arr.length();

        dp = new int[n+1][n+1];

        String tmp;


        int result = 0;

        for(int i = 0 ; i < n - 1 ; i++) {
            tmp = arr.substring(i,i+2);

            if(tmp.equals("at") || tmp.equals("gc")) {
                dp[i][i+1] = 2;
                
                result = 2;
            }
        }

        for(int k = 2; k < n ; k++) {
            for(int i = 0 ; i < n - k ; i++) {
                int len = i + k;

                int semiLen = 0;

                if(chars[i] == 'a' && chars[i+k] == 't')
                    semiLen = 2;

                if(chars[i] == 'g' && chars[i+k] == 'c')
                    semiLen = 2;

                for(int j = i + 1; j < len ; j++) {

                    dp[i][len] = Math.max(dp[i][len],dp[j][len - 1] + semiLen);
                }

                for(int j = i ; j < len ;j++) {
                    dp[i][len] = Math.max(dp[i][len], dp[i][j] + dp[j+1][len]);
                }

                result = Math.max(result,dp[i][len]);
            }
        }


        System.out.println(result);
        br.close();
    }
}
