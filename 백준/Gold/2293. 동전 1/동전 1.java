import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;

    static int[][] dp = new int[101][10001];
    static int[] num = new int[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            num[i] = Integer.parseInt(br.readLine());
        }


        for(int i=0;i<=k;i++)
        {
            if(num[1]*i<=k)
                dp[1][num[1]*i] = 1;

            else
                break;
        }

        for(int i=2;i<=n;i++)
        {
            for(int j=0;j<=k;j++)
            {
                if(j-num[i]>=0)
                    dp[i][j] += dp[i-1][j] + dp[i][j-num[i]];

                else
                    dp[i][j] += dp[i-1][j];

            }
        }

        System.out.println(dp[n][k]);
        br.close();
    }

    static void print(int j)
    {
        for(int i=1;i<=k;i++)
        {
            System.out.print(dp[j][i]+" ");
        }
        System.out.println();
    }
}