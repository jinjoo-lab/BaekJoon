import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int t = 0;
    static int m = 0;

    static int[][] dp;
    static int k = 0;
    static ArrayList<Node>[] graph;

    static int result = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        t = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");

        k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,t,m));
            graph[v2].add(new Node(v1,t,m));
        }

        search();

        System.out.println(result);
        br.close();
    }

    static void search() {

        int INF = Integer.MAX_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.t - y.t
        );

        dp = new int[n+1][10001];

        pq.add(new Node(1,0,0));

        for(int i = 2 ; i <= n ; i++){
            for(int j = 0 ; j <= 10000 ; j ++)
                dp[i][j] = INF;
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.v == n){
                if(result == -1){
                    result = cur.m;
                }else{
                    result = Math.min(result,cur.m);
                }
                continue;
            }

            if(dp[cur.v][cur.t] < cur.m)
                continue;

            for(Node next : graph[cur.v]) {

                int nextT = cur.t + next.t;
                int nextM = cur.m + next.m;

                if(nextT > t || nextM > m)
                    continue;


                if(dp[next.v][nextT] >  nextM){
                    dp[next.v][nextT] =  nextM;
                    pq.add(new Node(next.v,nextT,nextM));

                    for(int i = nextT + 1 ; i <= t; i++){
                        if(dp[next.v][i] > dp[cur.v][cur.t] + nextM){
                            dp[next.v][i] = dp[cur.v][cur.t] + nextM;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

    }

    static class Node{
        int v;
        int t;
        int m;

        Node(int v,int t,int m){
            this.v = v;
            this.t = t;
            this.m = m;
        }
    }
}
