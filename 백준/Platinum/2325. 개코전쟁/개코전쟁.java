import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static int[] pathList;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c,i));
            graph[v2].add(new Node(v1,c,i));
        }

        first();

        int start = n;

        while(start != 0){

            result(start,pathList[start]);

            start = pathList[start];

            if(start == 0)
                break;
        }

        System.out.println(result);
        bf.close();
    }

    static void first(){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[] path = new int[n+1];
        int[] dis = new int[n+1];

        for(int i=2;i<=n;i++){
            dis[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == n){
                break;
            }

            for(Node next : graph[cur.v]){

                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    path[next.v] = cur.v;
                    pq.add(new Node(next.v,dis[next.v],next.num));
                }
            }
        }

        pathList = path;
    }

    static void result(int cx,int cy){


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

            if(cur.v == n){
                break;
            }

            for(Node next : graph[cur.v]){

                if((cur.v == cx && next.v == cy) || (cur.v == cy && next.v == cx))
                    continue;

                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v],next.num));
                }
            }
        }

        result = Math.max(result,dis[n]);

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
