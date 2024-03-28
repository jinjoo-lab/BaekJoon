import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int result = -1;
    static int n = 0;
    static int m = 0;
    static char[][] board;

    static int[][][] v;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int sx,sy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        v = new int[n+1][m+1][1 << 7];

        for(int i = 1 ; i <= n ; i++){
            char[] curLine = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = curLine[j - 1];

                if(board[i][j] == '0'){
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs();
        System.out.println(result);


        br.close();
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static void bfs(){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx,sy,0));
        v[sx][sy][0] = 1;

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(board[cur.x][cur.y] == '1'){
                result = v[cur.x][cur.y][cur.v] - 1;
                break;
            }

            for(int i = 0 ; i< 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(board[nx][ny] == '#')
                    continue;

                if(board[nx][ny] == '.' || board[nx][ny] == '1' || board[nx][ny] == '0'){
                    if(v[nx][ny][cur.v] == 0){
                        v[nx][ny][cur.v] = v[cur.x][cur.y][cur.v] + 1;
                        q.add(new Point(nx,ny,cur.v));
                    }
                }

                else if(board[nx][ny] >= 'A' && board[nx][ny] <= 'F'){
                    if((cur.v & (1 << (board[nx][ny] -'A'))) != 0){
                        if(v[nx][ny][cur.v] == 0){
                            v[nx][ny][cur.v] = v[cur.x][cur.y][cur.v] + 1;
                            q.add(new Point(nx,ny,cur.v));
                        }
                    }
                }

                else if(board[nx][ny] >= 'a' && board[nx][ny] <= 'f'){
                    int tmpV = cur.v | (1 << (board[nx][ny] - 'a'));

                    if(v[nx][ny][tmpV] == 0){
                        v[nx][ny][tmpV] = v[cur.x][cur.y][cur.v] + 1;
                        q.add(new Point(nx,ny,tmpV));
                    }
                }
            }
        }
    }

    static class Point{
        int x;
        int y;
        int v;

        Point(int x,int y,int v){
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}
