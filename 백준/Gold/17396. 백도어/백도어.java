import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] sight;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sight = new int[n];
        graph = new ArrayList[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            sight[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }
        sight[n-1] = 0;

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long v = Integer.parseInt(st.nextToken());

            if(sight[x] == 1 || sight[y] == 1){
                continue;
            }

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }

        bs();
        br.close();
    }

    static void bs(){
        boolean[] visit = new boolean[n];
        long[] dis = new long[n];
        for(int i=1;i<n;i++){
            dis[i] = Long.MAX_VALUE;
        }
        dis[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );
        pq.add(new Node(0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Node next : graph[cur.v]){
                if(sight[next.v] == 1)
                    continue;

                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        if(dis[n-1] != Long.MAX_VALUE){
            System.out.println(dis[n-1]);
        }

        else{
            System.out.println(-1);
        }
    }
}
class Node{
    int v;
    long c;

    Node(int v,long c){
        this.v = v;
        this.c = c;
    }
}