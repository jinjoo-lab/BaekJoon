
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i<= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        bfs();

        br.close();
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[n+1];
        q.add(1);
        v[1] = 1;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                if(v[next] == 0){
                    v[next] = v[cur] + 1;
                    q.add(next);
                }
            }
        }

        int reIdx = 1;
        int reDis = -1;
        int reCount = 0;

        for(int i = 1 ; i<= n ; i++){

            if(reDis < v[i] - 1){
                reDis = v[i] - 1;
                reIdx = i;
                reCount = 1;
            }else if(reDis == v[i] - 1){
                reCount += 1;
            }
        }


        System.out.println(reIdx+" "+reDis+" "+reCount);
    }
}