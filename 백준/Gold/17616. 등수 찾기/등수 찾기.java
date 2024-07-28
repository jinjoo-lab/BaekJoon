import java.util.*;
import java.io.*;

public class Main {

    static int n,m,x;

    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] graph2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        graph2 = new ArrayList[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph2[v2].add(v1);
        }

        int wCount = goWin(graph);
        int lCount = goWin(graph2);

        int max = 1 + lCount;
        int min = n - wCount;

        System.out.println(max+" "+min);

        br.close();
    }

    static int goWin(ArrayList<Integer>[] graph) {
        boolean[] v = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);

        v[x] = true;

        int count = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                if(!v[next]) {
                    v[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count;
    }
}
