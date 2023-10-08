import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[] dp = new long[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        int max = 3;
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(br.readLine());

            if(max < tmp){
                go(tmp);
                max = tmp;
            }

            sb.append(dp[tmp]+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void go(int cur){
        for(int i=4;i<=cur;i++){
            dp[i] = ((dp[i-1] % 1000000009) + (dp[i-2] % 1000000009)+ (dp[i-3]% 1000000009)) % 1000000009;
        }
    }
}
