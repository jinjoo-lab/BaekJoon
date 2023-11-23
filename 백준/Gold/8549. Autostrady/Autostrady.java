import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.v - y.v
        );

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Node(x,y,v));
        }

        long result = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(find(cur.x) != find(cur.y)){
                union(cur.x,cur.y);
                result += cur.v;
                count += 1;
            }

            if(count == n-1) {
                System.out.println(cur.v);
                break;
            }
        }


        br.close();
    }

    static int find(int x){
        if(x == root[x])
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
    int x;
    int y;
    int v;

    Node(int x,int y,int v){
        this.x = x;
        this.y = y;
        this.v = v;
    }
}

