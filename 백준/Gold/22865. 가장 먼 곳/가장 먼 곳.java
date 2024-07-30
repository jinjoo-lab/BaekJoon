import java.util.*;
import java.io.*;

public class Main {

    static long[] minDis;
    static int n,m;
    static int v1,v2,v3;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        minDis = new long[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
            minDis[i] = Long.MAX_VALUE;
        }

        st = new StringTokenizer(br.readLine()," ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        v3 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i =1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int v3 = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,v3));
            graph[v2].add(new Node(v1,v3));
        }

        go(v1);
        go(v2);
        go(v3);

        int idx = 0;
        long value = 0;

        for(int i = 1 ; i <= n ; i++) {
            if(v1 == i || v2 == i || v3 == i)
                continue;

            if(minDis[i] > value) {
                value = minDis[i];
                idx = i;
            }
        }

        System.out.println(idx);
        br.close();
    }

    static void go(int v) {
        long[] dis = new long[n+1];

        for(int i = 1 ; i <= n ; i++) {
            dis[i] = Long.MAX_VALUE;
        }

        dis[v] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        pq.add(new Node(v,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]) {
                if(dis[next.v] > cur.c + next.c) {
                    dis[next.v] = cur.c + next.c;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            if(v1 == i || v2 == i || v3 == i)
                continue;

            minDis[i] = Math.min(minDis[i],dis[i]);
        }
    }

    static void print(long[] dis) {
        for(int i = 1 ; i <= n ; i++) {

            if(i == v1 || i == v2 || i == v3)
                continue;

            System.out.print(i+" : "+dis[i]+" , ");
        }
        System.out.println();
    }


    static class Node{
        int v;
        long c;

        Node(int v, long c) {
            this.v = v;
            this.c = c;
        }
    }
}
