import java.util.*;
import java.io.*;

public class Main {

    static int t,n,m;

    static ArrayList<Node>[] graph;

    static boolean[] v;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            v = new boolean[n+1];
            dp = new int[n+1];

            for(int i=1 ;i<=n;i++){
                graph[i] = new ArrayList<>();
            }


            for(int i=1;i<=m;i++){
                st = new StringTokenizer(br.readLine()," ");

                int v1= Integer.parseInt(st.nextToken());
                int v2= Integer.parseInt(st.nextToken());
                int c= Integer.parseInt(st.nextToken());

                graph[v1].add(new Node(v2,c));
                graph[v2].add(new Node(v1,c));
            }
            
            sb.append(dfs(1,0)+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int dfs(int cur,int p){

        v[cur] = true;

        if(graph[cur].size() == 1 && graph[cur].get(0).v == p){
            return dp[cur] = graph[cur].get(0).c;
        }

        for(Node next : graph[cur]){

            int tmp = 0;

            if(!v[next.v]){
                tmp = dfs(next.v,cur);
            }

            dp[cur] += Math.min(tmp,next.c);
        }

        return dp[cur];
    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
