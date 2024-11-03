import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        board = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int result = n;
        int min = 1;
        int max = n;
        int mid = 0;

        while(min <= max) {
            mid = (min + max) / 2;

            if(go(mid)) {
                result = mid;
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }


        System.out.println(result);
        br.close();
    }

    static boolean go(int target) {
        PriorityQueue<Long> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x,y)
        );

        for(int i = 1 ; i <= target ; i++) {
            pq.add(0l);
        }

        for(int i = 1 ; i <= n ; i++) {
            long tmp = pq.poll();
            tmp += board[i];

            if(tmp > k)
                return false;

            pq.add(tmp);
        }

        return true;
    }
}
