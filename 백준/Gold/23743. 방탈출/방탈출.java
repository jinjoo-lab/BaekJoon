
import java.io.*;
import java.util.*;

public class Main {

    static int[] root;
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];

        for(int i = 1 ; i <= n ; i++){
            root[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new int[]{v1,v2,c});
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            int cur = Integer.parseInt(st.nextToken());
            pq.add(new int[]{0,i,cur});
        }

        int result = 0;
        int num = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                result += cur[2];
                num+= 1;
            }

            if(num == n)
                break;
        }

        System.out.println(result);
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
