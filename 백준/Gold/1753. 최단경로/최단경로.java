
import java.io.*;
import java.util.*;

public class Main {
    static int v = 0;
    static int e = 0;
    static int MAX = Integer.MAX_VALUE;
    static int start = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());

        PriorityQueue<point> pq = new PriorityQueue<>(
                (o1,o2) -> o1.cost - o2.cost
        );

        ArrayList<point>[] list = new ArrayList[v+1];
        int[] dis = new int[v+1];
        for(int i=1;i<=v;i++)
        {
            list[i] = new ArrayList<>();
            dis[i] = MAX;
        }

        for(int i=1;i<=e;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new point(b,c));
        }

        search(start,list,pq,dis);

        for(int i=1;i<=v;i++)
        {
            if(dis[i]==MAX)
                System.out.println("INF");
            else
                System.out.println(dis[i]);
        }
    }

    static void search(int start,ArrayList<point>[] list ,PriorityQueue<point> q,int[] dis)
    {
        q.add(new point(start,0));
        dis[start] = 0;

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