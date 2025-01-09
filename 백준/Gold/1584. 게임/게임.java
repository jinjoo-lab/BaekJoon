import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[][] board = new int[501][501];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= n ; a++) {
            st = new StringTokenizer(br.readLine()," ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int i = Math.min(x1,x2) ; i <= Math.max(x1,x2) ; i++) {
                for(int j = Math.min(y1,y2) ; j <= Math.max(y1,y2) ; j++) {
                    board[i][j] = 1;
                }
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= m ; a++) {
            st = new StringTokenizer(br.readLine()," ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int i = Math.min(x1,x2) ; i <= Math.max(x1,x2) ; i++) {
                for(int j = Math.min(y1,y2) ; j <= Math.max(y1,y2) ; j++) {
                    board[i][j] = 2;
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        int[][] dis = new int[501][501];

        for(int i = 0 ; i <= 500 ; i++) {
            for(int j = 0 ; j <= 500 ; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        dis[0][0] = 0;
        pq.add(new int[]{0,0,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(dis[cur[0]][cur[1]] < cur[2])
                continue;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx > 500 || ny < 0 || ny > 500)
                    continue;

                if(board[nx][ny] == 2)
                    continue;

                if(dis[nx][ny] > cur[2] + board[nx][ny]) {
                    dis[nx][ny] = cur[2] + board[nx][ny];
                    pq.add(new int[]{nx,ny,dis[nx][ny]});
                }
            }
        }

        System.out.println(dis[500][500] == Integer.MAX_VALUE ? -1 : dis[500][500]);

        br.close();
    }
}
