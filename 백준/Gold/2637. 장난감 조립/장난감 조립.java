import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static int[] inputArr;

    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());

        inputArr = new int[n+1];
        graph = new ArrayList[n+1];
        count = new int[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,c));
            inputArr[y] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> x - y
        );

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n,1));
        count[n] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(graph[cur.v].size() == 0){
                pq.add(cur.v);
            }

            for(Node next : graph[cur.v]){
                inputArr[next.v] -= 1;

                count[next.v] += (next.c * cur.c);

                if(inputArr[next.v] == 0){
                    q.add(new Node(next.v,count[next.v]));
                }
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur+" "+count[cur]+"\n");
        }
        System.out.print(sb);


        bf.close();
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
