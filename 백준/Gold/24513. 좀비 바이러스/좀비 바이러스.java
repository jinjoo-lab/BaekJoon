import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[][] v;
    static Queue<Node> one = new LinkedList<>();
    static Queue<Node> second = new LinkedList<>();

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == 1){
                    one.add(new Node(i,j,1));
                    v[i][j] = 1;
                }else if(board[i][j] == 2){
                    second.add(new Node(i,j,2));
                    v[i][j] = 1;
                }
            }
        }

        while(one.size() != 0 || second.size() != 0){
            go();
        }

        int[] result = new int[4];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == -1)
                    continue;

                result[board[i][j]] += 1;
            }
        }

        System.out.println(result[1] +" "+result[2] +" "+result[3]);
        br.close();
    }

    static void go(){
        int size = one.size();

        while(size != 0){
            size -= 1;
            Node cur = one.poll();

            if(board[cur.x][cur.y] == 3)
                continue;

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 0 && v[nx][ny] == 0){
                    board[nx][ny] = 1;
                    v[nx][ny] = v[cur.x][cur.y] + 1;
                    one.add(new Node(nx,ny,1));
                }
            }
        }

        size = second.size();

        while(size != 0){
            size -= 1;
            Node cur = second.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 0 && v[nx][ny] == 0){
                    board[nx][ny] = 2;
                    v[nx][ny] = v[cur.x][cur.y] + 1;
                    second.add(new Node(nx,ny,2));
                }

                else if(board[nx][ny] == 1 && v[nx][ny] == v[cur.x][cur.y] + 1){
                    board[nx][ny] = 3;
                }
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

    int num;

    Node(int x,int y,int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
}
