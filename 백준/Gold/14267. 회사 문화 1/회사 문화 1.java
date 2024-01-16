import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;
    static int[] p;
    static int[] count;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        count = new int[n+1];
        p = new int[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=1;i<=n;i++){
            int cur = Integer.parseInt(st.nextToken());

            if(cur == -1)
                continue;

            p[i] = cur;

            graph[i].add(cur);
            graph[cur].add(i);
        }


        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            count[x] += y;
        }

        dfs(1);

        for(int i=1;i<=n;i++){
            sb.append(count[i]+" ");
        }
        System.out.println(sb);

        bf.close();
    }

    static void dfs(int cur){

        for(int next : graph[cur]){

            if(p[cur] == next)
                continue;

            count[next] += count[cur];
            dfs(next);
        }
    }
}
