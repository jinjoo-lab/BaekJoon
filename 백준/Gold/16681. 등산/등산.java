import java.util.*;
import java.io.*;

public class Main {

    static int n,m,d,e;
    static int[] height;

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        height = new int[n+1];
        graph = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            height[i] = Integer.parseInt(st.nextToken());

            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        long[] dis1 = search(1);
        long[] dis2 = search(n);

        long result = Long.MIN_VALUE;

        for(int i = 2 ; i <= n - 1 ; i++) {
            if(dis1[i] != Long.MAX_VALUE && dis2[i] != Long.MAX_VALUE) {
                result = Math.max(result,(height[i] * e) - (dis1[i] + dis2[i]));
            }
        }

        if(result == Long.MIN_VALUE) {
            System.out.println("Impossible");
        }else {
            System.out.println(result);
        }


        br.close();
    }

    static long[] search(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        long[] dis = new long[n+1];
        for(int i = 1 ; i <= n ; i++) {
            dis[i] = Long.MAX_VALUE;
        }

        dis[start] = 0;
        pq.add(new Node(start,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]) {
                if(height[next.v] <= height[cur.v])
                    continue;

                if(dis[next.v] <= cur.c + (next.c * d)) {
                    continue;
                }

                dis[next.v] = cur.c + (next.c * d);
                pq.add(new Node(next.v,dis[next.v]));
            }
        }

        return dis;
    }

    static class Node {
        int v;
        long c;

        Node(int v,long c) {
            this.v = v;
            this.c = c;
        }
    }
}
