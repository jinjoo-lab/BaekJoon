import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int k = 0;

    static ArrayList<Node>[] graph;

    static int result = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());


            if(c > k)
                continue;

            graph[s].add(new Node(e,l,c));
        }


        search();
        bf.close();
    }

    static void search(){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[][] dis = new int[n+1][k+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<=k;j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        dis[1][0] = 0;
        pq.add(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur =pq.poll();

            if(cur.v == n){
                continue;
            }

            if(dis[cur.v][cur.c] < cur.l)
                continue;


            for(Node next : graph[cur.v]){

                if(cur.c + next.c > k)
                    continue;

                if(dis[next.v][cur.c + next.c] <= dis[cur.v][cur.c] + next.l) {
                    continue;
                }



                dis[next.v][cur.c + next.c] = dis[cur.v][cur.c] + next.l;
                pq.add(new Node(next.v,dis[next.v][cur.c + next.c],cur.c + next.c));
            }
        }


        result = dis[n][0];

        for(int i=1;i<=k;i++){
            result = Math.min(result,dis[n][i]);
        }

        if(result == Integer.MAX_VALUE)
            result = -1;

        System.out.println(result);
    }
}
class Node{
    int v;
    int l;
    int c;

    Node(int v,int l,int c){
        this.v = v;
        this.l = l;
        this.c = c;
    }
}