import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static ArrayList<int[]> graph[];

    static boolean[] isIt;
    static long[] dis;

    static PriorityQueue<Point> pq = new PriorityQueue<>(
            (x,y) -> Long.compare(x.c,y.c)
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dis = new long[n+1];
        isIt = new boolean[n+1];

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
            dis[i] = Long.MAX_VALUE;
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v2].add(new int[]{v1,c});
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= k ; i++) {
            int node = Integer.parseInt(st.nextToken());
            pq.add(new Point(node,0l));
            dis[node] = 0l;
            isIt[node] = true;
        }

        go();
        br.close();
    }

    static void go() {
        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            if(dis[cur.v] < cur.c)
                continue;

            for(int[] next : graph[cur.v]) {
                if(isIt[next[0]])
                    continue;

                if(dis[next[0]] > cur.c + next[1]) {
                    dis[next[0]] = cur.c + next[1];
                    pq.add(new Point(next[0],dis[next[0]]));
                }
            }
        }

        int maxIdx = 0;
        long maxNum = 0;

        for(int i = 1 ; i <= n ; i++) {
            if(maxNum < dis[i]) {
                maxIdx = i;
                maxNum = dis[i];
            }
        }

        System.out.println(maxIdx);
        System.out.println(maxNum);
    }

    static class Point {
        int v;
        long c;

        Point(int v,long c) {
            this.v = v;
            this.c = c;
        }
    }
}
