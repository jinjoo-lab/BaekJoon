import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int t = 0;
    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());
        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");
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
                st = new StringTokenizer(br.readLine(), " ");
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int v3 = Integer.parseInt(st.nextToken());

                pq.add(new int[]{v1,v2,v3});
            }

            int num = 0;
            long total = 0;

            while(!pq.isEmpty()){
                int[] cur = pq.poll();

                if(find(cur[0]) != find(cur[1])){
                    union(cur[0],cur[1]);
                    num += 1;
                    total += cur[2];
                }

                if(num == -1)
                    break;
            }

            sb.append("#"+a+" "+total+"\n");
        }
        System.out.print(sb);
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

