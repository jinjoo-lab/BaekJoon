import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Integer>[] graph;

    static int[] v;

    static boolean result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        for(int k=1;k<=t;k++) {

            result = true;

            st = new StringTokenizer(bf.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            v = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>(10);
            }

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(bf.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                graph[y].add(x);
            }

            for (int i = 1; i <= n; i++) {
                if (v[i] == 0) {
                    dfs(i, 1);
                }

                if (!result) {
                    sb.append("impossible\n");
                    break;
                }
            }

            if (result) {
                sb.append("possible\n");
            }

        }
        System.out.print(sb);

        bf.close();
    }

    static void dfs(int x,int c){

        int change = change(c);

        for(int next : graph[x]){

            if(v[next] == c) {
                result = false;
                return;
            }

            if(v[next] == 0){
                v[next] = change;
                dfs(next,change);
            }
        }
    }

    static int change(int c){
        if(c == 1)
            return 2;

        return 1;
    }
}



