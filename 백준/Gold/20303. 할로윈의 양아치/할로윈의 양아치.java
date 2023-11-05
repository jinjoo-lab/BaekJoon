import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] candy = new int[n+1];
        for(int i=1;i<=n;i++){
            candy[i] = Integer.parseInt(st.nextToken());
        }

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            union(s,e);
        }

        for(int i=1;i<=n;i++){
            union(i,root[i]);
        }

        int[][] sumCandy = new int[n+1][2];
        for(int i=1;i<=n;i++){
            sumCandy[root[i]][0] += candy[i];
            sumCandy[root[i]][1] += 1;
        }

        ArrayList<Node> list = new ArrayList<>();

        for(int i=1;i<=n;i++){
            if(sumCandy[i][1] != 0 && sumCandy[i][1] < k){
                list.add(new Node(sumCandy[i][0],sumCandy[i][1]));
            }
        }

        int[][] dp = new int[list.size() + 1][k+1];

        int result = 0;
        for(int i = 1;i<=list.size();i++){

            Node cur = list.get(i-1);

            for(int j=0;j<k;j++){
                dp[i][j] = dp[i-1][j];

                if(j - cur.c >= 0){
                    dp[i][j] = Math.max(dp[i][j] , dp[i-1][j - cur.c] + cur.v);
                }

                result = Math.max(result,dp[i][j]);
            }

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

        else{
            root[x] = y;
        }

    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
