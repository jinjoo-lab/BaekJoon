import java.util.*;
import java.io.*;

public class Main {

    static int n,m,s,e;
    static long c;

    static long min = Long.MAX_VALUE,max = Long.MIN_VALUE;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

        graph = new ArrayList[n + 1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));

            min = Math.min(min,c);
            max = Math.max(max,c);
        }

        long mid;
        long result = -1;

        while(min <= max) {
            mid = (min + max) / 2;

            if(go(mid)) {
                result = mid;
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }

        System.out.println(result);

        br.close();
    }

    static boolean go(long target) {
        long[] v = new long[n+1];
        for(int i = 1 ; i <= n ; i++) {
            v[i] = Long.MAX_VALUE;
        }

        PriorityQueue<Node> q = new PriorityQueue<>(
                (x,y) -> Long.compare(x.c,y.c)
        );

        q.add(new Node(s,0));
        v[s] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.v == e) {
                return true;
            }
            
            if(v[cur.v] < cur.c)
                continue;

            for(Node next : graph[cur.v]) {
                if(next.c > target || cur.c + next.c > c) {
                    continue;
                }

                if(v[next.v] > v[cur.v] + next.c) {
                    v[next.v] = v[cur.v] + next.c;
                    q.add(new Node(next.v, cur.c + next.c));
                }
            }
        }


        return false;
    }


    static class Node {
        int v;
        long c;

        Node(int v, long c) {
            this.v = v;
            this.c = c;
        }
    }
}
