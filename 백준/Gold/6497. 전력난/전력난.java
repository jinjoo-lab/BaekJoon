import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0&&m==0)
                break;

            root = new int[n+1];
            for(int i=0;i<n;i++)
            {
                root[i] = i;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>(
                    (x,y) -> x.v - y.v
            );

            int all = 0;

            for(int i =1;i<=m;i++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                all += v;
                pq.add(new Edge(x,y,v));
            }

            int rest = kruskal(pq);
            System.out.println(all - rest);
        }


        br.close();
    }
    static int kruskal(PriorityQueue<Edge> pq)
    {
        int result = 0;

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(find(cur.x) != find(cur.y))
            {
                result += cur.v;
                union(cur.x,cur.y);
            }
        }

        return result;
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

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
class Edge{
    int x;
    int y;
    int v;

    Edge(int x,int y,int v)
    {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}