import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int P;
    private static int K;
    private static ArrayList<Node>[] map;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        int max = 0;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map[start].add(new Node(end, value));
            map[end].add(new Node(start, value));

            max = Math.max(max, value);
        }

        int start = 0;
        int answer = Integer.MIN_VALUE;

        while (start <= max) {
            int mid = (start + max) / 2;

            if (dijstra(mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (answer == Integer.MIN_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }

    private static boolean dijstra(int x) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[1] = 0;
        priorityQueue.offer(new Node(1, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            int now = node.index;
            int cost = node.value;

            if (dist[now] < cost) {
                continue;
            }

            for (Node n : map[now]) {
                int next = n.index;
                int nextCost = cost;

                if (n.value > x) {
                    nextCost += 1;
                }

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;
                    priorityQueue.offer(new Node(next, nextCost));
                }

            }
        }


        return dist[N] <= K;
    }

    static class Node implements Comparable<Node> {
        private int index;
        private int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}