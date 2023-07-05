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

        ArrayList<point> graph = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(start >=0 && end <= m)
                graph.add(new point(start,end,cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c -y.c
        );
        pq.add(new Edge(0,0));
        int[] dis = new int[m+1];
        for(int i=0;i<=m;i++)
            dis[i] = i;

        boolean[] visit = new boolean[m+1];

        int min = m;

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(point next : graph)
            {
                if(next.x >= cur.v)
                {
                    int tmp1 = next.x - cur.v;

                    if(dis[next.y] > dis[cur.v] + tmp1 + next.c)
                    {
                        dis[next.y] = dis[cur.v] + tmp1 + next.c;
                        pq.add(new Edge(next.y, dis[next.y]));

                        min = Math.min(min , m - next.y + dis[next.y]);
                    }
                }
            }
        }

        System.out.println(Math.min(dis[m],min));
        br.close();
    }
}
class point
{
    int x;
    int y;
    int c;

    point(int x,int y,int c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
class Edge
{
    int v;
    int c;

    Edge(int v,int c)
    {
        this.v =v;
        this.c= c;
    }
}