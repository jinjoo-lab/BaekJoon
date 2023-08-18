import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] count;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        count = new int[n+1];
        visit = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            count[y] += 1;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=n;i++){
            if(count[i] == 0) {
                q.add(i);
                visit[i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){

            int cur = q.poll();
            sb.append(cur+" ");

            for(int next : graph[cur]){
                count[next] -= 1;

                if(count[next] == 0 && !visit[next]){
                    visit[next] = true;
                    q.add(next);
                }
            }

        }
        System.out.println(sb);
        br.close();
    }

    static void print()
    {
        for(int i=1;i<=n;i++){
            System.out.print(count[i]+" ");
        }
        System.out.println();
    }
}
