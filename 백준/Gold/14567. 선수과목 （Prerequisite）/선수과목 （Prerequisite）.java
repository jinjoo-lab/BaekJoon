import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] count;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        count = new int[n+1];
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

        int[] result = new int[n+1];
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(count[i] == 0){
                result[i] = 1;
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                count[next] -= 1;

                if(count[next] == 0){
                    q.add(next);
                    result[next] = result[cur] + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(result[i]+" ");
        }sb.append("\n");

        System.out.println(sb);
        br.close();
    }

}