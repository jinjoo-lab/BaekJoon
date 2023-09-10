import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        ArrayList<Node>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            graph[x].add(new Node(y,v));
            graph[y].add(new Node(x,v));
        }


        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int tmp = bfs(y,graph,x);

            sb.append(tmp+"\n");
        }
        System.out.println(sb);

        br.close();
    }

    static void print(int[][] board){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int bfs(int x,ArrayList<Node>[] graph,int k){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,Integer.MAX_VALUE));
        boolean[] visit = new boolean[n+1];
        visit[x] = true;

        int result = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();

            for(Node next : graph[cur.x]){
                if(!visit[next.x] && next.v >= k ){
                    result += 1;
                    visit[next.x] = true;
                    q.add(next);
                }
            }
        }

        return result;
    }


}
class Node{
    int x;
    int v;

    Node(int x,int v){
        this.x = x;
        this.v = v;
    }
}