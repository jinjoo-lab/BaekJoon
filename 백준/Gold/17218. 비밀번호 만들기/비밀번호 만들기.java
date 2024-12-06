import java.util.*;
import java.io.*;

public class Main {

    static char[] a;
    static char[] b;

    static int aLen, bLen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        aLen = a.length;
        bLen = b.length;

        int[][] dp = new int[aLen + 1][bLen + 1];

        int max = 0;

        for(int i = 1 ; i <= aLen ; i++) {
            for(int j = 1 ; j <= bLen ; j++) {
                char tmpA = a[i-1];
                char tmpB = b[j-1];

                if(tmpA == tmpB) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

                max = Math.max(max,dp[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();

        int startA = aLen;
        int startB = bLen;

        while(startA > 0 && startB > 0) {
            if(a[startA - 1] == b[startB - 1]) {
                sb.append(a[startA - 1]);
                startA -= 1;
                startB -= 1;
            }else if(dp[startA][startB] == dp[startA - 1][startB]) {
                startA -= 1;
            }else {
                startB -= 1;
            }
        }

        System.out.println(sb.reverse());

        br.close();
    }

}
