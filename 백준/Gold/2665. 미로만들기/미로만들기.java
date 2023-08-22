import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=n;j++){
                board[i][j] = line.charAt(j-1) - '0';
            }
        }

        bfs();




        br.close();
    }

    static void bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][n+1];
        int[][] count = new int[n+1][n+1];
        q.add(new Point(1,1));
        visit[1][1] = true;
        if(board[1][1] == 0){
            count[1][1] = 1;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                if(!visit[nx][ny]){
                    visit[nx][ny] = true;

                    if(board[nx][ny] == 0){
                        count[nx][ny] = count[cur.x][cur.y] + 1;
                    }

                    else{
                        count[nx][ny] = count[cur.x][cur.y];
                    }

                    q.add(new Point(nx,ny));
                }

                else{
                    if(board[nx][ny] == 0 && count[nx][ny] > count[cur.x][cur.y] + 1){
                        count[nx][ny] = count[cur.x][cur.y] + 1;
                        q.add(new Point(nx,ny));
                    }

                    else if(board[nx][ny] == 1 && count[nx][ny] > count[cur.x][cur.y]){
                        count[nx][ny] = count[cur.x][cur.y];
                        q.add(new Point(nx,ny));
                    }
                }
            }
        }

        System.out.println(count[n][n]);
    }
}
class Point{
    int x;
    int y;

    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}
