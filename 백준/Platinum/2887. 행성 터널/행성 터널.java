import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Node[] node = new Node[n+1];
        root = new int[n+1];

        Point[] arrX = new Point[n];
        Point[] arrY = new Point[n];
        Point[] arrZ = new Point[n];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            arrX[i-1] = new Point(i,x);
            arrY[i-1] = new Point(i,y);
            arrZ[i-1] = new Point(i,z);

            node[i] = new Node(x,y,z);
            root[i] = i;
        }

        Arrays.sort(arrX,(x,y) -> x.v - y.v);
        Arrays.sort(arrY,(x,y) -> x.v - y.v);
        Arrays.sort(arrZ,(x,y) -> x.v - y.v);

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.z - y.z
        );

        for(int i=0;i<n-1;i++){
            pq.add(new Node(arrX[i].idx,arrX[i+1].idx,dis(arrX[i].v , arrX[i+1].v)));
            pq.add(new Node(arrY[i].idx,arrY[i+1].idx,dis(arrY[i].v , arrY[i+1].v)));
            pq.add(new Node(arrZ[i].idx,arrZ[i+1].idx,dis(arrZ[i].v , arrZ[i+1].v)));
        }

        get(pq);
        br.close();
    }

    static void get(PriorityQueue<Node> pq){

        long result = 0;
        int num = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.x) != find(cur.y)){
                union(cur.x,cur.y);
                result += cur.z;
                num += 1;
            }

            if(num == n-1)
            {
                break;
            }
        }

        System.out.println(result);
    }

    static int dis(int a,int b){
       return Math.abs(a - b);
    }

    static int find(int x){
        if(root[x] == x){
            return root[x];
        }

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
        {
            root[y] = x;
        }

        else{
            root[x] = y;
        }
    }

}
class Point{
    int idx;
    int v;

    Point(int idx,int v){
        this.idx = idx;
        this.v = v;
    }
}
class Node{
    int x;
    int y;
    int z;

    Node(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
