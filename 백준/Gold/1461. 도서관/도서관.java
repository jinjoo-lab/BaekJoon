import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static PriorityQueue<Integer> plus;
    static PriorityQueue<Integer> minus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        plus = new PriorityQueue<>(
                (x,y) -> y - x
        );
        minus = new PriorityQueue<>(
                (x,y) -> y - x
        );

        int max = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if (tmp >= 0) {
                plus.add(tmp);
                max = Math.max(max, tmp);
            } else {
                minus.add(tmp * -1);
                max = Math.max(max, tmp * -1);
            }
        }

        int result = 0;
        while(!plus.isEmpty()) {
            int next = plus.peek();

            result += (2*next);

            int idx = 0;
            while(!plus.isEmpty()) {
                plus.poll();
                idx++;

                if(idx == m)
                    break;
            }
        }

        while(!minus.isEmpty()) {
            int next = minus.peek();

            result += (2*next);

            int idx = 0;
            while(!minus.isEmpty()) {
                minus.poll();
                idx++;

                if(idx == m)
                    break;
            }
        }

        result -= max;

        System.out.println(result);

        br.close();
    }
}
