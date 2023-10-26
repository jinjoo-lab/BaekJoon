import java.io.*;
import java.util.*;

public class Main {
    static int t = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        t = Integer.parseInt(st.nextToken());

        boolean[] notPrime = new boolean[1000001];
        long[] sum = new long[1000001];

        for (int i = 1; i <= 1000000; i++) {

            int j = 1;

            while (i * j <= 1000000) {
                notPrime[i * j] = true;
                sum[i * j] += i;
                j = j + 1;
            }

        }

        for(int i=2; i<= 1000000;i++){
            sum[i] += sum[i-1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            int tmp = Integer.parseInt(br.readLine());

            sb.append(sum[tmp] + "\n");
        }
        System.out.print(sb);

        br.close();
    }

}
