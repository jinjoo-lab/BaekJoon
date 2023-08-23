import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        time = new int[n+1];
        int[] count = new int[n+1];

        ArrayList<Integer>[] graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<=n;i++){

            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;

            int num = Integer.parseInt(st.nextToken());
            count[i] = num;

            for(int j=1;j<=num;j++){
                graph[Integer.parseInt(st.nextToken())].add(i);
            }
        }


        Queue<Integer> q= new LinkedList<>();
        int[] result = new int[n+1];

        int max = 0;
        for(int i=1;i<=n;i++) {
            if(count[i] == 0) {
                q.add(i);
                result[i] = time[i];
                max = Math.max(max,result[i]);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                count[next] -= 1;
                result[next] = Math.max(result[next],result[cur]);

                if(count[next] == 0)
                {
                    q.add(next);
                    result[next] = result[next] + time[next];
                    max = Math.max(max,result[next]);
                }
            }


        }

        System.out.println(max);

        br.close();
    }
}