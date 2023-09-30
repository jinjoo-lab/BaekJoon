import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x == -1 && y == -1)
                break;

            graph[x].add(y);
            graph[y].add(x);
        }

        int min = 51;
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            int tmp = search(i);

            if(min > tmp){
                count = 1;
                list.clear();
                list.add(i);
                min = tmp;
            }

            else if(min == tmp){
                count += 1;
                list.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min+" "+count+"\n");

        for(int tmp : list){
            sb.append(tmp+" ");
        }sb.append("\n");

        System.out.print(sb);
    }

    static int search(int start){
        int[] dis = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        for(int i=1;i<=n;i++)
        {
            dis[i] = 51;

            if(i == start)
                dis[i] = 0;
        }
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(int next : graph[cur.v]){
                if(dis[next] > dis[cur.v] + 1){
                    dis[next] = dis[cur.v] + 1;
                    pq.add(new Node(next,dis[next]));
                }
            }
        }

        int max = 0;
        for(int i=1;i<=n;i++){
            max = Math.max(max,dis[i]);
        }

        return max;
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
