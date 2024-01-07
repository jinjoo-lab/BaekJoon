import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int m = 0;

    static int[][] board;

    static int[][] v;

    static int[] dx = {0,0,-1,1,1,1,-1,-1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};

    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=m;j++){
                char cur = line.charAt(j-1);

                if(cur >= '1' && cur <= '9'){
                    board[i][j] = cur - '0';
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] >= 1 && board[i][j] <= 8){
                    int count = 0;

                    for(int k=0;k<8;k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];


                        if(board[nx][ny] == 0){
                            count += 1;
                        }
                    }

                    if(count >= board[i][j]){
                        q.add(new Node(i,j));
                    }

                    v[i][j] = count;
                }
            }
        }

        boolean keep = true;
        int result = 0;

        while(keep){
            keep = bfs();
            result += 1;
        }


        System.out.println(result);

        bf.close();
    }

    static boolean bfs(){
        boolean keep = false;

        int size = q.size();

        while(size != 0){

            Node cur = q.poll();

            if(board[cur.x][cur.y] != 0){
                board[cur.x][cur.y] = 0;
            }

            for(int i=0;i<8;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(board[nx][ny] == 0 || board[nx][ny] == 9)
                    continue;

                v[nx][ny] += 1;


                if(v[nx][ny] >= board[nx][ny]){
                    keep = true;
                    board[nx][ny] = 0;
                    q.add(new Node(nx,ny));
                }
            }

            size -= 1;
        }

        return keep;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static void print(int[][] board){
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

    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}