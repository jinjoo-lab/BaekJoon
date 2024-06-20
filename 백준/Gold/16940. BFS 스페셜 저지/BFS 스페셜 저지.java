import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static HashSet<Integer>[] graph;

    static int[] v;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        graph = new HashSet[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new HashSet<>();
        }

        for(int i = 1 ; i <= n - 1 ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        st = new StringTokenizer(br.readLine()," ");
        result = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }

        if(result[1] != 1) {
            System.out.println(0);
        }else {
            System.out.println(bfs());
        }

        br.close();
    }

    static int getResult() {
        return 1;
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1,1});
        v = new int[n+1];
        v[1] = 1;
        int idx = 2;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            while(idx <= n ){
                int nextV = result[idx];

                if(v[nextV] != 0)
                    continue;

                if(graph[cur[0]].contains(nextV)) {
                    v[nextV] = cur[1] + 1;
                    q.add(new int[]{nextV,v[nextV]});
                }else {
                    break;
                }

                idx += 1;
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            if(v[i] == 0)
                return 0;
        }

        return 1;
    }

}
