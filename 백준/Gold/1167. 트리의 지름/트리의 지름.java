import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static int result = 0;
    static int end = 0;

    static boolean[] v;

    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());

            while(true){
                int v = Integer.parseInt(st.nextToken());

                if(v == -1)
                    break;

                int c =  Integer.parseInt(st.nextToken());

                graph[idx].add(new Node(v,c));
            }
        }

        v = new boolean[n+1];
        dfs(1,0);

        v = new boolean[n+1];
        dfs(end,0);

        System.out.println(result);
        bf.close();
    }

    static void dfs(int cur,int dis){

        v[cur] = true;

        if(result < dis){
            result = dis;
            end = cur;
        }

        for(Node next : graph[cur]){

            if(!v[next.v]){
                dfs(next.v,dis + next.c);
            }
        }

    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
