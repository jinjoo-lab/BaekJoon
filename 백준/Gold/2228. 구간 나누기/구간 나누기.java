import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n + 1];

        // 1 : 누적합

        for (int i = 1;i<=n;i++){
            int cur = Integer.parseInt(bf.readLine());

            num[i] = num[i-1] + cur;
        }

        int[][] dp = new int[n+1][m+1]; // dp(i,j) = i번째 수까지 j개의 구간으로 나눴을 때 합 max

        for(int i=0 ; i <= n ;i ++){
            for(int j=1;j<=m;j++){
                dp[i][j] = -5000000;
            }
        }

        dp[1][1] = num[1];

        for(int i=2;i<=n;i++){
            for(int k=1;k<=m;k++){

                dp[i][k] = dp[i-1][k]; // 포함되지 않는 경우

                int min = 0;

                if(k == 1)
                    min = -1;

                for(int j = i - 2 ;  j >= min; j --){

                    if(j < 0)
                        dp[i][k] = Math.max(dp[i][k] , num[i]);
                    else
                        dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + (num[i] - num[j + 1]));
                }
            }
        }


        System.out.println(dp[n][m]);

        bf.close();
    }
}
