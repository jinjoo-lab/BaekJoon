import java.util.*;
import java.io.*;

public class Main {

    static int n,t;
    static int[][] board;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[n+1][n+1][4];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                for(int k = 0 ; k <= 3 ; k++) {
                    dp[i][j][k] = Long.MAX_VALUE;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x.v,y.v)
        );

        dp[1][1][0] = 0;
        pq.add(new Node(1,1,0,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dp[cur.x][cur.y][cur.c] < cur.v)
                continue;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nc = cur.c + 1;

                if(isOut(nx,ny))
                    continue;

                long nextV = cur.v + t + ((nc == 3) ? board[nx][ny] : 0);

                if(nc == 3)
                    nc = 0;

                if(dp[nx][ny][nc] > nextV) {
                    dp[nx][ny][nc] = nextV;
                    pq.add(new Node(nx,ny,nc,nextV));
                }
            }
        }

        Long min = Math.min(Math.min(dp[n][n][0],dp[n][n][1]),dp[n][n][2]);

        System.out.println(min);

        br.close();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n) {
            return true;
        }

        return false;
    }

    static class Node {
        int x;
        int y;
        int c;
        long v;


        Node(int x,int y,int c,long v) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.v = v;
        }
    }
}
