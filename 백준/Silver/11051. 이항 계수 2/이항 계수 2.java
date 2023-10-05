import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
            dp[i][1] = i;
        }

        what(n,m);
        System.out.println(dp[n][m]);
        br.close();
    }

    static int what(int n,int c){
        if(n < 1)
            return 0;

        if(dp[n][c] != 0)
            return dp[n][c] % 10007;

        return dp[n][c] = (what(n-1,c-1) + what(n-1,c)) % 10007;
    }

}
