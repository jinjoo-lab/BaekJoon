import java.util.*;
import java.io.*;

public class Main {

    static int n, s, p;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= n - 1 ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);

        }

        int result = n - 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(p);
        boolean[] v = new boolean[n+1];
        v[p] = true;
        int count = 0;
        int d = 0;

        while(!q.isEmpty() && count < 2) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                if(cur <= s && count < 2) {
                    result -= d;
                    count++;
                }

                else {
                    for (int next : graph[cur]) {
                        if (!v[next]) {
                            v[next] = true;
                            q.add(next);
                        }
                    }
                }
            }

            d++;
        }

        System.out.println(result);


        br.close();
    }
}
