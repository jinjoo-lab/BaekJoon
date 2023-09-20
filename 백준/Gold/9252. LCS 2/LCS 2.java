import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();
        char[] brr = br.readLine().toCharArray();

        int aLen = arr.length;
        int bLen = brr.length;

        int[][] dp = new int[aLen+1][bLen+1];
        int max = 0;
        int iIdx = 0;
        int jIdx = 0;

        for(int i=1;i<=aLen;i++){
            for(int j=1;j<=bLen;j++) {
                if (arr[i-1] == brr[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

                if(max < dp[i][j]){
                    max = dp[i][j];
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        iIdx = aLen;
        jIdx = bLen;

        while(iIdx > 0 && jIdx > 0){
            if(dp[iIdx][jIdx] == dp[iIdx-1][jIdx]){
                iIdx--;
            }
            else if(dp[iIdx][jIdx] == dp[iIdx][jIdx-1]){
                jIdx--;
            }
            else{
                sb.append(arr[iIdx-1]);
                iIdx--;
                jIdx--;
            }
        }
        System.out.println(max);
        System.out.println(sb.reverse());
        br.close();
    }

    static void print(int[][] dp,int n,int m){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
