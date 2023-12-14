import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int s = 0;

    static int e = 0;

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine(), " ");

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }

        go();

        bf.close();
    }

    static void go(){

        int[] dis = new int[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> y.c - x.c
        );

        pq.add(new Node(s,1001));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.v]){

                int curV = Math.min(cur.c,next.c);

                if(dis[next.v] < curV){
                    dis[next.v] = curV;
                    pq.add(new Node(next.v,curV));
                }
            }
        }

        System.out.println(dis[e]);

    }

}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
