import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        int sx = 0;
        int sy = 0;

        char max = 0;
        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }

            }
        }

        bfs(sx,sy);

        br.close();
    }

    static void bfs(int sx,int sy){
        Queue<Node> q = new LinkedList<>();
        int[][][] visit = new int[n+1][m+1][k+1];

        q.add(new Node(sx,sy,0));
        visit[sx][sy][0] = 1;
        int maxFind = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.num < maxFind)
                continue;

            if(board[cur.x][cur.y] - '0' == k && cur.num == k){
                System.out.println(visit[cur.x][cur.y][cur.num] -1);
                return;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 'X')
                    continue;

                if(board[nx][ny] == '.' || board[nx][ny] == 'S'){
                    if(visit[nx][ny][cur.num] == 0){
                        visit[nx][ny][cur.num] = visit[cur.x][cur.y][cur.num] + 1;
                        q.add(new Node(nx,ny,cur.num));
                    }
                }

                else{
                    int tmp = board[nx][ny] - '0';
                    if(cur.num +1 == tmp && visit[nx][ny][tmp] == 0){
                        visit[nx][ny][tmp] = visit[cur.x][cur.y][cur.num] + 1;
                        q.add(new Node(nx,ny,tmp));
                        maxFind = tmp;
                    }

                    else if(visit[nx][ny][cur.num+1] == 0){
                        visit[nx][ny][cur.num] = visit[cur.x][cur.y][cur.num] + 1;
                        q.add(new Node(nx,ny,cur.num));
                    }
                }
            }
        }
    }

    static void print(int[][][] visit){
        for(int i=1;i<=n;i++){

        }
    }
}
class Node{
    int x;
    int y;

    int num;

    Node(int x,int y,int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
}
