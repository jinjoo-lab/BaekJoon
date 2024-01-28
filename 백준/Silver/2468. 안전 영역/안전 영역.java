import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;


    static int min = 101;
    static int max = 0;
    static int[][] board;

    static boolean[][] v;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        v = new boolean[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(board[i][j],min);
                max = Math.max(board[i][j],max);
            }
        }

        int result = 1;

        for(int h=min;h<=max;h++){
            int count = 0;
            v = new boolean[n+1][n+1];

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(board[i][j] > h && !v[i][j]){
                        bfs(i,j,h);
                        count += 1;
                    }
                }
            }

            result = Math.max(result,count);
        }
        System.out.println(result);

        bf.close();
    }

    static void bfs(int x,int y,int h){
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y));
        v[x][y] = true;

        while(!q.isEmpty()){

            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 ||ny > n)
                    continue;

                if(board[nx][ny] <= h || v[nx][ny])
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx,ny));
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




