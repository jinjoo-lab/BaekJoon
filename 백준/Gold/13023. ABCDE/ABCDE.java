
import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static ArrayList<Integer>[] graph;

    static boolean[] v;

    static boolean possible = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        v = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        for(int i = 0 ; i < n ; i++){
            
            v[i] = true;
            go(i,1);
            v[i] = false;

            if(possible)
                break;
        }

        int num = possible ? 1 : 0;

        System.out.println(num);

        br.close();
    }

    static void go(int idx,int num){

        if(possible)
            return;
        
        if(num == 5){
            possible = true;
            return;
        }
        
        for(int next : graph[idx]){
            if(!v[next]){
                v[next] = true;
                go(next,num + 1);
                v[next] = false;
            }
        }
    }
}
