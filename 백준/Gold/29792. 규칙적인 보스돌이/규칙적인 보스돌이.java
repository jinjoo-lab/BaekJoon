import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;
    static long[] attack;
    static long[][] boss;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        attack = new long[n+1];
        for(int i = 1 ; i <= n ; i++) {
            long cur = Long.parseLong(br.readLine());
            attack[i] = cur;
        }

        boss = new long[k+1][2];
        for(int i = 1 ; i <= k ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            long hp = Long.parseLong(st.nextToken());
            long gold = Long.parseLong(st.nextToken());

            boss[i][0] = hp;
            boss[i][1] = gold;
        }

        for(int i = 1 ; i <= n ; i++) {
            cal(i);
        }

        long result = 0;
        while(m --> 0) {
            result += maxQ.poll();
        }

        System.out.println(result);

        br.close();
    }

    static PriorityQueue<Long> maxQ = new PriorityQueue<>(
            (x,y) -> Long.compare(y,x)
    );
    static long[] time;
    static long MAX = 15 * 60;
    static long mGold = 0;
    static long[] goldy = new long[n+1];

    static void cal(int idx) {
        time = new long[k+1];

        for(int i = 1 ; i <= k ; i++) {
            time[i] = boss[i][0] / attack[idx] + (boss[i][0] % attack[idx] == 0 ? 0 : 1);
        }

        mGold = 0;
        findMax(1,0,0);
        maxQ.add(mGold);
    }

    static void findMax(int idx,long curT,long gold) {

        if(idx > k) {
            mGold = Math.max(mGold, gold);
            return;
        }

        if(time[idx] != 0 && curT + time[idx] <= MAX) {
            findMax(idx + 1, curT + time[idx], gold + boss[idx][1]);
        }
        findMax(idx + 1, curT,gold);
    }
}
