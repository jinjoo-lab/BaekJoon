import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[41][2];

        for(int i=0;i<=40;i++){
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        dp[0][0] = 1;
        dp[0][1] = 0;

        dp[1][0] = 0;
        dp[1][1] = 1;

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(br.readLine());

            int r1 = findZ(tmp);
            int r2 = findO(tmp);

            sb.append(r1+" "+r2+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int findZ(int x){
        if(dp[x][0] != -1 || x == 0 || x == 1)
            return dp[x][0];

        dp[x][0] = 0;

        return dp[x][0] += findZ(x-1) + findZ(x-2);
    }

    static int findO(int x){
        if(dp[x][1] != -1)
            return dp[x][1];

        dp[x][1] = 0;

        return dp[x][1] += findO(x-1) + findO(x-2);
    }
}
