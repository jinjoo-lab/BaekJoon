import java.util.*;
import java.io.*;

public class Main {
    static int n,m,s,e;

    static int MAX = 1000000009;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        search();

        br.close();
    }

    static void search(){
        long[][] dis = new long[n+1][2];

        for(int i = 1 ; i <= n ; i++){
            dis[i][0] = Long.MAX_VALUE;
        }

        dis[s][0] = 0;
        dis[s][1] = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        pq.add(new Node(s,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == e){
                continue;
            }

            if(dis[cur.v][0] < cur.c)
                continue;

            for(Node next : graph[cur.v]){
                if(dis[next.v][0] > cur.c + next.c){
                    dis[next.v][0] = cur.c + next.c;
                    dis[next.v][1] = dis[cur.v][1];
                    pq.add(new Node(next.v,dis[next.v][0]));
                }else if(dis[next.v][0] == cur.c + next.c){
                    dis[next.v][1] = (dis[next.v][1] + dis[cur.v][1]) % MAX;
                }
            }
        }

        System.out.println(dis[e][1]);

    }

    static void print(long[][] dis){
        for(int i = 1 ; i <= n ; i++){
            System.out.print("("+dis[i][0]+","+dis[i][1]+")");
        }
        System.out.println();
    }

    static class Node{
        int v;
        long c;

        Node(int v,long c){
            this.v = v;
            this.c = c;
        }
    }
}