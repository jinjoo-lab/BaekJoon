import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int r = 0;

    static int[] item;

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n+1];
        graph = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            item[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }


        for(int i=1;i<=r;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,v));
            graph[b].add(new Node(a,v));
        }

        int result = 0;
        for(int i=1;i<=n;i++){
           result = Math.max(result,search(i));
        }

        System.out.println(result);
        br.close();
    }

    static int search(int start){

        int result = 0;
        boolean[] visit = new boolean[n+1];
        int[] dis = new int[n+1];
        for(int i=1;i<=n;i++){
            if(i == start)
                continue;

            dis[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Node(start,0));


        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visit[cur.v])
                continue;

            visit[cur.v] = true;

            for(Node next : graph[cur.v]){
                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        for(int i=1;i<=n;i++){
            if(dis[i] <= m)
                result += item[i];
        }

        return result;
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
