
import java.util.*;
import java.io.*;

public class Main {
    static int n,k;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n];

        st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> y - x
        );

        for(int i = 1  ; i < n ; i++){
            pq.add(board[i] - board[i-1]);
        }

        if(k >= n){
            System.out.println(0);
            return;
        }

        long result = 0;
        int idx = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();

            if(idx < k - 1) {
                idx = idx + 1;
                continue;
            }

            result += cur;
        }

        System.out.println(result);
        br.close();
    }
}
