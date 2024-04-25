import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k,s,d;
    static int[][] dis;
    static int[] up;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c,0));
            graph[v2].add(new Node(v1,c,0));
        }

        up = new int[k+1];
        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int tmpNum = Integer.parseInt(st.nextToken());
            up[i] = tmpNum;
        }

        search();

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <= k  ; i++){
            int curUp = up[i];
            int max = Integer.MAX_VALUE;

            for(int j = 1 ; j <= n ; j++){

                if(dis[d][j] == Integer.MAX_VALUE)
                    continue;

                int upper = (j - 1) * curUp;

                dis[d][j] += upper;

                max = Math.min(max,dis[d][j]);
            }

            sb.append(max+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void search(){
        dis = new int[n+1][n+2]; // i번째 지점에 j개의 간선을 딛고 온 경으
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j <= n ; j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0 ; i <= n ; i++){
            dis[s][i] = 0;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Node(s,0,1));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == d){
                continue;
            }

            if(dis[cur.v][cur.count] < cur.c)
                continue;

            for(Node next : graph[cur.v]){
                int nextC = cur.c + next.c;
                int nextCount = cur.count + 1;

                if(dis[next.v][nextCount] > nextC){
                    dis[next.v][nextCount] = nextC;
                    pq.add(new Node(next.v,nextC,nextCount));
                }

                for(int tmpC = nextCount + 1 ; tmpC <= n ; tmpC++){
                    if(dis[next.v][tmpC] > nextC){
                        dis[next.v][tmpC] = nextC;
                    }
                }
            }
        }
    }



    static class Node{
        int v;
        int c;
        int count;

        Node(int v,int c,int count){
            this.v = v;
            this.c = c;
            this.count = count;
        }
    }
}
