import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int h = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] bag = new int[n+1][2];
        int[][] dp = new int[n+1][m+1];


        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");

            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bag[i][0] = w;
            bag[i][1] = v;
        }
        int result = 0;

        for(int i=1;i<=n;i++)
        {
            for(int j=0;j<=m;j++)
            {
                if(j < bag[i][0])
                    dp[i][j] = dp[i-1][j];

                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - bag[i][0]] + bag[i][1]);
                    result = Math.max(result , dp[i][j]);
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}