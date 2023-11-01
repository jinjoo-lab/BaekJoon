import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    static int[][] graph;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        root = new int[n+1];
        count = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        m = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++)
            {
                if(i == j)
                    graph[i][j] = 0;

                else
                    graph[i][j] = 101;
            }
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s][e] = 1;
            graph[e][s] = 1;

            union(s,e);
        }

        for(int i=1;i<=n;i++){
            union(i,root[i]);
        }


        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j)
                    continue;

                if(graph[i][j] >= 101)
                    continue;

                count[i] = Math.max(count[i],graph[i][j]);
            }
        }


        boolean[] leader = new boolean[n+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> x - y);
        int num = 0;
        for(int i=1;i<=n;i++){
            if(!leader[root[i]]){
                num = num + 1;
                leader[root[i]] = true;

                int group = root[i];
                int idx = i;

                for(int j=1;j<=n;j++){
                    if(root[j] == group){
                        if(count[idx] > count[j]){
                            idx = j;
                        }
                    }
                }

                pq.add(idx);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(num+"\n");

        while(!pq.isEmpty()){
            sb.append(pq.poll()+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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
