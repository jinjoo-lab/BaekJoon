import java.util.*;
import java.io.*;

public class Main {

    static int max = 0;
    static int[] candy;
    static int n,m,k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        candy = new int[n+1];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 1 ; i <= n ; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(candy,1,n+1);

        Queue<Box> q = new ArrayDeque<>();
        q.add(new Box(0,0));
        boolean[] v = new boolean[90001];

        int nextCount = 0;
        int nextBox = 0;

        while(!q.isEmpty()) {
            Box cur = q.poll();

            if(cur.count % k == 0) {
                max = Math.max(max,cur.count);
            }

            if(cur.boxNum == m) {
                continue;
            }

            for(int i = 1 ; i <= n ; i++) {
                nextCount = cur.count + candy[i];
                nextBox = cur.boxNum + 1;

                if(!v[nextCount]) {
                    v[nextCount] = true;
                    q.add(new Box(nextBox,nextCount));
                }
            }
        }


        System.out.println(max);

        br.close();
    }

    static class Box {
        int boxNum;
        int count;

        Box(int boxNum,int count) {
            this.boxNum = boxNum;
            this.count = count;
        }
    }
}
