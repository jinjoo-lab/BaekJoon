import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static double r,d,x,y;

    static double[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        r = Double.parseDouble(st.nextToken());
        d = Double.parseDouble(st.nextToken());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());


        graph = new double[n+1][2];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            graph[i][0] = Double.parseDouble(st.nextToken());
            graph[i][1] = Double.parseDouble(st.nextToken());
        }

        boolean[] v = new boolean[n+1];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y,0));
        double result = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i = 1 ; i <= n ; i++) {

                boolean canGo = cal(cur.x,cur.y,graph[i][0],graph[i][1]);

                if(canGo && !v[i]) {
                    v[i] = true;

                    if(cur.count == 0) {
                        result += d;
                    }
                    else {
                        result += (d / Math.pow(2,cur.count));
                    }
                    q.add(new Node(graph[i][0],graph[i][1],cur.count + 1));
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static boolean cal(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) <= r;
    }

    static class Node {
        double x;
        double y;
        int count;

        Node(double x, double y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
