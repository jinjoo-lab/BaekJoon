import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int sx = 0,sy = 0,ex = 0,ey = 0;

    static int[] dx = {0,1,0,-1,0};
    static int[] dy = {1,0,-1,0,0};

    static char[][] board;

    static boolean[][][] sight;

    // 0 : 오른쪽, 1 : 아래, 2 : 왼쪽, 3 : 위

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine(), " ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        sight = new boolean[n+1][m+1][4];

        Queue<Node> ghost = new LinkedList<>();

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] >= '0' && board[i][j] <= '3'){
                    ghost.add(new Node(i,j,board[i][j] - '0'));
                }
            }
        }

        makeSight(ghost);


        search();

        bf.close();
    }

    static void search(){
        Queue<Node> q = new LinkedList<>();
        int[][][] v = new int[n+1][m+1][4];
        q.add(new Node(sx,sy,0));
        v[sx][sy][0] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == ex && cur.y == ey){
                System.out.println(cur.d);
                return;
            }

            for(int i=0;i<5;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int curSight = (cur.d + 1) % 4 ;

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == '#')
                    continue;

                if(sight[nx][ny][curSight])
                    continue;

                if(v[nx][ny][curSight] > 0){
                    continue;
                }

                v[nx][ny][curSight] = v[cur.x][cur.y][cur.d % 4] + 1;
                q.add(new Node(nx,ny,cur.d + 1));
            }
        }

        System.out.println("GG");
    }

    static void print(){

        for(int k=0;k<=3;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=m;j++){
                    if(sight[i][j][k])
                        System.out.print(1+" ");

                    else
                        System.out.print(0+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();

    }

    static void makeSight(Queue<Node> q){

        int nx=0,ny=0;
        int idx = 0;

        for(int i=0;i<=3;i++){
            int size = q.size();

            while(size != 0){
                Node cur = q.poll();
                size -= 1;
                sight[cur.x][cur.y][i] = true;
                idx= 1;

                while(true){
                    nx = cur.x + (idx * dx[cur.d]);
                    ny = cur.y + (idx * dy[cur.d]);

                    if(nx < 1 || nx > n || ny < 1 || ny > m)
                        break;

                    if(board[nx][ny] =='#')
                        break;

                    if(board[nx][ny] >= '0' && board[nx][ny] <= '3')
                        break;

                    sight[nx][ny][i] = true;
                    idx += 1;
                }

                q.add(new Node(cur.x,cur.y,changeDir(cur.d)));
            }
        }
    }


    static int changeDir(int cur){
        if(cur == 3)
            return 0;

        return cur + 1;
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
