import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] isMen = new boolean[n+1];
        boolean[] check = new boolean[n+1];

        root = new int[n+1];
        for(int i=1;i<=n;i++)
        {
            root[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            String cur =st.nextToken();
            if(cur.equals("M"))
                isMen[i] = true;
        }


        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.val - y.val
        );

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            if(isMen[x] != isMen[y])
            {
                pq.add(new Edge(x,y,val));
            }
        }

        int cost = 0;
        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(find(cur.x) != find(cur.y))
            {
                cost += cur.val;
                union(cur.x, cur.y);
                check[cur.x] = true;
                check[cur.y] = true;
            }
        }

        if(cost==0 || !allCheck(check))
        {
            System.out.println(-1);
        }

        else{
            System.out.println(cost);
        }
        br.close();
    }

    static boolean allCheck(boolean[] arr)
    {
        for(int i=1;i<=n;i++)
        {
            if(!arr[i])
                return false;
        }

        return true;
    }

    static int find(int x)
    {
        if(root[x] == x)
            return x;

        else{
            return root[x] = find(root[x]);
        }
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
    int val;

    Edge(int x,int y,int val)
    {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
