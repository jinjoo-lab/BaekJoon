import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;
    static int[] root;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        graph = new ArrayList[n+1];
        dp = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            root[i] = i;
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Point(v1,v2,c));
        }

        int count = 0;
        int total = 0;

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                total += cur.c;
                count += 1;

                graph[cur.v1].add(new Node(cur.v2,cur.c));
                graph[cur.v2].add(new Node(cur.v1,cur.c));
            }

            if(count == n-1)
                break;
        }

        for(int i=1;i<=n;i++){
            bfs(i);
        }

        st = new StringTokenizer(bf.readLine(), " ");
        int k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=k;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            sb.append(total - dp[x1][x2]);
            sb.append("\n");
        }

        System.out.print(sb);
        bf.close();
    }
    static void bfs(int s){
        Queue<Node> q = new LinkedList<>();
        boolean[] v = new boolean[n+1];

        q.add(new Node(s,0));
        v[s] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(Node next : graph[cur.v]){

                if(!v[next.v]){
                    v[next.v] = true;
                    dp[s][next.v] = Math.max(cur.c,next.c);
                    q.add(new Node(next.v,dp[s][next.v]));
                }
            }
        }
    }

    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
class Point{
    int v1;
    int v2;
    int c;

    Point(int v1,int v2,int c){
        this.v1 = v1;
        this.v2 = v2;
        this.c = c;
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
