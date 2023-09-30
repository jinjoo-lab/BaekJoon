import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0)
                break;

            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());


            ArrayList<Edge>[] graph = new ArrayList[n];
            int[][] allDis = new int[n + 1][n + 1];

            for (int i = 0; i <= n - 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                allDis[x][y] = c;
                graph[x].add(new Edge(y, c));
            }
            searchMax(graph, start, last, allDis);
            int max = findSecond(graph,start,last,allDis);
            sb.append(max+"\n");
        }
        System.out.print(sb);
    }
    static int searchMax(ArrayList<Edge>[] graph,int start,int end,int[][] allDis)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        boolean[] visit = new boolean[n];
        int[] dis = new int[n];
        ArrayList<Integer>[] list = new ArrayList[n];

        for(int i=0;i<n;i++) {
            list[i] = new ArrayList<>();
            dis[i] = Integer.MAX_VALUE;
        }

        dis[start] = 0;
        pq.add(new Edge(start,0));

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Edge next : graph[cur.v])
            {
                if(dis[cur.v] + next.c < dis[next.v])
                {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Edge(next.v , dis[next.v]));
                    list[next.v].clear();
                    list[next.v].add(cur.v);
                }

                else if(dis[cur.v] + next.c == dis[next.v]){
                    list[next.v].add(cur.v);
                }
            }
        }

        if(dis[end] == Integer.MAX_VALUE )
            dis[end] = -1;

        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[n];
        q.add(end);
        v[end] = true;

        while(!q.isEmpty()){
            int cur = q.poll();


            for(int next : list[cur]){
                allDis[next][cur] = Integer.MAX_VALUE;

                if(!v[next]) {
                    q.add(next);
                    v[next] = true;
                }
            }
        }


        return dis[end];
    }

    static int findSecond(ArrayList<Edge>[] graph,int start,int end,int[][] allDis){
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        boolean[] visit = new boolean[n];
        int[] dis = new int[n];


        for(int i=0;i<n;i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        dis[start] = 0;
        pq.add(new Edge(start,0));

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Edge next : graph[cur.v])
            {
                if(allDis[cur.v][next.v] == Integer.MAX_VALUE)
                    continue;

                if(dis[cur.v] + next.c < dis[next.v])
                {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Edge(next.v , dis[next.v]));
                }

            }
        }

        if(dis[end] == Integer.MAX_VALUE )
            dis[end] = -1;

        return dis[end];
    }
}
class Edge
{
    int v;
    int c;

    ArrayList<Integer> list;

    Edge(int v,int c)
    {
        this.v = v;
        this.c = c;
        this.list = new ArrayList<>();
    }
}