
import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static char[][] board;
    static int sx,sy,lx,ly;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");

        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        lx = Integer.parseInt(st.nextToken());
        ly = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = arr[j - 1];
            }
        }

        PriorityQueue<Point> q = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );
        int[][] v = new int[n+1][m+1];
        v[sx][sy] = 1;
        q.add(new Point(sx,sy,0));

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.x == lx && cur.y == ly){
                System.out.println(cur.c - 1);
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == '*')
                    continue;

                int tmpV = (board[nx][ny] == '1' || board[nx][ny] == '#') ? 1 : 0;

                if(v[nx][ny] == 0 || v[nx][ny] > v[cur.x][cur.y] + tmpV){
                    v[nx][ny] = v[cur.x][cur.y] + tmpV;
                    q.add(new Point(nx,ny,v[nx][ny]));
                }
            }
        }

        br.close();
    }

    static class Point{
        int x;
        int y;
        int c;

        Point(int x,int y,int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}