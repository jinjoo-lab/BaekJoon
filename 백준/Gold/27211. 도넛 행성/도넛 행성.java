import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

    static int[][] board;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){

            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(board[i][j] == 0 && !v[i][j]){

                    v[i][j] = true;
                    go(i,j);
                    result += 1;
                }
            }
        }

        System.out.println(result);
        bf.close();
    }

    static void go(int x,int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));


        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1)
                    nx = n;

                else if(nx > n)
                    nx = 1;

                if(ny < 1)
                    ny = m;

                else if(ny > m)
                    ny = 1;


                if(board[nx][ny] == 1)
                    continue;

                if(!v[nx][ny]){
                    v[nx][ny] = true;
                    q.add(new Node(nx,ny));
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
