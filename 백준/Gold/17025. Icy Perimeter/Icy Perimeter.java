import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int max  = 0;
    static int per = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        char[][] board = new char[n+1][n+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();
            for(int j=1;j<=n;j++){
                board[i][j] = line.charAt(j-1);
            }
        }

        boolean[][] visit = new boolean[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(board[i][j] == '#' && !visit[i][j]){
                    visit[i][j] = true;
                    bfs(i,j,visit,board);
                }
            }
        }

        System.out.println(max+" "+per);
        br.close();
    }

    static void bfs(int x,int y,boolean[][] visit,char[][] board){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));

        int count = 1;
        int p = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) {
                    p = p + 1;
                    continue;
                }

                if(board[nx][ny] == '.'){
                    p = p + 1;
                }

                if(visit[nx][ny])
                    continue;

                if(board[nx][ny] == '#'){
                    visit[nx][ny] = true;
                    count = count + 1;
                    q.add(new Node(nx,ny));
                }
            }
        }

        if(max < count){
            max = count;
            per = p;
        }

        else if(max == count){
            per = Math.min(per , p);
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