
import java.util.*;
import java.io.*;

public class Main {
    static int n,m,c;
    static ArrayList<Node>[] graph;
    static long[] dis;
    static long all;

    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        dis = new long[n+1];

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

            all += c;
        }

        search();
        System.out.println(result);
        br.close();
    }


    static void search(){

        boolean[] v = new boolean[n+1];

        dis[1] = 0l;
        for(int i = 2 ; i <= n ; i++){
            dis[i] = Long.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        pq.add(new Node(1,0));


        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(v[cur.v])
                continue;

            v[cur.v] = true;

            for(Node next : graph[cur.v]){
                if(v[next.v])
                    all -= next.c;
            }

            long tmpV = all + (dis[cur.v] * c);
            result = Math.min(result,tmpV);

            for(Node next : graph[cur.v]){
                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        //print(path);
    }

    static void print(int[][] dis){
        for(int i =1 ; i <= n ; i++){
            System.out.print(dis[i][0]+" : "+dis[i][1]+" , ");
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
