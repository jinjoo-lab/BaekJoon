
import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int t = 0;

    static HashMap<Node,Integer> map = new HashMap<>();
    static HashSet<Node> ground = new HashSet<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ground.add(new Node(x,y));
        }

        int result = -1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        map.put(new Node(0,0),0);

        while(!q.isEmpty()){
            Node cur = q.poll();
            int v = map.get(cur);

            if(cur.y == t){
                result = v;
                break;
            }

            for(int i = -2 ; i <= 2 ; i++){
                for(int j = -2 ; j <= 2 ; j++){
                    int nx = cur.x + i;
                    int ny = cur.y + j;

                    if(nx < 0 || nx >= 1_000_001 || ny < 0 || ny >= 1_000_001){
                        continue;
                    }

                    Node next = new Node(nx,ny);

                    if(!ground.contains(next))
                        continue;

                    if(!map.containsKey(next)){
                        map.put(next,v + 1);
                        q.add(next);
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static class Node{
        int x;
        int y;

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public boolean equals(Object obj) {

            Node tmp = (Node) obj;

            return (this.x == tmp.x && this.y == tmp.y);
        }
    }
}
