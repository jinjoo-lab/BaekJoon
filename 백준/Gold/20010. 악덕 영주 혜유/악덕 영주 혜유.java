import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    static boolean[] v;

    static int maxNode = 0;

    static ArrayList<Node>[] graph;

    static int max = Integer.MIN_VALUE;


    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n];
        graph = new ArrayList[n];

        for(int i=0;i<n;i++){
            root[i] = i;
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Edge(x,y,v));
        }

        int count = 0;
        int result = 0;

        while(!pq.isEmpty()){

            Edge cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                count += 1;
                result += cur.c;
                graph[cur.v1].add(new Node(cur.v2,cur.c));
                graph[cur.v2].add(new Node(cur.v1,cur.c));
            }

            if(count == n-1){
                break;
            }
        }

        max = Integer.MIN_VALUE;
        v = new boolean[n];
        dfs(0,0);
        max = Integer.MIN_VALUE;
        v = new boolean[n];
        dfs(maxNode,0);
        
        System.out.println(result);
        System.out.println(max);
        br.close();
    }

    static void dfs(int cur,int c){
        v[cur] = true;

        if(max < c){
            max = c;
            maxNode = cur;
        }

        for(Node next : graph[cur]){
            if(!v[next.v]){
                dfs(next.v,c + next.d);
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

class Edge{
    int v1;
    int v2;
    int c;

    Edge(int v1,int v2,int c){
        this.v1 = v1;
        this.v2 = v2;
        this.c = c;
    }
}
class Node{
    int v;
    int d;

    Node(int v,int d){
        this.v = v;
        this.d = d;
    }
}
