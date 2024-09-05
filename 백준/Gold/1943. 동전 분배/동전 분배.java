import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static boolean[] dp = new boolean[50_001];
    static int[] coin;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for(int tt = 1 ; tt <= 3 ; tt++) {
            dp = new boolean[50_001];
            n = Integer.parseInt(br.readLine());
            dp[0] = true;

            coin = new int[n+1];
            num = new int[n+1];

            int sum = 0;

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                coin[i] = money;
                num[i] = count;
                sum += (money * count);
            }

            for(int i = 1 ; i <= n ; i++) {
                int money = coin[i];
                int count = num[i];

                for (int k = sum / 2; k >= 0; k--) {
                    if(dp[k]) {
                        for (int j = 1; j <= count; j++) {
                            if(k + (money * j) <= sum/2) {
                                dp[k + (money * j)] = true;
                            }else {
                                break;
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
