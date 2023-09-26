import java.util.*;
import java.io.*;

public class Main {
    static int k = 0;
    static int n = 0;
    static int m = 0;
    static int res = 0;
    static ArrayList<Node>[] graph;

    static int[] dis;
    static boolean[] walk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        StringBuilder result = new StringBuilder();
        for(int i=1;i<=k;i++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            res = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int j=1;j<=n;j++){
                graph[j] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for(int j=1;j<=m;j++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());


                graph[x].add(new Node(y,c));
                graph[y].add(new Node(x,c));
            }

            PriorityQueue<Integer> reQ = new PriorityQueue<>(
                    (x,y) -> x - y
            );
            for(int j=1;j<=res;j++){
                int tmp = Integer.parseInt(br.readLine());
                reQ.add(tmp);
            }

            result.append(search(start,g,h,reQ));
        }
        System.out.print(result);

        br.close();
    }

    static String search(int start,int g,int h,PriorityQueue<Integer> reQ){
        dis = new int[n+1];
        walk = new boolean[n+1];

        for(int i=1;i<=n;i++){
            dis[i] = 1000000001;
        }
        dis[start] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(!x.w){
                        if(!y.w) return x.c - y.c;

                        else{
                            return 1;
                        }
                    }

                    else{
                        if(!y.w) return -1;

                        else
                            return x.c - y.c;
                    }
                }
        );
        pq.add(new Point(start,0,false));

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            for(Node next : graph[cur.v]){
                
                if(dis[next.v] == dis[cur.v] + next.c){
                    if(!walk[next.v] && (walk[cur.v] ||((cur.v == g || cur.v == h) && (next.v == g || next.v == h)))){
                        pq.add(new Point(next.v,dis[next.v],true));
                        walk[next.v] = true;
                    }
                }
                
                
                if(dis[next.v] > dis[cur.v] + next.c){
                    if(walk[cur.v] ||((cur.v == g || cur.v == h) && (next.v == g || next.v == h) )){
                        dis[next.v] = dis[cur.v] + next.c;
                        pq.add(new Point(next.v,dis[next.v],true));
                        walk[next.v] = true;
                    }

                    else if(dis[next.v] > dis[cur.v] + next.c){
                        dis[next.v] = dis[cur.v] + next.c;
                        walk[next.v] = false;
                        pq.add(new Point(next.v,dis[next.v],false));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!reQ.isEmpty()){
            int tmp = reQ.poll();

            if(dis[tmp] < 1000000001 && walk[tmp]){
                sb.append(tmp+" ");
            }
        }
        sb.append("\n");

        return sb.toString();
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
class Point{
    int v;
    int c;

    boolean w;

    Point(int v,int c,boolean w){
        this.v = v;
        this.c = c;
        this.w = w;
    }
}