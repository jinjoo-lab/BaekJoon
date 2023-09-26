import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }
        search();
        br.close();
    }

    static void search(){
        long[][] dis = new long[n+1][k+1];
        for(int i=2;i<=n;i++){
            for(int j=0;j<=k;j++){
                dis[i][j] = Long.MAX_VALUE;
            }
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        pq.add(new Point(1,0,0));

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(dis[cur.v][cur.num] < cur.c)
                continue;

            for(Node next : graph[cur.v]){
                if(dis[next.v][cur.num] > dis[cur.v][cur.num] + next.c){
                    dis[next.v][cur.num] = dis[cur.v][cur.num] + next.c;
                    pq.add(new Point(next.v,dis[next.v][cur.num],cur.num));
                }

                if(cur.num < k && dis[next.v][cur.num+1] > dis[cur.v][cur.num]){
                    dis[next.v][cur.num+1] = dis[cur.v][cur.num];
                    pq.add(new Point(next.v,dis[next.v][cur.num+1],cur.num+1));
                }
            }
        }

        long result = dis[n][0];
        for(int i=1;i<=k;i++){
            result = Math.min(result,dis[n][i]);
        }
        System.out.println(result);

    }
}
class Point{
    int v;
    long c;
    int num;

    Point(int v,long c,int num){
        this.v = v;
        this.c = c;
        this.num = num;
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
