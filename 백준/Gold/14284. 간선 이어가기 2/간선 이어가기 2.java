import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static int v1,v2;

    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new int[]{v2,c});
            graph[v2].add(new int[]{v1,c});
        }

        st = new StringTokenizer(br.readLine()," ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[1] - y[1]
        );

        pq.add(new int[]{v1,0});

        int[] dis = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        dis[v1] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dis[cur[0]] < cur[1] || cur[0] == v2)
                continue;

            for(int[] next : graph[cur[0]]) {
                if(dis[next[0]] > dis[cur[0]] + next[1]) {
                    dis[next[0]] = dis[cur[0]] + next[1];
                    pq.add(new int[]{next[0], dis[next[0]]});
                }
            }
        }

        System.out.println(dis[v2]);

        br.close();
    }
}
