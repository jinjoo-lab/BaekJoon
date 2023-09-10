import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Point[] board = new Point[n+1];
        root = new int[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[i] = new Point(x,y);
            root[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        for(int i=1;i<n;i++){
            for(int j=2;j<=n;j++){
                int tmp = dis(board[i],board[j]);

                if(tmp >= m){
                    pq.add(new Node(i,j,tmp));
                }
            }
        }

        int result = 0;
        int num = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(num == n-1)
                break;

            if(find(cur.x) != find(cur.y)){
                union(cur.x,cur.y);
                result += cur.c;
                num = num + 1;
            }
        }

        if(num != n-1)
            System.out.println(-1);
        else
            System.out.println(result);
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


    static int dis(Point x,Point y){
        int nx = (x.x - y.x);
        int ny = (x.y - y.y);

        return (nx*nx + ny*ny);
    }
}
class Point{
    int x;
    int y;

    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}

class Node{
    int x;
    int y;
    int c;

    Node(int x,int y,int c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
