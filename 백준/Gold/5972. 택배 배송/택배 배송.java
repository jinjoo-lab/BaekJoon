import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int MAX = 500000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<point>[] list = new ArrayList[n+1];
        for(int i=0;i<=n;i++)
        {
            list[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new point(b,c));
            list[b].add(new point(a,c));
        }

        PriorityQueue<point> q = new PriorityQueue<>(
                (o1,o2) -> o1.cost - o2.cost
        );

        q.add(new point(1,0));
        int[] dis = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            dis[i] = MAX;
        }
        dis[1] = 0;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<list[cur.to].size();i++)
            {
                point next = list[cur.to].get(i);

                if(dis[next.to] > dis[cur.to] + next.cost)
                {
                    dis[next.to] = dis[cur.to] + next.cost;
                    q.add(new point(next.to,dis[next.to]));
                }
            }
        }

        System.out.println(dis[n]);
        br.close();
    }


}
class point
{
    int to;
    int cost;

    point(int to,int cost)
    {
        this.to = to;
        this.cost = cost;
    }
}