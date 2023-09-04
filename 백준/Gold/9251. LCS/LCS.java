import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] aArr = br.readLine().toCharArray();
        char[] bArr = br.readLine().toCharArray();

        int aLen = aArr.length;
        int bLen = bArr.length;

        int[][] dp = new int[aLen+1][bLen+1];


        int max = 0;
        for(int i=1;i<=aLen;i++){
            for(int j=1;j<=bLen;j++)
            {
                if(aArr[i-1] == bArr[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;

                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }

                max = Math.max(dp[i][j] , max);
            }
        }

        System.out.println(max);
        br.close();
    }
}
