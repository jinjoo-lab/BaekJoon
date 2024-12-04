import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] men;
    static int[] women;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        men = new int[n];
        women = new int[m];

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < n; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < m; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(men);
        Arrays.sort(women);

        int[][] dp = new int[n][m];

        if(n <= m) {

            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < m ; j++) {
                    dp[i][j] = 2_000_001;
                }
            }

            for(int i = 0 ; i < m ; i++) {
                dp[0][i] = getMinus(men[0], women[i]);
            }

            for(int i = 1 ; i < n ; i++) {
                for(int j = i ; j < m ; j++) {

                    int minNum = 2000_001;
                    for(int k = 0 ; k < j ; k++) {
                        minNum = Math.min(minNum, dp[i-1][k]);
                    }

                    dp[i][j] = Math.min(dp[i][j], getMinus(men[i],women[j]) +  minNum);
                }
            }

            int result = dp[n-1][n-1];
            for(int i = n ; i < m ; i++) {
                result = Math.min(result,dp[n-1][i]);
            }

            System.out.println(result);
        }else {

            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < m ; j++) {
                    dp[i][j] = 2_000_001;
                }
            }

            for(int i = 0 ; i < m ; i++) {
                dp[i][0] = getMinus(men[i], women[0]);
            }

            for(int i = 1 ; i < m ; i++) {
                for(int j = i ; j < n ; j++) {

                    int minNum = 2000_001;
                    for(int k = 0 ; k < j ; k++) {
                        minNum = Math.min(minNum, dp[k][i-1]);
                    }
                    dp[j][i] = Math.min(dp[j][i], getMinus(men[j],women[i]) +  minNum);
                }
            }

            int result = dp[m-1][m-1];
            for(int i = m ; i < n ; i++) {
                result = Math.min(result,dp[i][m-1]);
            }

            System.out.println(result);
        }


        br.close();
    }

    static void print(int[][] dp) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int getMinus(int curM, int curW) {
        return Math.abs(curM - curW);
    }
}
