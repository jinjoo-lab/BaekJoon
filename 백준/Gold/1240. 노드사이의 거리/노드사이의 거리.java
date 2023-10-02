import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n-1;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int dis = search(s,e);
            sb.append(dis+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int search(int s,int e){
        Queue<Node> q = new LinkedList<>();
        boolean[] v= new boolean[n+1];
        v[s] = true;
        q.add(new Node(s,0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.v == e){
                return cur.c;
            }

            for(Node next : graph[cur.v]){
                if(!v[next.v]){
                    v[next.v] = true;
                    q.add(new Node(next.v,next.c + cur.c));
                }
            }
        }

        return 0;
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
