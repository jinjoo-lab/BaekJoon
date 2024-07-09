import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static ArrayList<Node>[] graph;

    static int v1,v2,v3;

    static final int MAX = Integer.MAX_VALUE;

    static int[][] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        path = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        st = new StringTokenizer(br.readLine()," ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        v3 = Integer.parseInt(st.nextToken());

        int[] r1 = search(v1);
        int[] r2 = search(v2);
        int[] r3 = search(v3);

        int result = MAX;
        int idx = 1;

        for(int i = 1 ; i <= n ; i++) {
            if(r1[i] != MAX && r2[i] != MAX && r3[i] != MAX) {
                if(result > r1[i] + r2[i] + r3[i]) {
                    result = r1[i] + r2[i] + r3[i];
                    idx = i;
                }
            }
        }

        makePath(v1,idx);
        makePath(v2,idx);
        makePath(v3,idx);

        StringBuilder sb = new StringBuilder();
        sb.append(result+" "+edges.size()+"\n");

        for(Edge next : edges) {
            sb.append(next.v1+" "+next.v2+"\n");
        }

        System.out.print(sb);


        br.close();
    }

    static HashSet<Edge> edges = new HashSet<>();

    static void makePath(int v,int idx) {

        while(path[v][idx] != 0) {
            edges.add(new Edge(idx,path[v][idx]));
            idx = path[v][idx];
        }
    }

    static int[] search(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[] dis = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        dis[start] = 0;
        pq.add(new Node(start,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.v] < cur.c) {
                continue;
            }

            for(Node next : graph[cur.v]) {
                if(dis[next.v] > cur.c + next.c) {
                    dis[next.v] = cur.c +  next.c;
                    path[start][next.v] = cur.v;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        return dis;
    }

    static class Node {
        int v;
        int c;

        Node(int v,int c) {
            this.v = v;
            this.c = c;
        }
    }

    static class Edge {
        int v1;
        int v2;

        Edge(int v1,int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.min(v1,v2),Math.max(v1,v2));
        }
    }
}
