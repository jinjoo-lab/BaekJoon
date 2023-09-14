import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int[] time;
    static int[] connect;

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new int[n+1];
        connect = new int[n+1];
        graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;

            while(true){
                int next = Integer.parseInt(st.nextToken());

                if(next == -1)
                    break;

                connect[i] = connect[i] + 1;
                graph[next].add(i);
            }
        }
        int[] result  = new int[n+1];
        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=n;i++){
            if(connect[i] == 0) {
                q.add(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                connect[next] = connect[next] - 1;

                result[next] = Math.max(result[next] , result[cur]);

                if(connect[next] == 0){
                    q.add(next);
                    result[next] = result[next] + time[next];
                }

            }
        }

        for(int i=1;i<=n;i++){
            System.out.println(result[i]);
        }
        br.close();
    }
}