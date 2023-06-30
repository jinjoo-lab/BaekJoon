import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        root = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            root[i] = i;
        }

        m = Integer.parseInt(st.nextToken());

        point[] parr = new point[n+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            parr[i] = new point(x,y);
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            union(tmp1, tmp2);
        }


        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> Double.compare(x.v,y.v)
        );

        for(int i=1;i<n;i++)
        {
            for(int j=i+1;j<=n;j++)
            {
                   pq.add(new Edge(i,j,distance(parr[i], parr[j])));
            }
        }


        kruskal(pq);


        br.close();
    }

    static void kruskal(PriorityQueue<Edge> pq)
    {
        double result = 0.0;

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(find(cur.x) != find(cur.y))
            {
                result += cur.v;
                union(cur.x , cur.y);
            }
        }

        System.out.printf("%.2f\n",result);
    }

    static double distance(point a,point b)
    {
        double tmp1 = Math.pow(Math.abs((a.x - b.x)),2);
        double tmp2 = Math.pow(Math.abs((a.y - b.y)),2);

        double tmp3 = Math.sqrt(Math.abs(tmp2 + tmp1));

        return tmp3;
    }

    static int find(int x)
    {
        if(root[x] == x)
            return x;
        else
            return root[x] = find(root[x]);
    }

    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
class point
{
    int x;
    int y;

    point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}
class Edge
{
    int x;
    int y;
    double v;

    Edge(int x,int y,double v)
    {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}
