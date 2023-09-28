import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;
    static int[] count;

    static ArrayList<Integer>[] past;

    static int start = 0;
    static int end = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        count = new int[n + 1];
        graph = new ArrayList[n + 1];
        past = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            past[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y, c));
            count[y] += 1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        search();
        br.close();
    }

    static void search() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        int[] time = new int[n + 1];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Node next : graph[cur.v]) {
                count[next.v] -= 1;

                if (time[next.v] < time[cur.v] + next.c) {
                    time[next.v] = time[cur.v] + next.c;

                    past[next.v].clear();
                    past[next.v].add(cur.v);
                } else if (time[next.v] == time[cur.v] + next.c) {
                    past[next.v].add(cur.v);
                }

                if (count[next.v] == 0) {
                    q.add(new Node(next.v, time[next.v]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(time[end] + "\n");
        sb.append(count() + "\n");

        System.out.print(sb);
    }

    static int count() {
        int result = 0;
        boolean[][] v = new boolean[n + 1][n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(end);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == 0)
                continue;

            for (int next : past[cur]) {
                if(!v[cur][next]) {
                    v[cur][next] = true;
                    v[next][cur] = true;
                    result += 1;
                    q.add(next);
                }
            }
        }

        return result;
    }
}

class Node {
    int v;
    int c;

    Node(int v, int c) {
        this.v = v;
        this.c = c;
    }
}