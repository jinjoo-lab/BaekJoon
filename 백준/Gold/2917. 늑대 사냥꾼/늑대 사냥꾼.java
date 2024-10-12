import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static char[][] board;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int sx,sy,ex,ey;
    static PriorityQueue<Node> pq = new PriorityQueue<>(
            (x,y) -> x.dis - y.dis
    );
    static int[][] dis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        dis = new int[n+1][m+1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            char[] tmp = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = tmp[j-1];

                if(board[i][j] == 'V') {
                    sx = i;
                    sy = j;
                }else if(board[i][j] == 'J') {
                    ex = i;
                    ey = j;
                }else if(board[i][j] == '+') {
                    pq.add(new Node(i,j,0));
                    dis[i][j] = 0;
                }
            }
        }

        go();
        find();

        br.close();
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> y.dis - x.dis
        );

        int[][] v = new int[n+1][m+1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                v[i][j] = -1;
            }
        }

        pq.add(new Node(sx,sy,dis[sx][sy]));
        v[sx][sy] = dis[sx][sy];

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.x == ex && cur.y == ey) {
                System.out.println(cur.dis);
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(v[nx][ny] < Math.min(cur.dis,dis[nx][ny])) {
                    v[nx][ny] = Math.min(cur.dis,dis[nx][ny]);
                    pq.add(new Node(nx,ny,v[nx][ny]));
                }
            }
        }
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static void print(int[][] dis) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void go() {
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dis[cur.x][cur.y] < cur.dis)
                continue;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny) || board[nx][ny] == '+')
                    continue;

                if(dis[nx][ny] > dis[cur.x][cur.y] + 1) {
                    dis[nx][ny] = dis[cur.x][cur.y] + 1;
                    pq.add(new Node(nx,ny,dis[nx][ny]));
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int dis;

        Node(int x,int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
