import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    static int[] input;
    static int[] count;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        input = new int[n+1];
        count = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine()," ");

            while(true) {
                int next = Integer.parseInt(st.nextToken());

                if(next == 0)
                    break;

                graph[i].add(next);

                input[i] += 1;
                count[i] += 1;
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());


        result = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            result[i] = -1;
        }
        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= m ; i++) {
            int next = Integer.parseInt(st.nextToken());
            input[next] = 0;
            result[next] = 0;
            q.add(next);
        }

        int[] sec = new int[n+1];

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {

                if(input[next] == 0)
                    continue;

                input[next] -= 1;

                sec[next] = Math.max(sec[next], result[cur] + 1);

                if((count[next] - input[next]) * 2 >= count[next]) {
                    input[next] = 0;
                    result[next] = sec[next];
                    q.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= n ; i++) {
            sb.append(result[i]+" ");
        }
        System.out.println(sb);

        br.close();
    }
}
