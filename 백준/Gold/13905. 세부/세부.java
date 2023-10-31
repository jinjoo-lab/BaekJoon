import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int s = 0;
    static int e = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> y.c - x.c
        );

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Node(v1,v2,c));
        }

        int min = 1000001;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.v1) != find(cur.v2))
            {
                union(cur.v1,cur.v2);
                min = Math.min(min,cur.c);
            }

            if(find(s) == find(e)){
                System.out.println(min);
                return;
            }
        }

        System.out.println(0);
        br.close();
    }

    static int find(int x){
        if(root[x] == x)
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
class Node{
    int v1;
    int v2;
    int c;

    Node(int v1,int v2,int c){
        this.v1 = v1;
        this.v2 = v2;
        this.c = c;
    }
}
