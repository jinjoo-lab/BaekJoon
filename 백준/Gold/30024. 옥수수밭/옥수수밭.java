import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int k;
    static int[][] board;
    static boolean[][] v;

    static long result = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static PriorityQueue<Point> pq = new PriorityQueue<>(
            (x,y) -> y.v - x.v
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new boolean[n+1][m+1];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        k = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= n ; i++) {
            if(!v[i][1]) {
                v[i][1] = true;
                pq.add(new Point(i, 1, board[i][1]));
            }

            if(!v[i][m]) {
                v[i][m] = true;
                pq.add(new Point(i, m, board[i][m]));
            }
        }

        for(int i = 1 ; i <= m ; i++) {

            if(!v[1][i]) {
                v[1][i] = true;
                pq.add(new Point(1, i, board[1][i]));
            }

            if(!v[n][i]) {
                v[n][i] = true;
                pq.add(new Point(n, i, board[n][i]));
            }
        }

        for(int i = 0 ; i < k ; i++) {
            if(!pq.isEmpty()) {
                Point tmp = pq.poll();
                sb.append(tmp.x+" "+tmp.y+"\n");

                for(int j = 0 ; j < 4 ; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];

                    if(nx < 1 || nx > n || ny < 1 || ny > m)
                        continue;

                    if(v[nx][ny])
                        continue;
                    
                    v[nx][ny] = true;

                    pq.add(new Point(nx,ny,board[nx][ny]));
                }
            }
        }

        System.out.println(sb);

        br.close();
    }

    static class Point {
        int x;
        int y;
        int v;

        Point(int x,int y,int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}
