import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Integer>[] graph;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        count = new int[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());

            int tmp = 0;

            for(int j=1;j<=num;j++){
                int next = Integer.parseInt(st.nextToken());
                if(tmp != 0){
                    graph[tmp].add(next);
                    count[next] += 1;
                }

                tmp = next;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        int total = 0;
        for(int i=1;i<=n;i++){
            if(count[i] == 0)
            {
                q.add(i);
                total = total + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur+"\n");

            for(int next : graph[cur]){
                if(count[next] == 0){
                    break;
                }

                count[next] -= 1;
                if(count[next] == 0){
                    q.add(next);
                    total = total + 1;
                }
            }
        }

        if(total == n) {
            System.out.print(sb);
        }
        else{
            System.out.println(0);
        }
        br.close();
    }
}
