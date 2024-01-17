import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static ArrayList<Integer>[] graph;
    static char[] what;
    static long[] count;

    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        what = new char[n+1];
        count = new long[n+1];
        v = new boolean[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=2;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            char w = st.nextToken().charAt(0);
            long num = Long.parseLong(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[v].add(i);
            what[i] = w;

            int tmp = 1;
            if(what[i] == 'W'){
                tmp = -1;
            }
            count[i] = num * tmp;
        }

        long result = dfs(1);

        System.out.println(result);
        bf.close();
    }

    static long dfs(int cur){

        v[cur] = true;

        long tmpCount = 0;

        for(int next : graph[cur]){
            if(!v[next]){
                tmpCount += dfs(next);
            }
        }

        count[cur] += tmpCount;

        if(count[cur] < 0)
            return 0l;

        return count[cur];
    }
}
