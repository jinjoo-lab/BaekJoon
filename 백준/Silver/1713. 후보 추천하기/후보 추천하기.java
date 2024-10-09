import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        board = new int[m+1];
        for(int i=1;i<=m;i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.c == y.c)
                        return x.time - y.time;

                    return x.c - y.c;
                }
        );

        HashSet<Integer> set = new HashSet<>();

        for(int i = 1 ; i <= m ; i++) {
            int cur = board[i];

            if(set.contains(cur)) {
                PriorityQueue<Node> tmp = new PriorityQueue<>(
                        (x,y) -> {
                            if(x.c == y.c)
                                return x.time - y.time;

                            return x.c - y.c;
                        }
                );

                while(!pq.isEmpty()) {
                    Node node = pq.poll();

                    if(node.num == cur) {
                        node.c += 1;
                    }

                    tmp.add(node);
                }

                pq = tmp;
            }else {
                if(pq.size() < n ) {
                    set.add(cur);
                    pq.add(new Node(cur,1,i));
                }else {
                    Node tmp = pq.poll();
                    set.remove(tmp.num);
                    pq.add(new Node(cur,1,i));
                    set.add(cur);
                }
            }
        }

        int[] arr = new int[pq.size()];
        int idx =0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            arr[idx] = node.num;
            idx++;
        }

        Arrays.sort(arr);
        for(int i = 0 ; i < arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        br.close();
    }

    static class Node {
        int num;
        int c;
        int time;

        Node(int num, int c,int time) {
            this.num = num;
            this.c = c;
            this.time = time;
        }
    }
}
