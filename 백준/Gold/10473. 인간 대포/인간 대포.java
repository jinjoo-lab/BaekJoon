import java.util.*;
import java.io.*;

public class Main {

    static double sx,sy,lx,ly;
    static int n;

    static double DIS = 50.000000;

    static Node[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        sx = Double.parseDouble(st.nextToken());
        sy = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        lx = Double.parseDouble(st.nextToken());
        ly = Double.parseDouble(st.nextToken());

        n = Integer.parseInt(br.readLine());

        edges = new Node[n+2];

        edges[0] = new Node(sx,sy);

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            edges[i] = new Node(x,y);
        }

        edges[n+1] = new Node(lx,ly);

        System.out.println(go());

        br.close();
    }

    static double go() {
        double[] dis = new double[n+2];
        for(int i = 1; i <= n+1 ; i++) {
            dis[i] = Double.MAX_VALUE;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x,y) -> Double.compare(x.sec,y.sec)
        );

        for(int i = 1 ; i <= n + 1 ; i++) {
            double tmpDis = getDis(sx,sy,edges[i].x,edges[i].y);

            dis[i] = Math.min(dis[i],tmpDis / 5.0);

            if(i <= n) {
                pq.add(new Point(i,dis[i]));
            }
        }

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            for(int i = 1 ; i <= n + 1 ; i++) {
                if(cur.idx == i)
                    continue;

                double tmpSec = 0;
                double tmpDis = getDis(edges[cur.idx].x,edges[cur.idx].y,edges[i].x,edges[i].y);
                double wSec = tmpDis / 5.0;

                if(tmpDis > DIS) {
                    tmpSec += 2.0;
                    tmpDis -= DIS;
                    tmpSec += (tmpDis / 5.0);
                }else if(tmpDis == DIS) {
                    tmpSec += 2.0;
                }else{
                    tmpSec += 2.0;
                    double tmpDis2 = DIS - tmpDis;
                    tmpSec += (tmpDis2 / 5.0);
                }

                if(dis[i] > cur.sec + Math.min(wSec,tmpSec)) {
                    dis[i] = cur.sec + Math.min(wSec,tmpSec);

                    if(i <= n) {
                        pq.add(new Point(i,dis[i]));
                    }
                }
            }
        }

        return dis[n+1];
    }

    static void print(double[] dis) {
        for(int i = 1 ; i <= n + 1; i++) {
            System.out.print(dis[i]+" ");
        }
        System.out.println();
    }


    static double getDis(double x,double y,double x1,double y1) {
        double tmpX = Math.pow(x - x1,2);
        double tmpY = Math.pow(y - y1,2);

        return Math.sqrt(tmpX + tmpY);
    }

    static class Point {
        int idx;
        double sec;

        Point(int idx,double sec) {
            this.idx = idx;
            this.sec = sec;
        }
    }

    static class Node {
        double x;
        double y;

        Node(double x,double y) {
            this.x = x;
            this.y = y;
        }
    }
}


