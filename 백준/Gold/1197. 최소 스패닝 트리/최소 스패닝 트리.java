import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[n+1];
        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(last,cost));
            graph[last].add(new Edge(start,cost));

        }

        prim(graph, visit);
        br.close();
    }

    static void prim(ArrayList<Edge>[] graph, boolean[] visit)
    {
        int result = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.cost - y.cost
        );

        pq.add(new Edge(1,0));

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.w])
                continue;

            visit[cur.w] = true;
            result += cur.cost;

            for(Edge e : graph[cur.w])
            {
                if(!visit[e.w])
                {
                    pq.add(e);
                }
            }
        }

        System.out.println(result);
    }
}
class Edge
{
    int w;
    int cost;

    Edge(int w,int cost)
    {
        this.w = w;
        this.cost = cost;
    }
}