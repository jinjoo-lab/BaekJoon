import java.util.*;
import java.io.*;

public class Main {

    static int n,t,w,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        Queue<Node> wait = new ArrayDeque<>();

        for(int i = 1 ; i <= n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            wait.add(new Node(num,t,0,0));
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        P[] arr = new P[m];

        for(int i = 1 ; i <= m; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int idx = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int waitT = Integer.parseInt(st.nextToken());

            arr[i-1] = new P(idx, time, waitT);
        }

        Arrays.sort(arr, (x,y) -> x.come -y.come);

        Node cur = null;
        int ni = 0;

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= w ; i++) {

            if(ni < m && arr[ni].come == i) {
                wait.add(new Node(arr[ni].idx,arr[ni].time,0,0));
                ni++;
            }

            if(cur == null) {
                if(!wait.isEmpty()) {
                    cur = wait.poll();
                }
            }

            cur.curT += 1;
            cur.tmpT += 1;
            sb.append(cur.idx).append("\n");

            if(cur.time == cur.curT) {
                cur = null;
            }else if(cur.tmpT == t) {
                cur.tmpT = 0;
                wait.add(cur);
                cur = null;
            }

        }

        System.out.print(sb);
        br.close();
    }

    static class Node {
        int idx;
        int time;
        int curT;
        int tmpT;

        Node(int idx, int time, int curT,int tmpT) {
            this.idx = idx;
            this.time = time;
            this.curT = curT;
            this.tmpT = tmpT;
        }
    }

    static class P {
        int idx;
        int time;
        int come;

        P(int idx, int time, int come) {
            this.idx = idx;
            this.time = time;
            this.come = come;
        }
    }
}
