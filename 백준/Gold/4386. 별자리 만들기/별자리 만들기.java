import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int[] root;
    static double[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        board = new double[n+1][2];
        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            root[i] = i;
        }

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Double.compare(x.dis,y.dis)
        );

        for(int i = 1 ; i < n ; i++){
            for(int j = i + 1 ; j <= n ; j++){
                pq.add(new Node(i,j,calDis(i,j)));
            }
        }

        int count = 0;
        double result = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                count++;
                result += cur.dis;
            }

            if(count == n - 1)
                break;
        }

        System.out.println(result);
        br.close();
    }

    static double calDis(int idx1,int idx2){
        return Math.sqrt(Math.pow(Math.abs(board[idx1][0] - board[idx2][0]),2) + Math.pow(Math.abs(board[idx1][1] - board[idx2][1]),2));
    }

    static class Node{
        int v1;
        int v2;
        double dis;

        Node(int v1,int v2,double dis){
            this.v1 = v1;
            this.v2 = v2;
            this.dis = dis;
        }
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
