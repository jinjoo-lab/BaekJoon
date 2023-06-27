import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
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

        star[] arr = new star[n+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y =  Double.parseDouble(st.nextToken());
            star cur = new star(x,y);

            arr[i] = cur;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) ->  Double.compare(x.cost , y.cost)
        );

        for(int i=1;i<n;i++)
        {
            for(int j= i+1;j<=n;j++)
            {
                Edge edge = new Edge(i,j,cal(arr[i], arr[j]));

                pq.add(edge);
            }
        }

        double re = 0.0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.x) != find(cur.y))
            {
                re += cur.cost;
                union(cur.x ,cur.y);
            }
        }

        System.out.printf("%.2f",re);
        System.out.println();
        br.close();
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

    static double cal(star cur1 , star cur2)
    {
        double tmp1 = Math.pow((cur1.x - cur2.x),2);
        double tmp2 = Math.pow((cur1.y - cur2.y),2);

        return Math.sqrt(Math.abs(tmp2 + tmp1));
    }
}
class star
{
    double x;
    double y;

    star(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
}

class Edge
{
    int x;
    int y;
    double cost;

    Edge(int x,int y, double cost)
    {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}