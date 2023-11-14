import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static boolean[][] v;

    static boolean[] black;

    static ArrayList<Node> can = new ArrayList<>();

    static int result  = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if((board[i][j] == 2 || board[i][j] == 1) && !v[i][j]){
                    bfs(i,j);
                }
            }
        }

        black = new boolean[can.size()];

        travel(0,0,black.length);
        System.out.println(result);
        br.close();
    }
    static void travel(int num,int cur,int size){
        if(num == 2){
            int tmpCount = 0;
            boolean[][] tmpV = new boolean[n+1][m+1];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=m;j++)
                {
                    if(board[i][j] == 2 && !tmpV[i][j]){
                        tmpCount += check(i,j,tmpV);
                    }
                }
            }

            if(tmpCount > result){
                result = tmpCount;
            }
            // check
            return;
        }

        for(int i=cur;i<size;i++){
            if(!black[i]){
                black[i] = true;
                Node tmp = can.get(i);
                board[tmp.x][tmp.y] = 1;
                travel(num+1,i+1,size);
                board[tmp.x][tmp.y] = 0;
                black[i] = false;
            }
        }
    }

    static int check(int x,int y,boolean[][] tmpV){
        int count = 1;
        boolean open = false;
        Queue<Node> q = new LinkedList<>();
        tmpV[x][y] = true;
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 2){
                    if(!tmpV[nx][ny]){
                        tmpV[nx][ny] = true;
                        count += 1;
                        q.add(new Node(nx,ny));
                    }
                }

                else if(board[nx][ny] == 0){
                    open = true;
                }
            }
        }

        if(open){
            return 0;
        }

        return count;
    }

    static void bfs(int x,int y){
        Queue<Node> q = new LinkedList<>();
        v[x][y] = true;
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >=1 && nx <= n && ny >=1 && ny <= m){
                    if(!v[nx][ny]){
                        v[nx][ny] = true;

                        if(board[nx][ny] == 2 || board[nx][ny] == 1){
                            q.add(new Node(nx,ny));
                        }

                        else if(board[nx][ny] == 0){
                            can.add(new Node(nx,ny));
                        }
                    }
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


