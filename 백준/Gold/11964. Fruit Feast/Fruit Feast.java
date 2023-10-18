import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int o = 0;
    static int l = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        o = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        if(n % o == 0 || n % l ==0){
            System.out.println(n);
            return;
        }

        boolean[][] v = new boolean[n+1][2];
        Queue<Node> q= new LinkedList<>();
        q.add(new Node(0,0));
        v[0][0] = true;
        int max = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();
            max = Math.max(max,cur.v);

            if(cur.d == 0){
                if(cur.v + o <= n && !v[cur.v+o][0]){
                    v[cur.v+o][0] = true;
                    q.add(new Node(cur.v+o,0));
                }

                if(cur.v + l <= n && !v[cur.v+l][0]){
                    v[cur.v+l][0] = true;
                    q.add(new Node(cur.v+l,0));
                }

                if(!v[cur.v/2][1]){
                    v[cur.v/2][1] = true;
                    q.add(new Node(cur.v/2,1));
                }
            }else{
                if(cur.v + o <= n && !v[cur.v+o][0] && !v[cur.v+o][1]){
                    v[cur.v+o][1] = true;
                    q.add(new Node(cur.v+o,1));
                }

                if(cur.v + l <= n && !v[cur.v+l][0] && !v[cur.v+l][1]){
                    v[cur.v+l][1] = true;
                    q.add(new Node(cur.v+l,1));
                }
            }

        }

        System.out.println(max);
        br.close();
    }
}
class Node{
    int v;
    int d;

    Node(int v,int d){
        this.v = v;
        this.d = d;
    }
}
