import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static final long NOT = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,i,i));
            graph[y].add(new Node(x,i,i));
        }


        search();
        br.close();
    }

    static void search(){
        boolean[] v = new boolean[n+1];
        v[1] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.t,y.t)
        );

        long[] dis = new long[n+1];
        for(int i=2;i<=n;i++){
            dis[i] = NOT;
        }

        for(Node tmp : graph[1]){
            dis[tmp.v] = tmp.idx;
            pq.add(new Node(tmp.v,dis[tmp.v],tmp.idx));
        }


        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == n){
                System.out.println(cur.t);
                break;
            }

            if(v[cur.v])
                continue;

            v[cur.v] = true;

            for(Node next : graph[cur.v]){
                long curT = (cur.t - next.idx);

                if(curT < 0)
                    curT = 0;

                else {
                    curT = curT / m;
                    curT = curT + 1;
                }

                if(dis[next.v] > next.idx + (curT * m)){
                    dis[next.v] = next.idx + (curT * m);

                    pq.add(new Node(next.v,dis[next.v],next.idx));
                }

            }
        }

    }

    static void print(long[] dis){
        for(int i=1;i<=n;i++){
            System.out.print(dis[i]+" ");
        }
        System.out.println();
    }
}
class Node{
    int v;
    long  t;

    long idx;

    Node(int v,long t,long idx){
        this.v = v;
        this.t = t;
        this.idx = idx;
    }
}

