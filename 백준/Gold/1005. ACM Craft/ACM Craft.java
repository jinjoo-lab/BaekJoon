import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int t = 0;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int k=1;k<=t;k++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            time = new int[n + 1];
            int[] count = new int[n + 1];

            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }


            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                count[y] += 1;
            }

            int where = Integer.parseInt(br.readLine());

            int[] result = new int[n + 1];
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (count[i] == 0) {
                    q.add(i);
                    result[i] = time[i];
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    count[next] -= 1;
                    result[next] = Math.max(result[next], result[cur]);

                    if (count[next] == 0) {
                        q.add(next);

                        result[next] += time[next];
                    }
                }
            }

           sb.append(result[where]+"\n");

        }
        System.out.print(sb);

        br.close();
    }
}
