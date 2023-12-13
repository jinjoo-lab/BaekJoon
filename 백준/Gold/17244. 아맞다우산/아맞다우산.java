import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int sx = 0;

    static int sy = 0;

    static int lx = 0;
    static int ly = 0;

    static int count = 0;

    static int[][] board;

    static int[][][] v;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1][40];

        for(int i=1;i<=n;i++){

            String line = bf.readLine();

            for(int j=1;j<=m;j++){

                char cur = line.charAt(j-1);

                if(cur == '#'){
                    board[i][j] = -1;
                }else if(cur == 'S'){
                    sx = i;
                    sy = j;
                }else if(cur == 'X'){
                    board[i][j] = (int)Math.pow(2,count);
                    count += 1;
                }else if(cur == 'E'){
                    lx = i;
                    ly = j;
                }

            }
        }


        int full = 0;

        for(int i=0;i<count;i++){
            full += (int)Math.pow(2,i);
        }


        bfs(full);

        bf.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int full){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx,sy,0));
        v[sx][sy][0] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.c == full && cur.x == lx && cur.y == ly){
                System.out.println(v[lx][ly][full] - 1);
                return;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == -1)
                    continue;


                if(board[nx][ny] == 0){
                    if(v[nx][ny][cur.c] == 0 || v[nx][ny][cur.c] > v[cur.x][cur.y][cur.c] + 1){
                        v[nx][ny][cur.c] = v[cur.x][cur.y][cur.c] + 1;
                        q.add(new Node(nx,ny,cur.c));
                    }
                }
                else if(board[nx][ny] >= 1){

                    if(already(cur.c,board[nx][ny])) {
                        if(v[nx][ny][cur.c] == 0 || v[nx][ny][cur.c] > v[cur.x][cur.y][cur.c] + 1){
                            v[nx][ny][cur.c] = v[cur.x][cur.y][cur.c] + 1;
                            q.add(new Node(nx,ny,cur.c));
                        }
                        continue;
                    }

                    int tmp = cur.c + board[nx][ny];

                    if(v[nx][ny][tmp] == 0 || v[nx][ny][tmp] > v[cur.x][cur.y][cur.c] + 1){
                        v[nx][ny][tmp] = v[cur.x][cur.y][cur.c] + 1;
                        q.add(new Node(nx,ny,tmp));
                    }
                }
            }
        }
    }

    static boolean already(int it,int t){

        int max = (int)Math.pow(2,count-1);

        while(max > 0){

            if(max <= it){
                it -= max;

                if(max == t && it >= 0){
                    return true;
                }
            }

            max /= 2;
        }

        return false;
    }
}
class Node{
    int x;
    int y;

    int c;

    Node(int x,int y,int c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
