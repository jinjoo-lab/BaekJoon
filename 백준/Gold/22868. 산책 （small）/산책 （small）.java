
import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static boolean[] v;
    static int s,e;
    static ArrayList<Integer>[] graph;

    static int result = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        v = new boolean[n+1];

        for(int i =1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1;  i <= m ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        for(int i = 1 ; i <= n ; i++){
            Collections.sort(graph[i]);
        }

        st = new StringTokenizer(br.readLine(), " ");

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        List<Integer> fp = bfs(s,e);

        v = new boolean[n+1];

        for(int next : fp){
            if(next == s || next == e)
                continue;

            v[next] = true;
        }

        List<Integer> sp = bfs(e,s);

        HashSet<Integer> set = new HashSet<>();

        set.addAll(sp);
        set.addAll(fp);

        result = set.size();
        System.out.println(result);

        br.close();
    }

    static List<Integer> bfs(int x,int end){
        v[x] = true;
        Queue<Node> q = new ArrayDeque<>();
        Node start = new Node(x);
        start.path.add(x);
        q.add(start);

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == end){
                return cur.path;
            }

            for(int next : graph[cur.x]){
                if(!v[next]){
                    v[next] = true;
                    Node nextNode = new Node(next);
                    nextNode.path.addAll(cur.path);
                    nextNode.path.add(next);
                    q.add(nextNode);
                }
            }
        }

        return null;
    }

    static class Node{
        int x;
        List<Integer> path = new ArrayList<>(20);

        Node(int x){
            this.x = x;
        }
    }
}
