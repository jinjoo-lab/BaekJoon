import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int m = 0;

    static int t = 0;

    static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );


        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int v3 = Integer.parseInt(st.nextToken());

            pq.add(new Node(v1,v2,v3));
        }

        int tmp = 0;
        int result = 0;
        int num = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);

                result += tmp + cur.c;
                tmp += t;
                num += 1;
            }

            if(num == n-1){
                break;
            }
        }

        System.out.println(result);
        bf.close();
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
