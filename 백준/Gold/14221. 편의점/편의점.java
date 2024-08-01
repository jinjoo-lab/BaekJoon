import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static ArrayList<Node>[] graph;

    static int p,q;

    static int[] pArr;
    static int[] qArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        st = new StringTokenizer(br.readLine()," ");
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        pArr = new int[p+1];
        qArr = new int[q+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= p ; i++) {
            pArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pArr,1,p+1);

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= q ; i++) {
            qArr[i] = Integer.parseInt(st.nextToken());
        }

        search();

        br.close();
    }

    static void search() {
        int[] dis = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        for(int i = 1 ; i <= n ; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        for(int i = 1 ; i <= q ; i++) {
            dis[qArr[i]] = 0;
            pq.add(new Node(qArr[i],0));
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]) {
                if(dis[next.v] > cur.c + next.c) {
                    dis[next.v] = cur.c + next.c;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        int reIdx = pArr[1];
        int reV = dis[pArr[1]];

        for(int i = 1 ; i <= p ; i++) {

            int curIdx = pArr[i];

            if(dis[curIdx] < reV) {
                reIdx = curIdx;
                reV = dis[curIdx];
            }
        }

        System.out.println(reIdx);
    }

    static class Node {
        int v;
        int c;

        Node(int v,int c) {
            this.v = v;
            this.c = c;
        }
    }
}
