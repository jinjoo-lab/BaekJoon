import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static int m = 0;
    static int[][] board = new int[51][51];

    static int[][] v;

    static int[] dx = {0,0,-1,1,1,1,-1,-1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++){

            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == 0)
                    bfs(i,j);
            }
        }

        System.out.println(result);

        bf.close();
    }

    static void bfs(int x,int y){

        Queue<Node> q = new LinkedList();
        q.add(new Node(x,y,0));
        v = new int[51][51];
        v[x][y] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<8;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny] > 0)
                    continue;

                v[nx][ny]= 1;

                if(board[nx][ny] == 1){

                    result = Math.max(result, cur.d + 1);
                    return;
                }

                q.add(new Node(nx,ny,cur.d+1));
            }
        }
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




