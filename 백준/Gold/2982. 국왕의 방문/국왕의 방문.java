
import java.util.*;
import java.io.*;

public class Main {

    static int result = 0;
    static int n,m;
    static int a,b,k,g;

    static int[] gMove;

    static ArrayList<Edge>[] graph;

    static boolean[][] isG;

    static int[][][] gTime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        isG = new boolean[n+1][n+1];
        gTime = new int[n+1][n+1][2];

        for(int i =1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine()," ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        gMove = new int[g + 1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= g ; i++){
            gMove[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Edge(v2,c));
            graph[v2].add(new Edge(v1,c));
        }

        kMove();
        sMove();
        System.out.println(result);


        br.close();
    }

    static int INF = Integer.MAX_VALUE;

    static void sMove(){
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[] dis = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            dis[i] = INF;
        }

        dis[a] = k;
        pq.add(new Edge(a,k));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            int curV = cur.v;
            int curC = cur.c;

            if(curV == b){
                result = dis[b] - dis[a];
                continue;
            }

            for(Edge next : graph[cur.v]){

                int nextV = next.v;
                int nextC = next.c;

                if(isG[cur.v][nextV]){

                    if(curC >= gTime[cur.v][nextV][0] && curC < gTime[cur.v][nextV][1]){
                        int tmpC = gTime[cur.v][nextV][1];

                        if (dis[nextV] > tmpC + nextC){
                            dis[nextV] = tmpC + nextC;
                            pq.add(new Edge(nextV,dis[nextV]));
                        }
                    }


                    else if(dis[nextV] > dis[cur.v] + nextC){
                        dis[nextV] = dis[cur.v] + nextC;
                        pq.add(new Edge(nextV,dis[nextV]));
                    }

                }else{
                    if(dis[nextV] > dis[cur.v] + nextC){
                        dis[nextV] = dis[cur.v] + nextC;
                        pq.add(new Edge(nextV,dis[nextV]));
                    }
                }
            }
        }

    }

    static void kMove (){

        int time = 0;

        for(int i = 1 ; i < g; i++){
            int curP = gMove[i];
            int nextP = gMove[i + 1];

            isG[curP][nextP] = true;
            isG[nextP][curP] = true;

            for(Edge next : graph[curP]){
                if(next.v == nextP) {
                    gTime[curP][nextP][0] = time;
                    gTime[curP][nextP][1] = time + next.c;

                    gTime[nextP][curP][0] = time;
                    gTime[nextP][curP][1] = time + next.c;

                    time += next.c;
                    break;
                }
            }

            //System.out.println(curP+" "+nextP+" : "+gTime[curP][nextP][0] +" ~ "+gTime[curP][nextP][1]);
        }
    }

    static class Edge{
        int v;
        int c;

        Edge(int v,int c){
            this.v = v;
            this.c = c;
        }
    }
}
