import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static char[][] board;

    static int sx = 0;
    static int lx = 0;
    static int sy = 0;
    static int ly = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n+1][n+1];
        boolean f = false;
        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=n;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] =='#'){
                    if(!f){
                        f = true;
                        sx = i;
                        sy = j;
                    }

                    else{
                        lx = i;
                        ly = j;
                    }
                }
            }
        }


        search();
        br.close();
    }

    static void search(){
        int result = 2501;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx,sy,-1));
        int[][][] visit = new int[n+1][n+1][4];
        visit[sx][sy][0]= 1;
        visit[sx][sy][1]= 1;
        visit[sx][sy][2]= 1;
        visit[sx][sy][3]= 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == lx && cur.y == ly){
                result = Math.min(result,visit[lx][ly][cur.d] -1);
                continue;
            }

            if(board[cur.x][cur.y] == '#'){
                for(int i=0;i<4;i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 1 || nx > n || ny < 1 || ny > n)
                        continue;

                    if(board[nx][ny] == '*')
                        continue;

                    if(visit[nx][ny][i] == 0 || visit[nx][ny][i] > visit[cur.x][cur.y][i]){
                        visit[nx][ny] = visit[cur.x][cur.y];
                        q.add(new Node(nx,ny,i));
                    }
                }
            }

            else if(board[cur.x][cur.y] == '!'){

                if(cur.d == 0 || cur.d == 1){
                    for(int i=2;i<4;i++){
                       int nx = cur.x + dx[i];
                       int ny = cur.y + dy[i];

                        if(nx < 1 || nx > n || ny < 1 || ny > n)
                            continue;

                        if(board[nx][ny] == '*')
                            continue;

                        if(visit[nx][ny][i] == 0 || visit[nx][ny][i] > visit[cur.x][cur.y][cur.d] + 1){
                            visit[nx][ny][i] = visit[cur.x][cur.y][cur.d] + 1;
                            q.add(new Node(nx,ny,i));
                        }
                    }

                    int nx = cur.x + dx[cur.d];
                    int ny = cur.y + dy[cur.d];

                    if(nx < 1 || nx > n || ny < 1 || ny > n)
                        continue;

                    if(board[nx][ny] == '*')
                        continue;

                    if(visit[nx][ny][cur.d] == 0 || visit[nx][ny][cur.d] > visit[cur.x][cur.y][cur.d]){
                        visit[nx][ny][cur.d] = visit[cur.x][cur.y][cur.d];
                        q.add(new Node(nx,ny,cur.d));
                    }
                }

                else{
                    for(int i=0;i<2;i++){
                        int nx = cur.x + dx[i];
                        int ny = cur.y + dy[i];

                        if(nx < 1 || nx > n || ny < 1 || ny > n)
                            continue;

                        if(board[nx][ny] == '*')
                            continue;

                        if(visit[nx][ny][i] == 0 || visit[nx][ny][i] > visit[cur.x][cur.y][cur.d] + 1){
                            visit[nx][ny][i] = visit[cur.x][cur.y][cur.d] + 1;
                            q.add(new Node(nx,ny,i));
                        }
                    }

                    int nx = cur.x + dx[cur.d];
                    int ny = cur.y + dy[cur.d];

                    if(nx < 1 || nx > n || ny < 1 || ny > n)
                        continue;

                    if(board[nx][ny] == '*')
                        continue;

                    if(visit[nx][ny][cur.d] == 0 || visit[nx][ny][cur.d] > visit[cur.x][cur.y][cur.d]){
                        visit[nx][ny][cur.d] = visit[cur.x][cur.y][cur.d];
                        q.add(new Node(nx,ny,cur.d));
                    }
                }
            }

            else if(board[cur.x][cur.y] == '.'){
                int nx = cur.x + dx[cur.d];
                int ny = cur.y + dy[cur.d];

                if(nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                if(board[nx][ny] == '*')
                    continue;

                if(visit[nx][ny][cur.d] == 0 || visit[nx][ny][cur.d] > visit[cur.x][cur.y][cur.d]){
                    visit[nx][ny][cur.d] = visit[cur.x][cur.y][cur.d];
                    q.add(new Node(nx,ny,cur.d));
                }
            }
        }

        System.out.println(result);
    }

    static void print(int[][] visit){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Node{
    int x;
    int y;
    int d;

    Node(int x,int y,int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
