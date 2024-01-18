import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int[] num;

    static ArrayList<Integer>[] graph;

    static int[][] dp;

    static int[] p;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        num = new int[n+1];
        graph = new ArrayList[n+1];

        p = new int[n+1];
        dp = new int[n+1][2]; // 0 , 1

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=1;i<=n;i++){
            num[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n-1;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }


        dfs(1,0);
        System.out.println(Math.max(dp[1][0],dp[1][1]));

        bf.close();
    }


    static void dfs(int cur,int parent){

        for(int next : graph[cur]){

            if(next == parent)
                continue;

            dfs(next,cur);
            dp[cur][0] += Math.max(dp[next][0],dp[next][1]);
            dp[cur][1] += dp[next][0];
        }

        dp[cur][1] += num[cur];
    }
}