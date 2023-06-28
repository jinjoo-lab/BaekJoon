import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;
    static int[] root;
    static boolean[] isCenter;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        root = new int[n + 1];
        isCenter = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= k; i++) {
            isCenter[Integer.parseInt(st.nextToken())] = true;
        }

        PriorityQueue<Edge> cpq = new PriorityQueue<>(
                (x, y) -> x.v - y.v
        );

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (x, y) -> x.v - y.v
        );

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());


            pq.add(new Edge(x, y, v));

        }

        while (!pq.isEmpty()) {
            if (all())
                break;

            Edge cur = pq.poll();

            if (isCenter[cur.x] && isCenter[cur.y])
                continue;

            if (find(cur.x) != find(cur.y)) {

                if (isCenter[root[cur.x]] && isCenter[root[cur.y]])
                    continue;
                
                result += cur.v;
                union(cur.x, cur.y);
                cunion(cur.x, cur.y);
            }

        }

        System.out.println(result);
        br.close();
    }

    static void print() {
        for (int i = 1; i <= n; i++) {
            if (isCenter[i])
                System.out.print("1 ");
            else
                System.out.print("2 ");
        }
        System.out.println();
    }

    static boolean all() {
        for (int i = 1; i <= n; i++) {
            if (!isCenter[i])
                return false;
        }

        return true;
    }


    static int find(int x) {
        if (isCenter[x] || isCenter[root[x]]) {
            isCenter[x] = true;
            isCenter[root[x]] = true;
        }

        if (root[x] == x)
            return x;

        else
            return root[x] = find(root[x]);
    }

    static void cunion(int x, int y) {
        int nx = find(x);
        int ny = find(y);

        if (isCenter[nx] || isCenter[ny]) {
            isCenter[x] = true;
            isCenter[y] = true;

            isCenter[nx] = true;
            isCenter[ny] = true;
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}

class Edge {
    int x;
    int y;
    int v;

    Edge(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}