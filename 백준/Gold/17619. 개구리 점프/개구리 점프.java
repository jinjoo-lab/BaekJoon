import java.util.*;
import java.io.*;

public class Main {
    static int n,m;

    static Node[] arr;

    static int[] root;

    static int[][] query;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new Node[n];
        root = new int[n+1];
        query = new int[m][2];
        for(int i = 1 ; i <=  n ; i++){
            root[i] = i;
        }

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int idx = i + 1;

            arr[i] = new Node(x1,x2,y,idx);
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            query[i][0] = x1;
            query[i][1] = x2;
        }


        Arrays.sort(arr,(x,y) -> x.x1 - y.x1);

        int maxNum = arr[0].x2;
        int idx = arr[0].idx;

        for(int i = 0 ; i < n ; i ++){
            Node cur = arr[i];

            if(cur.x1 <= maxNum){
                union(idx,cur.idx);
                maxNum = Math.max(maxNum,cur.x2);
            }else{
                idx = cur.idx;
                maxNum = Math.max(maxNum,cur.x2);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < m ; i++){
            int tmpResult = find(query[i][0]) == find(query[i][1]) ? 1 : 0;

            sb.append(tmpResult+"\n");
        }

        System.out.print(sb.toString());

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

        if(x < y){
            root[y] = x;
        }else{
            root[x] = y;
        }
    }

    static class Node{
        int x1;
        int x2;
        int y;
        int idx;

        Node(int x1,int x2,int y,int idx){
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.idx = idx;
        }
    }
}
