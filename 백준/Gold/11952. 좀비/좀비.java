import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int s = 0;

    static int p = 0;
    static int q = 0;

    static int[] zombie;

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        zombie = new int[n+1];

        for(int i=1;i<=k;i++){
            int tmp = Integer.parseInt(br.readLine());
            zombie[tmp] = 1;
        }

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());


            graph[x].add(y);
            graph[y].add(x);
        }

        danger();



        search();

        br.close();
    }
    static void danger(){
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[n+1];

        for(int i=1;i<=n;i++){
            if(zombie[i] == 1){
                q.add(i);
                v[i] = 1;
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            if(v[cur] -1 >= s){
                continue;
            }


            for(int next : graph[cur]){
                if(v[next] == 0 || v[next] > v[cur] + 1){
                    v[next] = v[cur] + 1;
                    zombie[next] = 2;
                    q.add(next);
                }
            }
        }
    }
    static void search(){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );
        boolean[] v = new boolean[n+1];
        long[] dis = new long[n+1];

        for(int i=1;i<=n;i++){
            dis[i] = Long.MAX_VALUE;
        }

        dis[1] = 0;

        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == n)
                continue;


            for(int next : graph[cur.v]){
                if(zombie[next] == 2){
                    if(dis[next] > dis[cur.v] + q){
                        dis[next] = dis[cur.v] + q;
                        pq.add(new Node(next,dis[next]));
                    }
                }else if(zombie[next] == 0){
                    if(dis[next] > dis[cur.v] + p){
                        dis[next] = dis[cur.v] + p;
                        pq.add(new Node(next,dis[next]));
                    }
                }
            }
        }

        if(zombie[n] == 0){
            dis[n] -= p;
        }else if(zombie[n] == 2){
            dis[n] -= q;
        }

        System.out.println(dis[n]);
    }
}
class Node{
    int v;
    long c;

    Node(int v,long c){
        this.v = v;
        this.c = c;
    }
}
