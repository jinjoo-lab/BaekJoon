import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int o = 0;
    static int l = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.d == y.d)
                        return y.v - x.v;

                    return x.d - y.d;
                }
        );

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Node(d,v));
        }


        int result = 0;

        PriorityQueue<Integer> time = new PriorityQueue<>(
                (x,y) -> x - y
        );

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int size = time.size();

            if(size < cur.d){
                time.add(cur.v);
                result += cur.v;
            }

            else if(size == cur.d && time.peek() < cur.v){
                result -= time.poll();
                result += cur.v;
                time.add(cur.v);
            }


        }

        System.out.println(result);
        br.close();
    }
}
class Node{
    int v;
    int d;

    Node(int d,int v){
        this.d = d;
        this.v = v;
    }
}
