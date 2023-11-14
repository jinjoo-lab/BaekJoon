import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[i-1] = new Node(d,c);
        }

        Arrays.sort(arr,(x,y) -> {
            if(x.d == y.d){
                return y.c - x.c;
            }
            return x.d - y.d;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> y - x
        );

        st = new StringTokenizer(br.readLine()," ");
        int end = Integer.parseInt(st.nextToken());
        int cur = Integer.parseInt(st.nextToken());
        int idx = 0;
        int answer = 0;
        boolean out = false;

        while(cur < end){
            while(idx < n && cur >= arr[idx].d){
                pq.add(arr[idx].c);
                idx++;
            }

            if(pq.isEmpty()){
                out = true;
                break;
            }

            cur += pq.peek();
            pq.poll();
            answer += 1;
        }

        if(!out){
            System.out.println(answer);
        }else{
            System.out.println(-1);
        }

        br.close();
    }
}
class Node{
    int d;
    int c;

    Node(int d,int c){
        this.d = d;
        this.c = c;
    }
}