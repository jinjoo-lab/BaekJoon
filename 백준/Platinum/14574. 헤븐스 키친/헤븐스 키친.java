import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] root;
    static int[][] cook;
    static ArrayList<Integer>[] graph;
    static boolean[] v;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        root = new int[n+1];
        cook = new int[n+1][2];
        v = new boolean[n+1];

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;

            st = new StringTokenizer(br.readLine()," ");

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cook[i][0] = p;
            cook[i][1] = c;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> y[2] - x[2]
        );

        for(int i = 1 ; i < n ; i++) {
            for(int j = i + 1 ; j <= n ; j++) {
                pq.add(new int[]{i,j,getValue(i,j)});
            }
        }

        long result = 0;
        int count = 0;

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();

            if(find(tmp[0]) != find(tmp[1])) {
                union(tmp[0],tmp[1]);
                result += tmp[2];
                graph[tmp[0]].add(tmp[1]);
                graph[tmp[1]].add(tmp[0]);
                count++;
            }

            if(count == n - 1)
                break;
        }

        go(1);
        System.out.println(result);
        System.out.println(sb);

        br.close();
    }

    static void go(int c) {
        v[c] = true;
        for(int next : graph[c]) {
            if(!v[next]) {
                go(next);
                sb.append(c).append(" ").append(next).append("\n");
            }
        }
    }

    static int getValue(int idx1, int idx2) {
        return (cook[idx1][1] + cook[idx2][1]) / Math.abs(cook[idx1][0] - cook[idx2][0]);
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
