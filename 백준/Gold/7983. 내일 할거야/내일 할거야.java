import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x,y) -> {
                if(x[1] == y[1])
                    return y[0] - x[0];

                return y[1] - x[1];
            }
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new int[]{d,e});
        }



        int day = 1000_000_001;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(day > cur[1]){
                day = cur[1];
            }
            day = day - cur[0];
        }

        System.out.println(day);

        br.close();
    }
}
