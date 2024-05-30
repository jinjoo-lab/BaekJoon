import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long m;
    static int[] arr;

    static PriorityQueue<Long> pq = new PriorityQueue<>(
            (x,y) -> Long.compare(x,y)
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        arr = new int[n+1];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 1 ; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = n;
        int mid = 0;

        int result = n;

        while(l <= r) {
            mid = (l + r) / 2;

            if(go(mid)) {
                result = Math.min(result , mid);
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        System.out.println(result);
        br.close();
    }

    static boolean go(int num) {
        pq.clear();

        for(int i = 1 ; i <= num ; i++) {
            pq.add(0l);
        }

        for(int i = 1 ; i <= n ; i++) {
            if(!pq.isEmpty()) {
                long tmp = pq.poll();
                tmp += arr[i];
                pq.add(tmp);
            }
        }

        long result = 0;

        while(!pq.isEmpty()) {
            result = pq.poll();
        }

        return result <= m;
    }
}
