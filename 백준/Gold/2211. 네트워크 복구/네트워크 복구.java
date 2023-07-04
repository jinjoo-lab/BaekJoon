import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());


            graph[x].add(new Edge(y,c));
            graph[y].add(new Edge(x,c));
        }

        search(graph);
        br.close();
    }

    static void search(ArrayList<Edge>[] graph)
    {
        boolean[] visit = new boolean[n+1];
        int[] dis = new int[n+1];
        int[] past = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            dis[i] = Integer.MAX_VALUE;
        }
        dis[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Edge(1,0));

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Edge next : graph[cur.v])
            {
                if(dis[cur.v] + next.c < dis[next.v])
                {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Edge(next.v , dis[next.v]));
                    past[next.v] = cur.v;
                }
            }
        }

        HashSet<String> set = new HashSet<>();

        for(int i=2;i<=n;i++)
        {
            int small = Math.min(i,past[i]);
            int big = Math.max(i,past[i]);

            set.add(small+" "+big+"\n");
        }

        System.out.println(set.size());
        for(String go : set)
        {
            System.out.print(go);
        }
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
