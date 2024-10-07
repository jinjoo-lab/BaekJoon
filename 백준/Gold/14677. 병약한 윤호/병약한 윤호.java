import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static String start;
    static char[] arr;
    static boolean[][][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        start = br.readLine();


        v = new boolean[3*n + 1][3*n + 1][3];

        arr = start.toCharArray();

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,arr.length - 1,0,0));
        v[0][arr.length - 1][0] = true;

        int result = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            result = Math.max(result,cur.count);

            if(cur.l > cur.r)
                break;


            if(cur.time == 0) {
                if(arr[cur.l] == 'B' && !v[cur.l + 1][cur.r][1]) {
                    v[cur.l + 1][cur.r][1] = true;
                    q.add(new Node(cur.l + 1, cur.r,1,cur.count + 1));
                }

                if(arr[cur.r] == 'B' && !v[cur.l][cur.r - 1][1]) {
                    v[cur.l][cur.r - 1][1] = true;
                    q.add(new Node(cur.l,cur.r - 1,1,cur.count + 1));
                }

            }else if(cur.time == 1 ) {
                if(arr[cur.l] == 'L' && !v[cur.l + 1][cur.r][2]) {
                    v[cur.l + 1][cur.r][2] = true;
                    q.add(new Node(cur.l + 1,cur.r,2,cur.count + 1));
                }

                if(arr[cur.r] == 'L' && !v[cur.l][cur.r - 1][2]) {
                    v[cur.l][cur.r - 1][2] = true;
                    q.add(new Node(cur.l,cur.r - 1,2,cur.count + 1));
                }
            }else {
                if(arr[cur.l] == 'D' && !v[cur.l + 1][cur.r][0]) {
                    v[cur.l + 1][cur.r][0] = true;
                    q.add(new Node(cur.l + 1,cur.r,0,cur.count + 1));
                }

                if(arr[cur.r] == 'D' && !v[cur.l][cur.r - 1][0]) {
                    v[cur.l][cur.r - 1][0] = true;
                    q.add(new Node(cur.l,cur.r - 1,0, cur.count + 1));
                }
            }
        }


        System.out.println(result);
        br.close();
    }

    static class Node {
        int l;
        int r;
        int time;
        int count;

        Node(int l,int r, int time, int count) {
            this.l = l;
            this.r = r;
            this.time = time;
            this.count = count;
        }
    }
}
