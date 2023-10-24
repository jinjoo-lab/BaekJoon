import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        int max = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.e == y.e)
                        return x.t - y.t;
                    return y.e - x.e;}
        );
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            max = Math.max(max,e);

            pq.add(new Node(t,e));

        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(max > cur.e)
                max = cur.e;

            max = max - cur.t;
        }

        if(max < 0)
            System.out.println(-1);
        else
            System.out.println(max);
        br.close();
    }
}
class Node{
    int t;
    int e;

    Node(int t,int e){
        this.t= t;
        this.e = e;
    }
}
