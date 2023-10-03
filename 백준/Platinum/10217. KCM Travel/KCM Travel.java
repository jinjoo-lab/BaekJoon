import java.io.*;
import java.util.*;

public class Main {
    static int t = 0;
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static ArrayList<Node>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y, c, d));
        }

        for(int i=1;i<=n;i++){
            Collections.sort(graph[i],(x,y) -> x.d - y.d);
        }

        search();

        br.close();
    }

    static void search() {
        int[][] dp = new int[n + 1][m + 1]; // i번째 지점에 j번째 돈으로 왔을 때 거리

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[1][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x, y) -> x.d - y.d
        );

        pq.add(new Node(1, 0, 0));
        int max = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dp[cur.v][cur.c] < cur.d)
                continue;


            for (Node next : graph[cur.v]) {

                if (cur.c + next.c > m) {
                    continue;
                }

                if (dp[next.v][cur.c + next.c] > dp[cur.v][cur.c] + next.d) {
                    dp[next.v][cur.c + next.c] = dp[cur.v][cur.c] + next.d;
                    pq.add(new Node(next.v, cur.c + next.c, dp[next.v][cur.c + next.c]));

                    for (int i = cur.c + next.c + 1; i <= m; i++) {
                        if (dp[next.v][i] > dp[cur.v][cur.c] + next.d)
                            dp[next.v][i] = dp[cur.v][cur.c] + next.d;

                        else{
                            break;
                        }
                    }
                }
            }
        }

        for(int i=0;i<=m;i++){
            max = Math.min(max,dp[n][i]);
        }

        if (max == Integer.MAX_VALUE)
            System.out.println("Poor KCM\n");

        else
            System.out.println(max);

    }
}

class Node {
    int v;
    int c;
    int d;

    Node(int v, int c, int d) {
        this.v = v;
        this.c = c;
        this.d = d;
    }
}
