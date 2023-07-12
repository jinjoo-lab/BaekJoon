import java.util.*;
import java.io.*;

public class Main {

    static int tc = 0;
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        tc = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=tc;i++)
        {
            n = Integer.parseInt(br.readLine());
            int[] coin = new int[n+1];

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                coin[j] = Integer.parseInt(st.nextToken());
            }

            m = Integer.parseInt(br.readLine());

            sb.append(search(coin));
        }

        System.out.println(sb);

        br.close();
    }

    static String search(int[] coin)
    {
        int[][] dp = new int[n+1][m+1];

        for(int i=0;i<=n;i++)
            dp[i][0] = 1; // 0원을 만드는 방법 : 1

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                int tmp = coin[i];

                while(tmp <= j) {
                    dp[i][j] += dp[i - 1][j - tmp];

                    tmp = tmp + coin[i];
                }

                dp[i][j] += dp[i - 1][j];
            }
        }

        return dp[n][m]+"\n";
    }

    static void print(int[][] dp)
    {
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=m;j++)
            {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
