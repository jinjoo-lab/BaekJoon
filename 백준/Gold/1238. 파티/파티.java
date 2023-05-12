
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int x = 0;

    static int MAX = 500000000;

    static int[] dis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

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
        }
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            if(i==x)
                continue;

            result = Math.max(search(i,list),result);
        }
        System.out.println(result);
        br.close();
    }

    static int search(int i,ArrayList<point>[] list)
    {
        int result = 0;
        PriorityQueue<point> q = new PriorityQueue<>(
                (o1,o2) -> o1.cost - o2.cost
        );

        q.add(new point(i,0));

        dis = new int[n+1];
        for(int j=1;j<=n;j++)
        {
            dis[j] = MAX;
        }
        dis[i] = 0;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int j=0;j<list[cur.to].size();j++)
            {
                point next = list[cur.to].get(j);

                if(dis[next.to] > dis[cur.to] + next.cost)
                {
                    dis[next.to] = dis[cur.to] + next.cost;
                    q.add(new point(next.to, dis[next.to]));
                }
            }
        }
        result += dis[x];

        q = new PriorityQueue<>(
                (o1,o2) -> o1.cost - o2.cost
        );

        dis = new int[n+1];
        for(int j=1;j<=n;j++)
        {
            dis[j] = MAX;
        }
        dis[x] = 0;

        q.add(new point(x,0));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int j=0;j<list[cur.to].size();j++)
            {
                point next = list[cur.to].get(j);

                if(dis[next.to] > dis[cur.to] + next.cost)
                {
                    dis[next.to] = dis[cur.to] + next.cost;
                    q.add(new point(next.to, dis[next.to]));
                }
            }
        }
        result +=dis[i];
        return result;
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
