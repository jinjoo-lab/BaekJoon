
import java.util.*;
import java.io.*;

public class Main {

    static long[] foxDis;

    static long[][] wolfDis;
    static ArrayList<Node>[] graph;
    static int n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        foxDis = new long[n+1];
        wolfDis = new long[n+1][2];

        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) * 2;

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        foxMove();
        wolfMove();

        int count = 0;
        for(int i = 2 ; i <= n ; i++){
            if(foxDis[i] < Math.min(wolfDis[i][0],wolfDis[i][1])){
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }
    static void wolfMove(){
        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        for(int i = 2 ; i <= n ; i++){
            for(int j = 0 ; j <= 1 ; j++){
                wolfDis[i][j] = Long.MAX_VALUE;
            }
        }

        wolfDis[1][1] = Long.MAX_VALUE;

        pq.add(new Point(1,0,0));

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(wolfDis[cur.v][cur.type] < cur.c)
                continue;

            for(Node next : graph[cur.v]){
                int nextState = 1 - cur.type;
                long nextDis = cur.c + ((cur.type == 0) ? next.c / 2 : next.c * 2);

                if(nextDis < wolfDis[next.v][nextState]){
                    wolfDis[next.v][nextState] = nextDis;
                    pq.add(new Point(next.v,nextDis,nextState));
                }
            }
        }
    }
    static void foxMove(){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        for(int i = 1 ; i <= n ; i++){
            foxDis[i] = Long.MAX_VALUE;
        }

        foxDis[1] = 0;
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(foxDis[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]){
                if(foxDis[next.v] <= cur.c + next.c){
                    continue;
                }

                foxDis[next.v] = cur.c + next.c;
                pq.add(new Node(next.v,foxDis[next.v]));
            }
        }
    }

    static int change(int cur){
        if(cur == 1)
            return 0;

        return 1;
    }

    static void print(int[][] dis){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j<= 1 ; j++){
                System.out.print(dis[i][j]+" ");
            }System.out.println();
        }
        System.out.println();
    }
    static void print(int[] dis){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(dis[i]+" ");
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

    static class Point{
        int v;
        long c;
        int type;

        Point(int v,long c,int type){
            this.v = v;
            this.c = c;
            this.type = type;
        }
    }
}
