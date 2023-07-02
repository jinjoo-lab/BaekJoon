import java.io.*;
import java.util.*;

public class Main {

    static int k = 0;
    static int n = 0;
    static int m = 0;

    static int c = 0;
    static int[] dis;

    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= k; a++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph.get(y).add(new Edge(x, cost));
            }

            dis = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dis[i] = Integer.MAX_VALUE;
            }
            dis[c] = 0;

            search(graph, c);

            int count = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (dis[i] != Integer.MAX_VALUE) {
                    count += 1;
                    max = Math.max(max, dis[i]);
                }
            }

            sb.append(count + " " + max + "\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

    static void search(ArrayList<ArrayList<Edge>> graph, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x, y) -> x.c - y.c
        );

        boolean[] visit = new boolean[n + 1];

        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (!visit[cur.v]) {
                visit[cur.v] = true;
            }
            else
                continue;
            for (Edge next : graph.get(cur.v)) {
                if (next.c + dis[cur.v] <= dis[next.v]) {
                    dis[next.v] = next.c + dis[cur.v];
                    pq.add(new Edge(next.v, dis[next.v]));
                }

            }
        }

    }
}

class Edge {
    int v;
    int c;

    Edge(int v, int c) {
        this.v = v;
        this.c = c;
    }
}