import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[] board;
    static int[] tmp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0)
                break;

            board = new int[n + 1];
            tmp = new int[m + 1];

            int[] dp = new int[m + 1];

            for (int i = 1; i <= n; i++) {

                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= m; j++) {
                    tmp[j] = Integer.parseInt(st.nextToken());
                }

                dp[1] = tmp[1];

                for (int j = 2; j <= m; j++) {
                    dp[j] = Math.max(dp[j - 1], dp[j - 2] + tmp[j]);
                }

                board[i] = dp[m];
            }

            int[] result = new int[n + 1];

            result[1] = board[1];

            for (int i = 2; i <= n; i++) {
                result[i] = Math.max(result[i - 1], result[i - 2] + board[i]);
            }

            sb.append(result[n]+"\n");
        }

        System.out.print(sb);

        br.close();
    }
}
