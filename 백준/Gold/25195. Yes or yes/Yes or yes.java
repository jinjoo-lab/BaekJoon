import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;
    static ArrayList<Integer>[] graph;
    static boolean[] v;
    static boolean[] isIt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
        }

        st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());

        v = new boolean[n+1];
        isIt = new boolean[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= k ; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            isIt[tmp] = true;
        }

        if(isIt[1]) {
            System.out.println("Yes");
        }else {
            System.out.println(go());
        }

        br.close();
    }

    static String go() {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        v[1] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                if(!isIt[next] && !v[next]) {
                    v[next] = true;
                    q.add(next);
                }
            }

            if(graph[cur].size() == 0 && !isIt[cur]) {
                return "yes";
            }
        }

        return "Yes";
    }
}
