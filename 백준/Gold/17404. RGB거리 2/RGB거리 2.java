import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] arr;

    static int MAX = 1000 * 1000 + 1;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n+1][4];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=3;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = MAX;

        for(int i=1;i<=3;i++){
            result = Math.min(result,search(i));
        }

        System.out.println(result);
    }

    static int search(int num){
        int[][] dp = new int[n+1][4];

        dp[1][num] = arr[1][num];

        for(int i=1;i<=3;i++){
            if(dp[1][i] == 0)
                dp[1][i] = MAX;
        }

        for(int i=2;i<n;i++){
            dp[i][1] = Math.min(dp[i-1][2] , dp[i-1][3]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][1] , dp[i-1][3]) + arr[i][2];
            dp[i][3] = Math.min(dp[i-1][1] , dp[i-1][2]) + arr[i][3];
        }

        if(num == 1){
            dp[n][1] = Integer.MAX_VALUE;
            dp[n][2] = Math.min(dp[n-1][1] , dp[n-1][3]) + arr[n][2];
            dp[n][3] = Math.min(dp[n-1][1] , dp[n-1][2]) + arr[n][3];
        }else if(num == 2){
            dp[n][1] = Math.min(dp[n-1][2] , dp[n-1][3]) + arr[n][1];
            dp[n][2] = Integer.MAX_VALUE;
            dp[n][3] = Math.min(dp[n-1][1] , dp[n-1][2]) + arr[n][3];
        }else{
            dp[n][1] = Math.min(dp[n-1][2] , dp[n-1][3]) + arr[n][1];
            dp[n][2] = Math.min(dp[n-1][1] , dp[n-1][3]) + arr[n][2];
            dp[n][3] = Integer.MAX_VALUE;
        }


        return Math.min(Math.min(dp[n][1],dp[n][2]),dp[n][3]);
    }
}

