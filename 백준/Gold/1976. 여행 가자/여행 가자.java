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
        root = new int[n+1];
        for(int i=1;i<=n;i++)
        {
            root[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        Queue<Edge> q = new LinkedList<>();

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp==1)
                    q.add(new Edge(i,j));
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] re = new int[m+1];
        for(int i=1;i<=m;i++)
        {
            re[i] = Integer.parseInt(st.nextToken());
        }

        while(!q.isEmpty())
        {
            Edge cur = q.poll();

            if(find(cur.x) != find(cur.y))
            {
                union(cur.x,cur.y);
            }
        }

        if(result(re))
            System.out.println("YES");

        else
            System.out.println("NO");


        br.close();
    }

    static boolean result(int[] arr)
    {
        int tmp = -1;
        for(int i=1;i<=m;i++)
        {
            if(tmp==-1)
                tmp = root[arr[i]];

            else{
                if(root[arr[i]] != tmp)
                    return false;
            }
        }

        return true;
    }

    static int find(int x)
    {
        if(x == root[x])
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

    Edge(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}