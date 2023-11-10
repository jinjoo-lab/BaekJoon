import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int t = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int k = 1; k <= t; k ++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            root = new int[n+1];
            Node[] arr = new Node[n+1];
            for(int i=1;i<=n;i++){root[i] = i;}

            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                arr[i] = new Node(x,y,z);
            }

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(i == j)
                        continue;

                    if(find(i) != find(j) && can(arr[i],arr[j])){
                        union(i,j);
                    }
                }
            }

            for(int i=1;i<=n;i++){union(i,root[i]);}

            HashSet<Integer> set = new HashSet<>();
            for(int i=1;i<=n;i++){
               set.add(root[i]);
            }

            sb.append(set.size()+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean can(Node a, Node b){
        double disX = Math.pow((a.x - b.x), 2);
        double disY = Math.pow((a.y - b.y), 2);

        double dis = Math.sqrt(disX+ disY);

        if(dis <= (double)(a.r + b.r))
            return true;

        return false;
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
            root[x] = y;

        else{
            root[y] = x;
        }

    }
}
class Node{
    int x;
    int y;
    int r;

    Node(int x,int y,int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
