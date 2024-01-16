import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int[] depth;
    static ArrayList<Integer>[] graph;

    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        depth = new int[n+1];
        v = new boolean[n+1];

        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<n;i++){
            int cur = Integer.parseInt(st.nextToken());

            if(cur == -1)
                continue;

            graph[cur].add(i);
        }

        depth[0] = 1;
        int result = dfs(0);

        sb.append(result+"\n");
        System.out.print(sb);

        bf.close();
    }

    static int dfs(int cur){

        for(int next : graph[cur]){
            depth[next] = 1 + dfs(next);
        }

        Collections.sort(graph[cur], (x,y) -> depth[y] - depth[x]);

        int max = 0;

        for(int i=0;i < graph[cur].size();i++){

            int num = graph[cur].get(i);
            depth[num] += i;
            max = Math.max(max,depth[num]);
        }

        return max;
    }
}
