import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;

    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new long[202][202];

        for (int i = 0; i <= n + m; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for (int i = 1; i <= 200; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

                if (dp[i][j] > 1_000_000_000) {
                    dp[i][j] = 1_000_000_001;
                } 
            }
        }

        if (dp[n + m][m] < k) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();

        int tn = n;
        int tm = m;
        int tk = k;

        while (!(tn == 0 && tm == 0)) {
            if (dp[tn + tm - 1][tm] >= tk) {
                sb.append("a");
                tn--;
            } else {
                sb.append("z");
                tk -= dp[tn + tm - 1][tm];
                tm--;
            }
        }

        System.out.println(sb.toString());
        

        br.close();
    }
}
