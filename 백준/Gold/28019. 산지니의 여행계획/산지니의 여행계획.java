import java.util.*;
import java.io.*;

public class Main {

    static int n,m,s;

    static int[] root;

    static boolean[] v;

    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        graph = new ArrayList[n+1];
        v = new boolean[n+1];

        for(int i = 1 ; i <= n ; i++){
            root[i] = i;
            graph[i] = new ArrayList<>();
        }

        long result = 0l;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) * -1;

            pq.add(new int[]{v1,v2,c});
        }

        st = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(st.nextToken());

        int count = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                count += 1;

                result += 2 * (cur[2] * -1);

                graph[cur[0]].add(new int[]{cur[1],cur[2] * -1});
                graph[cur[1]].add(new int[]{cur[0],cur[2] * -1});
            }

            if(count == n - 1)
                break;
        }

        long tmpResult = bfs();

        System.out.println(result - tmpResult);

        br.close();
    }

    static long bfs(){
        long result = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(s,0));
        v[s] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            boolean find = true;

            for(int[] next : graph[cur.v]){
                if(!v[next[0]]){
                    v[next[0]] = true;
                    find = false;
                    q.add(new Node(next[0],cur.c + next[1]));
                }
            }

            if(find){
                result = Math.max(result,cur.c);
            }
        }

        return result;
    }

    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y){
            root[y] = x;
        }else{
            root[x] = y;
        }
    }

    static class Node{
        int v;
        long c;

        Node(int v,long c){
            this.v = v;
            this.c = c;
        }
    }
}
