
import java.util.*;
import java.io.*;

public class Main {
    static long n = 0;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Long.parseLong(st.nextToken());

        int m = 1500_000;
        dp = new int[m+1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2 ; i <= m ; i++){
            fibo(i);
        }

        int tmpIdx = (int)(n % m);

        System.out.println(dp[tmpIdx]);
        br.close();
    }

    static void fibo(int idx){
        dp[idx] = (dp[idx-1] + dp[idx-2]) % 1000_000;
    }
}
