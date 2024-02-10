import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int open1,open2;

    static int[] use;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        open1 = Integer.parseInt(st.nextToken());
        open2 = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        use = new int[m+1];

        for(int i=1 ; i<= m ; i++){
            use[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[21][n+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][open1][open2] = 0;
        dp[0][open2][open1] = 0;

        int result = Integer.MAX_VALUE;

        for(int k=1; k<=m; k++){
            int curUse = use[k];

            for(int i=1 ; i<=n ; i++){
                for(int j=1 ;j<=n ; j++) {

                    if (dp[k - 1][i][j] == Integer.MAX_VALUE)
                        continue;

                    int minNum = Math.min(i, j);
                    int maxNum = Math.max(i, j);

                    if (curUse == minNum || curUse == maxNum) {
                        dp[k][minNum][maxNum] = Math.min(dp[k][minNum][maxNum], dp[k - 1][i][j]);

                        if (k == m) {
                            result = Math.min(result, dp[k][minNum][maxNum]);
                        }

                        continue;
                    }

                    if (curUse < minNum) {
                        dp[k][curUse][maxNum] = Math.min(dp[k][curUse][maxNum], dp[k - 1][i][j] + Math.abs(curUse - minNum));

                        if (k == m) {
                            result = Math.min(result, dp[k][curUse][maxNum]);
                        }

                    }

                    else if(maxNum < curUse){
                        dp[k][minNum][curUse] = Math.min(dp[k][minNum][curUse],dp[k-1][i][j] + Math.abs(curUse - maxNum));

                        if(k == m){
                            result = Math.min(result,dp[k][minNum][curUse]);
                        }
                    }

                    else {

                        dp[k][minNum][curUse] = Math.min(dp[k][minNum][curUse], dp[k - 1][i][j] + Math.abs(curUse - maxNum));
                        dp[k][curUse][maxNum] = Math.min(dp[k][curUse][maxNum], dp[k - 1][i][j] + Math.abs(curUse - minNum));

                        if (k == m) {
                            result = Math.min(result, Math.min(dp[k][minNum][curUse], dp[k][curUse][maxNum]));
                        }
                    }
                }
            }
        }



        System.out.println(result);
        br.close();
    }

}