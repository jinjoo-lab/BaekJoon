import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++)
        {
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=m;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Edge(end,cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        search(graph,x,y);
        br.close();
    }

    static void search(ArrayList<ArrayList<Edge>> graph,int start,int end) {
        boolean[] visit = new boolean[n + 1];
        int[] dis = new int[n + 1];
        int[] past = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        dis[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x, y) -> x.c - y.c
        );

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visit[cur.v])
                continue;

            visit[cur.v] = true;

            for (Edge next : graph.get(cur.v)) {
                if (dis[cur.v] + next.c < dis[next.v]) {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Edge(next.v, dis[next.v]));
                    past[next.v] = cur.v;
                }
            }
        }

        System.out.println(dis[end]);

        int count = 0;

        ArrayList<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int tmp = end;

        while(tmp!=0)
        {
            result.add(tmp);
            tmp = past[tmp];
            count = count + 1;
        }

        for(int i=result.size()-1;i>=0;i--)
        {
            sb.append(result.get(i)+" ");
        }

        System.out.println(count);
        System.out.println(sb);

    }
}
class Edge
{
    int v;
    int c;

    Edge(int v,int c)
    {
        this.v = v;
        this.c = c;
    }
}