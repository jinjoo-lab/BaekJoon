import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;

    static ArrayList<Node>[] graph;

    static int[][] dis;


    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }

        dis = new int[n+1][2];

        for(int i=0;i<=1;i++){
            if(i == 0){
                find(1,0);
            }else{
                find(k,1);
            }
        }

        if(dis[n][0] == Integer.MAX_VALUE || dis[k][0] ==Integer.MAX_VALUE || dis[n][1]== Integer.MAX_VALUE){
            System.out.println("GOOD BYE");
        }else if(dis[n][0] != dis[k][0] + dis[n][1]){
            System.out.println("GOOD BYE");
        }
        else{
            System.out.println("SAVE HIM");
        }

        br.close();
    }

    static void find(int num,int cur){
        for(int i=1;i<=n;i++){
            dis[i][cur] = Integer.MAX_VALUE;
        }
        dis[num][cur] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.v - y.v
        );

        pq.add(new Node(num,0));

        while(!pq.isEmpty()){
            Node tmp = pq.poll();

            for(Node next : graph[tmp.v]){
                if(dis[next.v][cur] > dis[tmp.v][cur] + next.c){
                    dis[next.v][cur] = dis[tmp.v][cur] + next.c;
                    pq.add(new Node(next.v,dis[next.v][cur]));
                }
            }
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
