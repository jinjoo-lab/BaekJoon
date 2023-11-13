import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.cost - y.cost
        );

        int total = 0;
        for(int i=1;i<=n;i++){
            char[] arr = br.readLine().toCharArray();

            for(int j=1;j<=n;j++){
                int cost = 0;
                char cur = arr[j-1];

                if(cur >= 'a' && cur <= 'z'){
                    cost = cur - 'a' + 1;
                }
                else if(cur >= 'A' && cur <= 'Z'){
                    cost = cur - 'A' + 27;
                }

                total += cost;

                if(i != j && cost > 0){
                    pq.add(new Node(i,j,cost));
                }
            }
        }

        int num = 0;
        int use = 0;

        while(!pq.isEmpty()){
            Node cur =pq.poll();

            if(find(cur.v1) != find(cur.v2)){
                union(cur.v1,cur.v2);
                num += 1;
                use += cur.cost;
            }

            if(num == n-1){
                break;
            }
        }

        if(num == n-1){
            System.out.println(total - use);
        }else{
            System.out.println(-1);
        }

        br.close();
    }

    static int find(int x){
        if(x == root[x]){
            return root[x];
        }

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
    int cost;

    Node(int v1,int v2,int cost){
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
}
