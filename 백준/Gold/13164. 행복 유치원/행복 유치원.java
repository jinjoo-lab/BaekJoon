import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board= new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> y - x
        );

        for(int i=1;i<n;i++){
            pq.add(board[i+1] - board[i]);
        }

        for(int i=1;i<=m - 1;i++){
            pq.poll();
        }

        int result = 0;
        while(!pq.isEmpty()){
            result += pq.poll();
        }
        System.out.println(result);

        br.close();
    }
}