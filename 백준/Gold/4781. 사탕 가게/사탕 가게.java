import java.util.*;
import java.io.*;

public class Main {

    static int[] dp = new int[10001];

    static int n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken().trim());
            m = (int) (Double.parseDouble(st.nextToken().trim()) * 100 + 0.5);

            if(n == 0)
                break;

            dp = new int[10001];
            int result = 0;

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int v = Integer.parseInt(st.nextToken().trim());
                int w = (int) (Double.parseDouble(st.nextToken().trim()) * 100 + 0.5);

                for (int j = 0; j <= m; j++) {
                    if (j + w <= m) {
                        dp[j + w] = Math.max(dp[j + w], dp[j] + v);

                        result = Math.max(result, dp[j + w]);
                    }
                }
            }

            sb.append(result+"\n");
        }

        System.out.print(sb);

        br.close();
    }
}
