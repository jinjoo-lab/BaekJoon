import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> dia = new PriorityQueue<>(
                (x, y) -> {
                    if (x.m == y.m)
                        return y.v - x.v;

                    return x.m - y.m;
                }
        );

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dia.add(new Node(m, v));
        }


        int[] bag = new int[k];
        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);


        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.reverseOrder()
        );

        long result = 0;

        for (int i = 0; i < k; i++) {

            while (!dia.isEmpty() && dia.peek().m <= bag[i]) {
                Node cur = dia.poll();
                pq.add(cur.v);
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }

        }


        System.out.println(result);
        br.close();
    }
}
class Node{
    int m;
    int v;

    Node(int m,int v){
        this.m = m;
        this.v = v;
    }
}
