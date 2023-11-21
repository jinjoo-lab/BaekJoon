import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k =  Integer.parseInt(st.nextToken());


        PriorityQueue<Node> l = new PriorityQueue<>(
                (x,y) -> y.dis - x.dis
        );
        PriorityQueue<Node> r = new PriorityQueue<>(
                (x,y) -> y.dis - x.dis
        );

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x < k){
                l.add(new Node(k-x,y));
            }else{
                r.add(new Node(x-k,y));
            }
        }

        int result = 0;

        while(!l.isEmpty()){

            int tmp = m;
            int max = 0;

            while(tmp!=0 && !l.isEmpty()){
                Node cur = l.peek();

                if(cur.num <= tmp){
                    tmp -= cur.num;
                    max = Math.max(max,cur.dis);
                    l.poll();
                }else{
                    cur.num -= tmp;
                    max = Math.max(max,cur.dis);
                    tmp = 0;
                    break;
                }
            }

            result += max * 2;
        }

        while(!r.isEmpty()){

            int tmp = m;
            int max = 0;

            while(tmp>0 && !r.isEmpty()){
                Node cur = r.peek();

                if(cur.num <= tmp){
                    tmp -= cur.num;
                    max = Math.max(max,cur.dis);
                    r.poll();
                }else{
                    cur.num -= tmp;
                    max = Math.max(max,cur.dis);
                    tmp = 0;
                    break;
                }
            }

            result += max * 2;
        }

        System.out.println(result);
        br.close();
    }

}
class Node{
    int dis;
    int num;

    Node(int dis,int num){
        this.dis = dis;
        this.num = num;
    }
}
