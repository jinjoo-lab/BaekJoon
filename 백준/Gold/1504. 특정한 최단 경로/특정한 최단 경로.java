import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n+1];
        root = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            root[i] = i;
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[x].add(new Edge(y,c));
            graph[y].add(new Edge(x,c));
        }
        st = new StringTokenizer(br.readLine()," ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        if(v1 == 1 && v2 == n)
        {
            int tmp = go(graph,1,n);

            if(tmp==Integer.MAX_VALUE)
                System.out.println(-1);

            else
                System.out.println(tmp);
        }

        else {
            int result = -1;

            int tmp1 = go(graph, 1, v1);
            int tmp2 = go(graph, v1, v2);
            int tmp3 = go(graph, v2, n);

            if (tmp1 == Integer.MAX_VALUE || tmp2 == Integer.MAX_VALUE || tmp3 == Integer.MAX_VALUE)
                result = -1;

            else {
                result = tmp1 + tmp2 + tmp3;
            }

            tmp1 = go(graph, 1, v2);
            tmp2 = go(graph, v2, v1);
            tmp3 = go(graph, v1, n);

            if (!(tmp1 == Integer.MAX_VALUE || tmp2 == Integer.MAX_VALUE || tmp3 == Integer.MAX_VALUE)) {
                result = Math.min(result, tmp1 + tmp2 + tmp3);
            }
            System.out.println(result);
        }
        br.close();
    }

    static int go(ArrayList<Edge>[] graph,int start,int end)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        boolean[] visit = new boolean[n+1];

        int[] dis = new int[n+1];

        for(int i=1;i<=n;i++)
            dis[i] = Integer.MAX_VALUE;

        dis[start] = 0;
        pq.add(new Edge(start,0));

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Edge next : graph[cur.v])
            {
                if(dis[next.v] > dis[cur.v] + next.c)
                {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Edge(next.v, dis[next.v]));
                }
            }
        }

        return dis[end];
    }

    static int find(int x)
    {
        if(x == root[x])
            return x;

        else return root[x] = find(root[x]);
    }

    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if( x < y)
            root[y] = x;

        else
            root[x] = y;
    }

}
class Edge{
    int v;
    int c;

    Edge(int v,int c)
    {
        this.v = v;
        this.c = c;
    }
}