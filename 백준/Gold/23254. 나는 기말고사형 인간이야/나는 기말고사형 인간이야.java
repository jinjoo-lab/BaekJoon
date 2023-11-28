import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[] score;

    static int[] plus;

    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        score = new int[m];
        plus = new int[m];
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> y.plus - x.plus
        );

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<m;i++){
            score[i] = Integer.parseInt(st.nextToken());
            total += score[i];
        }

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<m;i++){
            plus[i] = Integer.parseInt(st.nextToken());

            pq.add(new Node(100 - score[i],plus[i]));
        }

        int time = 24 * n;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(time >= (cur.rest / cur.plus)){
                time -= cur.rest / cur.plus;

                total += cur.plus * (cur.rest / cur.plus);

                if(cur.rest % cur.plus >= 1){
                    pq.add(new Node(cur.rest % cur.plus,cur.rest % cur.plus));
                }
            }else if(time > 0 && (cur.rest >= time * cur.plus)){
                total += time * cur.plus;
                time = 0;
            }
        }

        System.out.println(total);
        bf.close();
    }

}
class Node{
    int rest;
    int plus;

    Node(int rest,int plus){
        this.rest = rest;
        this.plus = plus;
    }
}
