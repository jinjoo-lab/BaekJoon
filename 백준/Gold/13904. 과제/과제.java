import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.v == y.v)
                        return y.d - x.d;

                    return y.v - x.v;
                }
        );

        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            Node node = new Node(d,v);
            pq.add(node);
        }

        boolean[] v = new boolean[1001];
        int result  = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            int tmpDay = cur.d;

            for(int i = tmpDay;i >=1;i--){
                if(!v[i]){
                    v[i] = true;
                    result += cur.v;
                    break;
                }
            }
        }
        System.out.println(result);
        br.close();
    }
}
class Node{
    int d;
    int v;

    Node(int d,int v){
        this.d = d;
        this.v = v;
    }
}
