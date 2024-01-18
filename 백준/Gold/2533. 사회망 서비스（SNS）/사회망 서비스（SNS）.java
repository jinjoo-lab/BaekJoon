import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static ArrayList<Integer>[] graph;

    static int[] p;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        //StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        p = new int[n+1];
        dp = new int[n+1][2];

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

        dfs(1);

        System.out.println(n - Math.max(dp[1][0],dp[1][1]));

        bf.close();
    }

    static void dfs(int cur){

        for(int next : graph[cur]){
            if(p[cur] == next){continue;}

            p[next] = cur;

            dfs(next);
            dp[cur][0] += Math.max(dp[next][0],dp[next][1]);
            dp[cur][1] += dp[next][0];
        }

        dp[cur][1] += 1;
    }


}