import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;

    static int sx = 0;
    static int sy = 0;
    static int lx = 0;
    static int ly = 0;

    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=m;j++){
                char cur = line.charAt(j-1);

                if(cur == 'g'){
                    board[i][j] = 1;
                }else if(cur == 'F'){
                    lx = i;
                    ly = j;
                }else if(cur =='S'){
                    sx = i;
                    sy = j;
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(board[i][j] == 1){
                    for(int k=0;k<4;k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 1 || nx > n || ny < 1 || ny > m)
                            continue;

                        if(board[nx][ny] == 1)
                            continue;

                        board[nx][ny] = 2;
                    }
                }

            }
        }

        move();

        bf.close();
    }

    static void move() {

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x, y) -> {
                    if (x.t1 == y.t1)
                        return x.t2 - y.t2;

                    return x.t1 - y.t1;
                }
        );

        pq.add(new Node(sx, sy, 0, 0));

        int[][][] dis = new int[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dis[i][j][0] = Integer.MAX_VALUE;
                dis[i][j][1] = Integer.MAX_VALUE;
            }
        }

        dis[sx][sy][0] = 0;
        dis[sx][sy][1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == lx && cur.y == ly) {
                if (board[lx][ly] == 1) {
                    System.out.println((dis[lx][ly][0] - 1) + " " + dis[lx][ly][1]);
                } else if (board[lx][ly] == 2) {
                    System.out.println((dis[lx][ly][0]) + " " + (dis[lx][ly][1] - 1));
                } else {
                    System.out.println((dis[lx][ly][0]) + " " + dis[lx][ly][1]);
                }

                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;


                if (board[nx][ny] == 1) {
                    if (dis[nx][ny][0] > dis[cur.x][cur.y][0] + 1) {
                        dis[nx][ny][0] = dis[cur.x][cur.y][0] + 1;
                        dis[nx][ny][1] = dis[cur.x][cur.y][1];

                        pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                    } else if (dis[nx][ny][0] == dis[cur.x][cur.y][0] + 1) {
                        if (dis[nx][ny][1] > dis[cur.x][cur.y][1]) {
                            dis[nx][ny][1] = dis[cur.x][cur.y][1];

                            pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                        }
                    }
                } else if (board[nx][ny] == 2) {
                    if (dis[nx][ny][0] > dis[cur.x][cur.y][0]) {
                        dis[nx][ny][0] = dis[cur.x][cur.y][0];
                        dis[nx][ny][1] = dis[cur.x][cur.y][1] + 1;

                        pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                    } else if (dis[nx][ny][0] == dis[cur.x][cur.y][0]) {
                        if (dis[nx][ny][1] > dis[cur.x][cur.y][1] + 1) {
                            dis[nx][ny][1] = dis[cur.x][cur.y][1] + 1;

                            pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                        }
                    }
                } else if (board[nx][ny] == 0) {
                    if (dis[nx][ny][0] > dis[cur.x][cur.y][0]) {
                        dis[nx][ny][0] = dis[cur.x][cur.y][0];
                        dis[nx][ny][1] = dis[cur.x][cur.y][1];

                        pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                    } else if (dis[nx][ny][0] == dis[cur.x][cur.y][0] && dis[nx][ny][1] > dis[cur.x][cur.y][1]) {
                        dis[nx][ny][1] = dis[cur.x][cur.y][1];
                        pq.add(new Node(nx, ny, dis[nx][ny][0], dis[nx][ny][1]));
                    }
                }
            }
        }
    }
}
class Node{
    int x;
    int y;
    int t1;
    int t2;

    Node(int x,int y,int t1,int t2){
        this.x = x;
        this.y = y;
        this.t1 = t1;
        this.t2 = t2;
    }
}