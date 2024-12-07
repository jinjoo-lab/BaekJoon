import java.util.*;
import java.io.*;

public class Main {

    static int t,n,a,b;
    static boolean[] isPrime = new boolean[1_00_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makePrime();

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();

        for(int tt = 1 ; tt <= t ; tt++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            int tmp = go();

            sb.append(tmp).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int go() {
        int result = -1;
        Queue<Integer> q = new ArrayDeque<>();
        int[] v = new int[1_000_001];
        q.add(n);
        v[n] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur >= a && cur <= b && !isPrime[cur]) {
                return v[cur] - 1;
            }

            int half = cur / 2;
            if(v[half] == 0) {
                v[half] = v[cur] + 1;
                q.add(half);
            }

            int talf = cur / 3;
            if(v[talf] == 0) {
                v[talf] = v[cur] + 1;
                q.add(talf);
            }

            if(cur + 1 <= 1_000_000) {
                if(v[cur + 1] == 0) {
                    v[cur + 1] = v[cur] + 1;
                    q.add(cur + 1);
                }
            }

            if(cur - 1 >= 0 && v[cur - 1] == 0) {
                v[cur - 1] = v[cur] + 1;
                q.add(cur - 1);
            }
        }

        return result;
    }


    static void makePrime() {
        for(int i = 2 ; i <= Math.sqrt(1000_00) + 1 ; i++) {
            if(!isPrime[i]) {
                int j = 2;

                while(i * j <= 1000_00) {
                    isPrime[i*j] = true;
                    j += 1;
                }
            }
        }
    }
}
