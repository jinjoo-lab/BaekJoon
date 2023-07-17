import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            if(n == 0)
                break;

            char[] type = new char[n+1];
            int[] cost = new int[n+1];

            ArrayList<Integer> graph[] = new ArrayList[n+1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();

                st = new StringTokenizer(br.readLine(), " ");
                char t = st.nextToken().charAt(0);
                type[i] = t;

                int c = Integer.parseInt(st.nextToken());
                cost[i] = c;

                while (st.hasMoreTokens()) {
                    int next = Integer.parseInt(st.nextToken());

                    if (next != 0)
                        graph[i].add(next);
                }
            }
            sb.append(search(type,cost,graph));
        }
        System.out.print(sb);

        br.close();
    }

    static String search(char[] t, int[] c, ArrayList<Integer>[] graph)
    {
        boolean[] v = new boolean[n+1];
        int[] dis = new int[n+1];

        for(int i=1;i<=n;i++)
            dis[i] = -1;

        PriorityQueue<room> pq = new PriorityQueue<>(
                (x,y) -> y.cost - x.cost
        );

        pq.add(new room(1,0));

        while(!pq.isEmpty())
        {
            room cur = pq.poll();

            if(t[cur.v] == 'E')
            {
                v[cur.v] = true;

                if(cur.v == n)
                    break;

                for(int next : graph[cur.v])
                {
                    if(dis[next] < cur.cost)
                    {
                        dis[next] = cur.cost;
                        pq.add(new room(next,dis[next]));
                    }
                }
            }

            else if(t[cur.v] == 'L')
            {
                int curC = cur.cost;

                if(curC <= c[cur.v])
                    curC = c[cur.v];

                v[cur.v] = true;

                if(cur.v == n)
                    break;

                for(int next : graph[cur.v])
                {
                    if(dis[next] < curC)
                    {
                        dis[next] = curC;
                        pq.add(new room(next,dis[next]));
                    }
                }
            }

            else{
                int curC = cur.cost;

                if(curC < c[cur.v]) {
                    continue;
                }

                v[cur.v] = true;

                if(cur.v == n)
                    break;

                for(int next : graph[cur.v])
                {
                    if(dis[next] < curC - c[cur.v])
                    {
                        dis[next] = curC - c[cur.v];
                        pq.add(new room(next,dis[next]));
                    }
                }
            }
        }

        if(v[n])
            return "Yes\n";
        else
            return "No\n";
    }
}
class room
{
    int v;
    int cost;

    room(int v,int cost)
    {
        this.v = v;
        this.cost = cost;
    }
}
