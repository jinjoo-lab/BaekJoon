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

        m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[m+1][3];

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[i][0] = start;
            graph[i][1] = end;
            graph[i][2] = cost;

        }

        kruskal(graph);
        br.close();
    }
    static void kruskal(int[][] graph)
    {
        int cost = 0;
        Arrays.sort(graph , (x,y) -> (x[2] - y[2]));
        ArrayList<Integer> result = new ArrayList<>();

        for(int i=1;i<=m;i++)
        {
            if(find(graph[i][0]) != find(graph[i][1]))
            {
                union(graph[i][0] , graph[i][1]);
                result.add(graph[i][2]);
            }
        }

        Collections.sort(result);
        for(int i=0;i<result.size()-1;i++)
        {
            cost +=result.get(i);
        }

        System.out.println(cost);
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
