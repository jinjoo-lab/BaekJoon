import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] wb;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        wb = new int[n];
        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int c = Integer.parseInt(st.nextToken());
            wb[i] = c;
        }

        graph = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0 ; j < n ; j++) {
                int next = Integer.parseInt(st.nextToken());

                if(next == 0)
                    continue;

                graph[i].add(new int[]{j,next});
            }
        }

        int[] count = new int[n];

        for(int i = 1 ; i < n ; i++) {
            count[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[1] - y[1]
        );

        pq.add(new int[]{0,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] > count[cur[0]])
                continue;

            for(int[] next : graph[cur[0]]) {
                if(wb[cur[0]] != wb[next[0]]) {
                    if(count[next[0]] > cur[1] + 1) {
                        count[next[0]] = cur[1] + 1;
                        pq.add(new int[]{next[0],count[next[0]]});
                    }
                }else {
                    if(count[next[0]] > cur[1]) {
                        count[next[0]] = cur[1];
                        pq.add(new int[]{next[0],count[next[0]]});
                    }
                }
            }
        }


        int[][] dis = new int[n][count[m] + 1];
        for(int i = 1 ; i < n ; i++) {
            for(int j = 0 ; j <= count[m]; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        pq.add(new int[]{0,0,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int[] next : graph[cur[0]]) {
                int nextV = cur[2] + next[1];
                int nextC = wb[cur[0]] == wb[next[0]] ? cur[1] : cur[1] + 1;

                if(nextC > count[m])
                    continue;

                if(dis[next[0]][nextC] > nextV) {
                    dis[next[0]][nextC] = nextV;
                    pq.add(new int[]{next[0],nextC,dis[next[0]][nextC]});
                }
            }
        }

        System.out.println(count[m]+" "+dis[m][count[m]]);
        br.close();
    }

    static void print(int[] count) {
        for(int i = 0 ; i < n ; i++) {
            System.out.print(count[i]+" ");
        }
        System.out.println();
    }
}
