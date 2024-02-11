import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        long[] dp = new long[100001];

        dp[0] = 1;
        dp[2] = 1;
        
        for(int i = 4 ; i <= n; i+=2){

            long tmp = 0;

            for(int j=0; j<=i-2; j+=2){
                tmp += ((dp[j] % 987654321l) * (dp[i - j-2] % 987654321l)) % 987654321l;

            }

            tmp %= 987654321l;

            dp[i] = tmp;
        }

        System.out.println(dp[n]);
        br.close();
    }
}
