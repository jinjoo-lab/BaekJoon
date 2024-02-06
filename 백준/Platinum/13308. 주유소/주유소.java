import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        price = new int[n+1];
        graph = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine()," ");

        for(int i=1;i<=n;i++){
            price[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }


        search();

        br.close();
    }

    static void search() {

        long [][] dp = new long [n + 1][2501];

        PriorityQueue<Point> pq = new PriorityQueue<>(
                (x, y) -> Long.compare(x.tc,y.tc)
        );

        pq.add(new Point(1, 0, price[1]));

        for (int i = 1; i <= n; i++) {
            for(int j= 0 ;j <=2500 ; j++){
                dp[i][j] = Long.MAX_VALUE;
            }
        }

        dp[1][price[1]] = 0;

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(cur.v == n){
                System.out.println(cur.tc);
                return;
            }

            if(dp[cur.v][cur.mc] > cur.tc){
                dp[cur.v][cur.mc] = cur.tc;
            }


            int minOil = Math.min(cur.mc,price[cur.v]);

            for(Node next : graph[cur.v]){

                if(dp[next.v][minOil] > dp[cur.v][cur.mc] + (minOil * next.d)){
                    dp[next.v][minOil] = dp[cur.v][cur.mc] + (minOil * next.d);
                    pq.add(new Point(next.v,dp[next.v][minOil],minOil));
                }

            }
        }

    }
}
class Point{
    int v;
    long tc;
    int mc;

    Point(int v,long tc,int mc){
        this.v = v;
        this.tc = tc;
        this.mc = mc;
    }
}

class Node{
    int v;
    int d;

    Node(int v,int d){
        this.v = v;
        this.d = d;
    }
}
