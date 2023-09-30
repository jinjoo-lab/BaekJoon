import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=0;i<=n;i++){
            root[i] = i;
        }

        PriorityQueue<Node> pq1 = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        PriorityQueue<Node> pq2 = new PriorityQueue<>(
                (x,y) -> y.c - x.c
        );

        for(int i=1;i<=m+1;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(v == 0)
                v = 1;

            else{
                v = 0;
            }

            pq1.add(new Node(x,y,v));
            pq2.add(new Node(x,y,v));
        }

        int min = find(pq1);
        root = new int[n+1];
        for(int i=1;i<=n;i++){root[i] = i;}
        int max = find(pq2);

        min = min * min;
        max = max * max;

        System.out.println(max - min);

        br.close();
    }

    static int find(PriorityQueue<Node> pq){
        int result = 0;
        int num = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                result += cur.c;
                num += 1;
            }

            if(num == n){
                break;
            }
        }

        return result;
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

        else{
            root[x] = y;
        }
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
