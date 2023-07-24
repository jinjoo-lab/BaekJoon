import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Node> graph = new ArrayList();

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(x,y,c));
        }

        search(graph);

        br.close();
    }

    static void search(ArrayList<Node> graph)
    {
        long[] dis = new long[n+1];

        for(int i=1;i<=n;i++)
        {
            dis[i] = Long.MAX_VALUE;
        }
        dis[1] = 0;

        for(int i=1;i<=n;i++){

            for(int j=0;j<m;j++)
            {
                Node cur = graph.get(j);

                if(dis[cur.f] != Long.MAX_VALUE && dis[cur.v] > dis[cur.f] + cur.c)
                {
                    dis[cur.v] = dis[cur.f] + cur.c;
                }
            }
        }

        for(int j=0;j<m;j++)
        {
            Node cur = graph.get(j);

            if(dis[cur.f] != Long.MAX_VALUE && dis[cur.v] > dis[cur.f] + cur.c)
            {
                System.out.println(-1);
                return;
            }
        }

        for(int i=2;i<=n;i++)
        {
            if(dis[i] == Long.MAX_VALUE)
                dis[i] = -1;

            System.out.println(dis[i]);
        }

    }
}
class Node
{
    int f;
    int v;
    int c;

    Node(int f,int v,int c)
    {
        this.f = f;
        this.v = v;
        this.c = c;
    }
}
