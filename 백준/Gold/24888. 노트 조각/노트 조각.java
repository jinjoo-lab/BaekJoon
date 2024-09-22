import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static ArrayList<Node>[] graph;
    static int[] isIt;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        isIt = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,c));
            graph[y].add(new Node(x,c));
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            isIt[i] = Integer.parseInt(st.nextToken());
            totalCount += isIt[i];
        }

        go();

        br.close();
    }


    static void go() {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        int[] path = new int[n+1];
        int[] count = new int[n+1];
        long[] dis = new long[n+1];
        for(int i = 2 ; i <= n ; i++) {
            dis[i] = Long.MAX_VALUE;
        }

        pq.add(new Node(1,0));

        count[1] = isIt[1];

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.v == n)
                continue;

            if(dis[cur.v] < cur.c)
                continue;


            for(Node next : graph[cur.v]) {
                if(dis[next.v] > dis[cur.v] + next.c) {
                    dis[next.v] = dis[cur.v] + next.c;
                    pq.add(new Node(next.v,dis[next.v]));

                    if(count[next.v] <= count[cur.v] + isIt[next.v]) {
                        count[next.v] = count[cur.v] + isIt[next.v];
                        path[next.v] = cur.v;
                    }
                }else if(dis[next.v] == dis[cur.v] + next.c) {
                    if(count[next.v] <= count[cur.v] + isIt[next.v]) {
                        count[next.v] = count[cur.v] + isIt[next.v];
                        path[next.v] = cur.v;
                    }
                }
            }
        }

        if(count[n] == totalCount) {
            Stack<Integer> stack = new Stack<>();
            stack.push(n);
            int tmp = n;

            while(true) {
                tmp = path[tmp];

                if(tmp == 0)
                    break;

                stack.push(tmp);
            }

            System.out.println(stack.size());
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop()+" ");
            }
            System.out.println(sb);
        }else {
            System.out.println(-1);
        }
    }


    static class Node {
        int v;
        long c;

        Node(int v,long c) {
            this.v = v;
            this.c = c;
        }
    }

    static class Point {
        int v;
        int num;


        Point(int v, int num) {
            this.v = v;
            this.num = num;
        }
    }
}
