import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[][] stopover;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        stopover = new int[n + 1][2];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stopover[i][0] = x;
            stopover[i][1] = y;
        }

        int l = 0;
        int r = cal(0,0,10000,10000);

        int result = r;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(go(mid)) {
                result = mid;
                r = mid - 1;
            }else {
                 l = mid + 1;
            }
        }


        System.out.println(result);
        br.close();
    }

    static boolean go(int target) {
        boolean[] v = new boolean[n+1];
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{0,0,0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cal(cur[0],cur[1],10000,10000) <= target)
                return true;

            if(cur[2] == k)
                continue;

            for(int i = 1 ; i <= n ; i++) {
                if(v[i])
                    continue;

                int nextV = cal(cur[0],cur[1],stopover[i][0],stopover[i][1]);

                if(nextV <= target) {
                    v[i] = true;
                    q.add(new int[]{stopover[i][0],stopover[i][1],cur[2] + 1});
                }
            }
        }

        return false;
    }

    static int cal(int x1,int y1,int x2,int y2) {
        double tmp = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

        return (int)tmp / 10 + (tmp % 10 > 0 ? 1 : 0);
    }
}
