import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[n+1][n+1];
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }

        for(int i=1;i<=n;i++){
            search(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j)
                    sb.append("- ");
                else
                    sb.append(result[i][j]+" ");
            }sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void search(int start){
        int[] dis = new int[n+1];
        int[] past = new int[n+1];

        for(int i=1;i<=n;i++){
            dis[i] = Integer.MAX_VALUE;
        }
        dis[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.v]){
                if(dis[next.v] > dis[cur.v] + next.c){
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v]));

                    if(cur.v == start) {
                        past[next.v] = next.v;
                    }

                    else{
                        past[next.v] = past[cur.v];
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            if(past[i] == start){
                past[i] = i;
            }

            result[start][i] = past[i];
        }
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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
