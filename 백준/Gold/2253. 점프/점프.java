import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] d = {-1,0,1};
    static int[] cannot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cannot = new int[n+1];
        for(int i=1;i<=m;i++){
            int tmp = Integer.parseInt(br.readLine());
            cannot[tmp] = -1;
        }
        int[][] dp = new int[n+1][(n/2) + 2];

        dp[2][1] = 1;
        for(int i=2;i<n;i++){

            if(cannot[i] == -1)
                continue;

            for(int j= i/2;j>=1;j--){

                if(dp[i][j] == 0)
                    continue;

                for(int k =0;k<3;k++){
                    int nd = j + d[k];

                    if(nd < 1)
                        continue;

                    if(i + nd > n || cannot[i+nd] == -1)
                        continue;

                    if(dp[i+nd][nd] == 0)
                        dp[i+nd][nd] = dp[i][j] + 1;

                    else{
                        dp[i+nd][nd] = Math.min(dp[i+nd][nd] , dp[i][j] + 1);
                    }
                }
            }
        }

        int min = -1;
        for(int i=1;i<=n/2+1;i++){

            if(dp[n][i] != 0)
            {
                if(min == -1)
                    min = dp[n][i];

                else{
                    min = Math.min(min, dp[n][i]);
                }
            }

        }

        System.out.println(min);
        br.close();
    }

    static void print(int[][] dp){
        for(int i=2;i<=n;i++){
            for(int j=1;j<=n/2;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
