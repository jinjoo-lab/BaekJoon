import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] input;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        input = new int[n+2];
        for(int i = 1 ; i <= n ; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n+2][n+2];

        for(int i = 0 ; i <= n + 1 ; i++) {
            for(int j = 0 ; j <= n + 1 ; j++) {
                dp[i][j] = -1;
            }
        }

        int result = go(1,1,n);
        System.out.println(result);

       // print();
        br.close();
    }

    static void print() {
        for(int i =1 ; i <= n ; i++) {
            for(int j =1 ; j<= n ;j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int go(int idx,int l,int r) {
        if(l == r) {
            dp[l][r] = idx * input[l];
            return dp[l][r];
        }

        if(dp[l][r] != -1) {
            return dp[l][r];
        }

        dp[l][r] = 0;

        int nl = l + 1;
        int nr = r - 1;

        if(nl <= n && nl <= r) {
            int nC = idx * input[l];
            dp[l][r] = nC + go(idx + 1,nl,r);
        }

        if(nr >= 1 && nr >= l) {
            int nC = idx * input[r];
            dp[l][r] = Math.max(dp[l][r],nC + go(idx + 1 , l, nr));
        }

        return dp[l][r];
    }
}
