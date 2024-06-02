import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static double r;

    static PriorityQueue<Point> pq = new PriorityQueue<>(
            (x,y) -> Double.compare(x.c,y.c)
    );
    static int[] path;
    static HashMap<String,Integer> map1 = new HashMap<>();

    static double[][] dis;

    static double[][] tDis;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        r = Double.parseDouble(st.nextToken());

        graph = new ArrayList[n+1];
        dis = new double[n+1][n+1];
        tDis = new double[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(i == j)
                    continue;

                dis[i][j] = Double.MAX_VALUE;
                tDis[i][j] = Double.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            String cur = st.nextToken();
            map1.put(cur,i);
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        path = new int[m+1];

        for(int i = 1 ; i <= m ; i++) {
            path[i] = map1.get(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= k ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            String w = st.nextToken();
            int v1 = map1.get(st.nextToken());
            int v2 = map1.get(st.nextToken());
            double c = Double.parseDouble(st.nextToken());

            graph[v1].add(new Node(w,v2,c));
            graph[v2].add(new Node(w,v1,c));
        }

        for(int i = 1 ; i <= n ; i++) {
            notUse(i);
            use(i);
        }

        double noUseResult = 0;
        double useResult = 0;

        for(int i = 1 ; i < m ; i++) {
            noUseResult += dis[path[i]][path[i+1]];
            useResult += tDis[path[i]][path[i+1]];
        }


        if(noUseResult > useResult + r) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }



        br.close();
    }

    static void use(int idx) {
        pq.clear();
        pq.add(new Point(idx,0));

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            for(Node next : graph[cur.v]) {
                double nextC = next.c;
                String nextW = next.w;

                if(nextW.equals("Mugunghwa") || nextW.equals("ITX-Saemaeul") || nextW.equals("ITX-Cheongchun")) {
                    nextC = 0;
                }else if(nextW.equals("S-Train") || nextW.equals("V-Train")) {
                    nextC /= 2;
                }

                if(tDis[idx][next.v] > cur.c + nextC) {
                    tDis[idx][next.v] = cur.c + nextC;
                    pq.add(new Point(next.v,tDis[idx][next.v]));
                }
            }
        }
    }

    static void print(int[][] dis) {
        for(int i =1 ; i <= n ; i++) {
            for(int j =1 ; j <= n ; j++) {
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void notUse(int idx) {
        pq.clear();
        pq.add(new Point(idx,0));

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            for(Node next : graph[cur.v]) {
                if(dis[idx][next.v] > cur.c + next.c) {
                    dis[idx][next.v] = cur.c + next.c;
                    pq.add(new Point(next.v,dis[idx][next.v]));
                }
            }
        }
    }

    static class Point {
        int v;
        double c;

        Point(int v,double c) {
            this.v = v;
            this.c = c;
        }
    }

    static class Node {
        String w;
        int v;
        double c;

        Node(String w,int v,double c) {
            this.w = w;
            this.v = v;
            this.c = c;
        }
    }
}
