import java.io.*;
import java.util.*;

public class Main {
    static int sx = 0;
    static int sy = 0;
    static int lx = 0;
    static int ly = 0;

    static int[] dx = {-1,-1,1,1,-1,1,-1,1};
    static int[] dy = {-1,1,-1,1,-1,-1,1,1};

    static int[][] v;

    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        lx = Integer.parseInt(st.nextToken());
        ly = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        v = new int[10][10];
        v[sx][sy] = 1;
        q.add(new Node(sx,sy));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == lx && cur.y == ly){
                System.out.println(v[cur.x][cur.y] - 1);
                break;
            }

            move(cur.x,cur.y,-1,0,0,2);
            move(cur.x,cur.y,1,0,2,4);
            move(cur.x,cur.y,0,-1,4,6);
            move(cur.x,cur.y,0,1,6,8);
        }

        br.close();
    }

    static void print()
    {
        for(int i=0;i<=9;i++){
            for(int j=0;j<=8;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void move(int x,int y,int dirx,int diry,int s,int e){
        int nx = x + dirx;
        int ny = y + diry;

        if(nx < 0 || nx > 9 || ny < 0 || ny > 8)
            return;

        if(nx == lx && ny == ly)
            return;

        for(int i=s;i<e;i++){
            int n2x = nx;
            int n2y = ny;

            for(int j=1;j<=2;j++){
                n2x = n2x + dx[i];
                n2y = n2y + dy[i];

                if(n2x < 0 || n2x > 9 || n2y < 0 || n2y > 8)
                    break;

                if(j == 1 && n2x == lx && n2y == ly)
                    break;

                else if(j == 2 && v[n2x][n2y] == 0 || v[n2x][n2y] > v[x][y] + 1){
                    v[n2x][n2y] = v[x][y] + 1;
                    q.add(new Node(n2x,n2y));
                }
            }

        }
    }
}
class Node{
    int x;
    int y;

    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}
