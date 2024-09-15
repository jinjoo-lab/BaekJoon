import java.util.*;
import java.io.*;

public class Main {

    static long a;
    static long b;
    static long[] dp = new long[57];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        dp[0] = 1;

        for(int i = 1 ; i <= 56 ; i++) {
            dp[i] = (dp[i-1] * 2) + (1l << i);
        }

        long countA = count(a - 1);
        long countB = count(b);

        long result = countB - countA;

        System.out.println(result);

        br.close();
    }

    static long count(long cur) {
        long count = cur & 1;
        int len = (int)(Math.log(cur) / Math.log(2));

        for(int i = len ; i >= 1 ; i--) {
            if((cur & (1l << i)) != 0l) {
                count += dp[i-1] + (cur - (1l << i) + 1);
                cur -= (1l << i);
            }
        }

        return count;
    }
}
