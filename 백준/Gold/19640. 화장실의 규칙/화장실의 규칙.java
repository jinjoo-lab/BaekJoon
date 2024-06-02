import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        PriorityQueue<Turn> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.d == y.d) {

                        if(x.h == y.h) {
                            return x.l - y.l;
                        }

                        return y.h - x.h;
                    }

                    return y.d - x.d;
                }
        );

        Queue<Turn>[] qList = new ArrayDeque[m+1];
        for(int i = 1 ; i <= m ; i++) {
            qList[i] = new ArrayDeque<>();
        }

        int curL = 1;

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            qList[curL].add(new Turn(i,curL,d,h));

            curL++;

            if(curL > m) {
                curL = 1;
            }
        }

        for(int i = 1 ; i <= m ; i ++) {
            if(!qList[i].isEmpty()) {
                pq.add(qList[i].poll());
            }
        }

        int result = 1;

        while(!pq.isEmpty()) {
            Turn cur = pq.poll();

            if(cur.idx == k + 1) {
                break;
            }

            if(!qList[cur.l].isEmpty()) {
                pq.add(qList[cur.l].poll());
            }

            result++;
        }


        System.out.println(result - 1);
        br.close();
    }
}

class Turn {
    int idx;
    int l;
    int d;
    int h;

    Turn(int idx,int l,int d,int h) {
        this.idx = idx;
        this.l = l;
        this.d = d;
        this.h = h;
    }
}
