import java.util.*;
import java.io.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int aLen = a.length;
        int bLen = b.length;

        int[][] dp = new int[aLen+1][bLen+1];
        int max = 0;
        for(int i=1;i<=aLen;i++){
            for(int j=1;j<=bLen;j++){

                if(a[i-1] == b[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }

                max = Math.max(max,dp[i][j]);
            }
        }

        System.out.println(max);

        br.close();
    }
}