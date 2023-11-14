import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        // 열 , 행
        long[][] dp = new long[m + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sc = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());

            //행 , 열

            // 세로 막힘
            if (sc == ec) {
                if(dp[Math.max(sr, er)][sc] == 0 || dp[Math.max(sr, er)][sc] == -2)
                    dp[Math.max(sr, er)][sc] += -1;
            }
            // 가로 막힘
            if (sr == er) {
                if(dp[sr][Math.max(sc, ec)] == 0 || dp[sr][Math.max(sc, ec)] == -1)
                    dp[sr][Math.max(sc, ec)] += -2;
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                if(i ==0 && j ==0)
                    continue;

                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    int tmpC = j - 1;
                    if (tmpC >= 0 && tmpC <= n) {
                        dp[i][j] += dp[i][tmpC];
                    }
                }
                else if(dp[i][j] == -2) {
                    dp[i][j] = 0;
                    int tmpR = i - 1;
                    if (tmpR >= 0 && tmpR <= m) {
                        dp[i][j] += dp[tmpR][j];

                    }
                }
                else if(dp[i][j] == -3){
                    dp[i][j] = 0;
                }
                else{
                    int tmpC = j - 1;
                    if (tmpC >= 0 && tmpC <= n) {
                        dp[i][j] += dp[i][tmpC];
                    }
                    int tmpR = i - 1;
                    if (tmpR >= 0 && tmpR <= m) {
                        dp[i][j] += dp[tmpR][j];

                    }
                }
            }

        }

        System.out.println(dp[m][n]);
        br.close();
    }
}


