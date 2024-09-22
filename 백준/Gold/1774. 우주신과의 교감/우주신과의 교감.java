import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] root;
    static double[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new double[n+1][2];

        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;
        }

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            graph[i][0] = x;
            graph[i][1] = y;
        }

        int count = n - 1;

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x,y);

            count--;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Double.compare(x.dis,y.dis)
        );

        for(int i = 1 ; i <= n ; i++) {
            for(int j = i + 1 ; j <= n ; j++) {
                pq.add(new Node(i,j,calDis(i,j)));
            }
        }

        double result = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            

            if(find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                result += cur.dis;
                count--;
            }
        }

        System.out.printf("%.2f",result);



        br.close();
    }

    static double calDis(int idx1, int idx2) {
        double x1 = graph[idx1][0];
        double y1 = graph[idx1][1];
        double x2 = graph[idx2][0];
        double y2 = graph[idx2][1];

        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;

        else
            root[x] = y;
    }

    static class Node {
        int v1;
        int v2;
        double dis;

        Node(int v1,int v2,double dis) {
            this.v1 = v1;
            this.v2 = v2;
            this.dis = dis;
        }
    }
}
