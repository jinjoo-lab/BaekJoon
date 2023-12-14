import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] d;

    static int[] s;

    static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        d = new int[n+1][n+1];
        s = new int[n+1];
        root = new int[n+1];

        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int total = 0;

        for(int i=1;i<=n;i++){
            int cur = Integer.parseInt(bf.readLine());
            s[i] = cur;

            pq.add(new Node(0,i,s[i]));
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=n;j++){
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=1;i<=n;i++){
            for(int j=i+1;j<=n;j++){
                pq.add(new Node(i,j,d[i][j]));
            }
        }



        int count = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                count += 1;
                total += cur.c;
            }

            if(count == n)
                break;
        }

        System.out.println(total);

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

        if(x < y){
            root[y] = x;
        }else{
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

