import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] bag = new int[m+1][2];
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            bag[i][0] = Integer.parseInt(st.nextToken());
            bag[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[m+1][n+101];

        int max = 1000000;

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n+100;j++){
                dp[i][j] = 1000000;

                if(j % bag[i][1] == 0){
                    dp[i][j] = bag[i][0] * (j/bag[i][1]);
                }

                if(j >= n) {
                    max = Math.min(max, dp[i][j]);
                }
            }
        }
        for(int i=2;i<=m;i++){
            for(int j=1;j<=n+100;j++){
                dp[i][j] = Math.min(dp[i][j],dp[i-1][j]);

                if(j >= bag[i][1]){
                    dp[i][j] = Math.min(dp[i][j - bag[i][1]] + bag[i][0],Math.min(dp[i][j],dp[i-1][j - bag[i][1]] + bag[i][0]));
                }

                if(j >= n){
                    max= Math.min(dp[i][j],max);
                }
            }
        }
/*
        int tmp = 2;
        while(tmp*j <= n){
            dp[i][j*tmp] = dp[i][j] * tmp;
            tmp = tmp + 1;
        }*/
        System.out.println(max);
        br.close();
    }
}
