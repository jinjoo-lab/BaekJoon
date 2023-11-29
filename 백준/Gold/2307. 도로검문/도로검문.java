import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static int[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,v,i));
            graph[y].add(new Node(x,v,i));
        }

        int result = 0;
        int shortestPath = findPath();



        for(int i=2;i<=n;i++){
            int tmp = banAndFind(i);

            if(tmp == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }

            if(tmp - shortestPath > result){
                result = tmp - shortestPath;
            }
        }

        System.out.println(result);
        bf.close();
    }

    static int findPath(){

        path = new int[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[] dis = new int[n+1];

        for(int i=2;i<=n;i++){
            dis[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.v]){
                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    path[next.v] = cur.v;
                    pq.add(new Node(next.v,dis[next.v],next.num));
                }
            }
        }

        return dis[n];
    }

    static int banAndFind(int ban){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[] dis = new int[n+1];

        for(int i=2;i<=n;i++){
            dis[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.v]){

                if((cur.v == ban && next.v == path[ban]) || (cur.v == path[ban] && next.v == ban)){
                    continue;
                }

                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v],next.num));
                }
            }
        }

        return dis[n];
    }
}
class Node{
    int v;
    int c;

    int num;

    Node(int v,int c,int num){
        this.v = v;
        this.c = c;
        this.num = num;
    }
}
