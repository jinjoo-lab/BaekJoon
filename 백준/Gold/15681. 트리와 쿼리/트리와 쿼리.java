import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int k = 0;

    static ArrayList<Integer>[] graph;

    static int[] count;
    static int[] p;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        count = new int[n+1];
        v = new boolean[n+1];
        p = new int[n+1];


        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n-1;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(m);


        for(int i=1;i<=k;i++){
           int cur = Integer.parseInt(bf.readLine());
           sb.append(count[cur]+"\n");
        }

        System.out.print(sb);

        bf.close();
    }

    static int dfs(int cur){

        if(v[cur])
            return count[cur];

        v[cur] = true;

        int sum = 1;

        for(int next : graph[cur]){
            if(p[cur] == next){
                continue;
            }

            else{
                p[next] = cur;
                sum += dfs(next);
            }
        }

        return count[cur] = sum;
    }
}
