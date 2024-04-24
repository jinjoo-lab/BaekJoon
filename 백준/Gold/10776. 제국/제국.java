import java.util.*;
import java.io.*;

public class Main {

    static int k,n,m,s,e;

    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,t,h));
            graph[v2].add(new Node(v1,t,h));
        }

        st = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        search();

        br.close();
    }

    static void search(){

        int result = -1;

        int[][] dis = new int[n+1][201];

        for(int i = 1 ; i <= n ; i ++){
            for(int j = 1; j <= k ; j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        dis[s][k] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.t - y.t
        );

        pq.add(new Node(s,0,k));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.v == e){
                result = cur.t;
                break;
            }

            if(dis[cur.v][cur.h] < cur.t)
                continue;

            for(Node next : graph[cur.v]){
                int nextH = cur.h - next.h;

                if(nextH <= 0)
                    continue;

                if(dis[next.v][nextH] > cur.t + next.t){
                    dis[next.v][nextH] = cur.t + next.t;
                    pq.add(new Node(next.v,dis[next.v][nextH],nextH));

                    for(int tmpH = nextH - 1 ; tmpH >= 1 ; tmpH--){
                        if(dis[next.v][tmpH] > dis[next.v][nextH]){
                            dis[next.v][tmpH] = dis[next.v][nextH];
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(result);

    }

    static class Node{
        int v;
        int t;
        int h;

        Node(int v,int t,int h){
            this.v = v;
            this.t = t;
            this.h = h;
        }
    }
}
