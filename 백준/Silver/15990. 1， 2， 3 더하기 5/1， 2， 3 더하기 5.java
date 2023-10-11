import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[][] dp = new long[100001][4];

    static long NUM = 1000000009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        int max  = 3;
        StringBuilder sb = new StringBuilder();

        find();
        for(int i=1;i<=n;i++){
            int cur = Integer.parseInt(br.readLine());

            sb.append(re(cur)+"\n");
        }
        System.out.print(sb);

        br.close();
    }

    static void find(){
        for(int i=4;i<=100000;i++){
            dp[i][1] = ((dp[i-1][2] % NUM) + (dp[i-1][3] % NUM) % NUM);
            dp[i][2] = ((dp[i-2][1] % NUM) + (dp[i-2][3] % NUM) % NUM);
            dp[i][3] = ((dp[i-3][1] % NUM) + (dp[i-3][2] % NUM) % NUM);
        }
    }

    static long re(int cur){
        return (dp[cur][1] + dp[cur][2] + dp[cur][3]) % NUM;
    }
}