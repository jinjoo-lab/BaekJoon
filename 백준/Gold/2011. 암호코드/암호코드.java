import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int[] dp = new int[arr.length + 1];

        dp[0] = 1;
        dp[1] = 1;
        
        if(arr[0] == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 2; i <= arr.length; i++) {

            int tmp = arr[i - 1] - '0';
            int past = arr[i - 2] - '0';

            if (tmp >=1 && tmp <= 9) {
                dp[i] += dp[i - 1] % 1000000;
            }


            int tmpNum = past * 10 + tmp;

            if (tmpNum >= 10 && tmpNum <= 26) {
                dp[i] += dp[i - 2] % 1000000;
            }


            dp[i] = dp[i] % 1000000;
        }

        System.out.println(dp[arr.length]);
        br.close();
    }
}

