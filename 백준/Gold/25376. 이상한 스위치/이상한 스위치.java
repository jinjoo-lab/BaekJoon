import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int result = -1;
    static int[] start;
    static ArrayList<Integer>[] graph;
    static int last;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        start = new int[n+1];
        graph = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            start[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int num = Integer.parseInt(st.nextToken());
            graph[i].add(i);

            for(int j = 1 ; j <= num ; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                graph[i].add(tmp);
            }
        }

        int startNum = 0;

        for(int i = 1 ; i <= n ; i++) {
            if(start[i] == 1)
                startNum += (1 << (i - 1));
        }

        last = (1 << (n)) - 1;

        go(startNum);
        System.out.println(result);
        br.close();
    }

    static void go(int startNum) {
        int[] dp = new int[1 << (n+1)];

        dp[startNum] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(startNum);

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == last) {
                if(result == -1) {
                    result = dp[cur] - 1;
                }else {
                    result = Math.min(result,dp[cur] - 1);
                }
                continue;
            }

            for(int i = 1 ; i <= n ; i++) {
                int tmpNum = cur;

                if((tmpNum & 1<<(i -1)) > 0)
                    continue;


                for(int next : graph[i]) {
                    tmpNum ^= 1 << (next - 1);
                }


                if(dp[tmpNum] == 0 || dp[tmpNum] > dp[cur] + 1) {
                    dp[tmpNum] = dp[cur] + 1;
                    q.add(tmpNum);
                }
            }
        }
    }
}
