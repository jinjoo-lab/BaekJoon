import java.util.*;
import java.io.*;

public class Main {

    static int tt;
    static int n,m;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);

        tt = sc.nextInt();

        for(int t = 1 ; t <= tt ; t++) {
            //st = new StringTokenizer(br.readLine()," ");
            n = sc.nextInt();
            m = sc.nextInt();

            graph = new ArrayList[m];

            for(int i = 0 ; i < m ; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i = 1 ; i <= n ; i++) {
                //st = new StringTokenizer(br.readLine()," ");

                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int c = sc.nextInt();

                graph[v1].add(new Node(v2,c));
                graph[v2].add(new Node(v1,c));
            }

            String result = go();
            sb.append("Case #"+t+": "+result);
        }

        System.out.print(sb);

        //br.close();
    }

    static String go() {
        int[] dis = new int[m];
        int[] path = new int[m];

        for(int i = 0 ; i < m ; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        dis[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Node(0,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]) {
                if(dis[next.v] > cur.c + next.c) {
                    dis[next.v] = cur.c + next.c;
                    path[next.v] = cur.v;
                    pq.add(new Node(next.v,dis[next.v]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if(dis[m-1] == Integer.MAX_VALUE) {
            sb.append("-1\n");
        }else {
            Stack<Integer> stack = new Stack<>();

            sb.append("0 ");

            int idx = m - 1;
            while(idx != 0) {
                stack.push(idx);
                idx = path[idx];
            }

            while(!stack.isEmpty()) {
                sb.append(stack.pop()+" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    static class Node{
        int v;
        int c;

        Node(int v,int c) {
            this.v = v;
            this.c = c;
        }
    }
}
