import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static boolean[] dp = new boolean[50_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for(int tt = 1 ; tt <= 3 ; tt++) {
            dp = new boolean[50_001];
            n = Integer.parseInt(br.readLine());
            dp[0] = true;

            int sum = 0;

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                sum += (money * count);


                for (int k = 500_00; k >= money; k--) {
                    if(dp[k - money]) {
                        for (int j = 0; j < count; j++) {
                            if(k + (money * j) <= 500_00) {
                                dp[k + (money * j)] = dp[k + (money * j)] || dp[k + (money * j) - money];
                            }
                        }
                    }
                }

            }

            int result = 0;
            if(sum % 2 == 0) {
                result = dp[sum / 2] ? 1 : 0;
            }

            sb.append(result + "\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
