import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static ArrayList<Integer>[] graph;
    static long d = 0;
    static long g = 0;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        v = new boolean[n+1];
        for(int i =1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }


        for(int i = 1 ; i <= n - 1 ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }


        for(int i = 1 ; i <= n ; i++) {
            if(graph[i].size() >= 3) {
                long tmp = graph[i].size();

                g += (tmp * (tmp -1) * (tmp - 2)) / 6;
            }
        }

        v[1] = true;
        go(1);

        if(d > g* 3) {
            System.out.println("D");
        }else if(d < g*3) {
            System.out.println("G");
        }else{
            System.out.println("DUDUDUNGA");
        }

        br.close();
    }

    static void go(int idx) {

        for(int next : graph[idx]) {
            if(!v[next]) {
                v[next] = true;

                if(graph[idx].size() >= 2 && graph[next].size() >= 2) {
                    d += ((graph[idx].size() - 1) * (graph[next].size() - 1));
                }

                go(next);
            }
        }
    }
}
