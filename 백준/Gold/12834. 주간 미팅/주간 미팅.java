import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int v = 0;

    static int e = 0;

    static int a = 0;
    static int b = 0;

    static ArrayList<Node>[] graph;

    static int[] dis;

    static int[] isIt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];

        for(int i=1;i<=v;i++){
            graph[i] = new ArrayList<>();
        }

        dis = new int[v+1];
        isIt = new int[n+1];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=1;i<=n;i++){
            isIt[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=e;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2,c));
            graph[v2].add(new Node(v1,c));
        }

        find(a);
        find(b);

        int result = 0;

        for(int i=1;i<=n;i++){
            result += dis[isIt[i]];
        }
        System.out.println(result);

        bf.close();
    }

    static void find(int s){

        int[] tmpDis = new int[v+1];

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        pq.add(new Node(s,0));

        for(int i=1;i<=v;i++){
            tmpDis[i] = Integer.MAX_VALUE;
        }

        tmpDis[s] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.c > tmpDis[cur.v])
                continue;
            
            for(Node next : graph[cur.v]){
                if(tmpDis[next.v] > tmpDis[cur.v] + next.c){
                    tmpDis[next.v] = tmpDis[cur.v] + next.c;
                    pq.add(new Node(next.v,tmpDis[next.v]));
                }
            }
        }

        for(int i=1;i<=v;i++){

            if(tmpDis[i] == Integer.MAX_VALUE)
                dis[i] += -1;
            else
                dis[i] += tmpDis[i];
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
