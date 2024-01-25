import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;
    static int[] boardCost;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        boardCost = new int[n+1];
        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(bf.readLine());

            boardCost[i] = tmp;
            root[i] = i;

            min = Math.min(min,boardCost[i]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[] tmp = new int[3];
            tmp[0] = v1;
            tmp[1] = v2;
            tmp[2] = 2*c + boardCost[v1] + boardCost[v2];

            pq.add(tmp);
        }

        int total = 0;
        int count = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                total += cur[2];
                count += 1;
                
            }

            if(count == n-1)
                break;
        }

        System.out.println(total + min);
        bf.close();
    }


    static int find(int x){
        if(x == root[x])
            return x;

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



