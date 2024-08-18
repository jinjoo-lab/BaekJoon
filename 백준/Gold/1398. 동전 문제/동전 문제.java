import java.util.*;
import java.io.*;

public class Main {

    static int tt;
    static long money;

    static int[] dp = new int[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        tt = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= 99 ; i++) {
            dp[i] = 101;
        }

        int[] tmp = {1,10,25};

        for(int j = 0 ; j < 3 ; j++) {
            for (int i = 1; i <= 99; i++) {

                if(tmp[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - tmp[j]] + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int t= 1 ; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine()," ");
            money = Long.parseLong(st.nextToken());
            long result = 0;

            while(money > 0) {
                int tmpM = (int)(money % 100);
                result += dp[tmpM];

                money = money / 100;
            }

            sb.append(result+"\n");
        }
        //print();

        System.out.print(sb.toString());



        br.close();
    }

    static void print() {
        for(int i = 0 ; i <= 99 ; i++) {
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }
}
