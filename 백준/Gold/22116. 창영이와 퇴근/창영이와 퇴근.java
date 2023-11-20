import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search();

        br.close();
    }

    static void search(){
        int MAX = Integer.MAX_VALUE;

        int[][] v = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                v[i][j] = MAX;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.num - y.num
        );
        v[1][1] = 0;
        pq.add(new Node(1,1,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.x == n && cur.y == n){
                continue;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                int tmp = Math.abs(board[nx][ny] - board[cur.x][cur.y]);

                if(v[nx][ny] > Math.max(v[cur.x][cur.y],tmp)){
                    v[nx][ny] = Math.max(v[cur.x][cur.y],tmp);
                    pq.add(new Node(nx,ny,v[nx][ny]));
                }
            }
        }

        System.out.println(v[n][n]);
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

