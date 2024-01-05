import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int sx = 0;
    static int sy = 0;
    static int[][] board;

    static int result = -1;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        int land = 2;

        for(int i=1;i<=n;i++){

            String line = bf.readLine();

            for(int j=1;j<=m;j++){

                char cur = line.charAt(j-1);

                if(cur == '#'){
                    board[i][j] = -1;
                }else if(cur == 'S'){
                    sx = i;
                    sy = j;
                }else if(cur == 'C'){
                    board[i][j] = land;
                    land *= 2;
                }
            }
        }

        bfs();
        System.out.println(result);
        bf.close();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        int[][][][] v = new int[n+1][m+1][4][7];

        for(int i=0;i<4;i++){
            int nx = sx + dx[i];
            int ny = sy + dy[i];

            if(isOut(nx,ny))
                continue;

            if(board[nx][ny] == 0){
                v[nx][ny][i][0] = 1;
                q.add(new Node(nx,ny,i,0));
            }else if(board[nx][ny] >= 1){
                v[nx][ny][i][board[nx][ny]] = 1;
                q.add(new Node(nx,ny,i,board[nx][ny]));
            }
        }

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.f == 6){
                if(result == -1 || result > v[cur.x][cur.y][cur.d][cur.f]){
                    result = v[cur.x][cur.y][cur.d][cur.f];
                }
                return;
            }

            for(int i=0;i<4;i++){

                if(cur.d == i)
                    continue;

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(board[nx][ny] == 0){
                    if(v[nx][ny][i][cur.f] == 0 || v[nx][ny][i][cur.f] > v[cur.x][cur.y][cur.d][cur.f] + 1){
                        v[nx][ny][i][cur.f] = v[cur.x][cur.y][cur.d][cur.f] + 1;
                        q.add(new Node(nx,ny,i,cur.f));
                    }
                }else if(board[nx][ny] >= 1){
                    if(already(cur.f,board[nx][ny])){

                        if(v[nx][ny][i][cur.f] == 0 || v[nx][ny][i][cur.f] > v[cur.x][cur.y][cur.d][cur.f] + 1){
                            v[nx][ny][i][cur.f] = v[cur.x][cur.y][cur.d][cur.f] + 1;
                            q.add(new Node(nx,ny,i,cur.f));
                        }
                    }else{
                        if(v[nx][ny][i][cur.f + board[nx][ny]] == 0 || v[nx][ny][i][cur.f + board[nx][ny]] > v[cur.x][cur.y][cur.d][cur.f] + 1 ){

                            v[nx][ny][i][cur.f + board[nx][ny]] = v[cur.x][cur.y][cur.d][cur.f] + 1;
                            q.add(new Node(nx,ny,i,cur.f + board[nx][ny]));
                        }
                    }
                }

            }
        }

    }
    static boolean already(int cur,int t){

        int[] arr = {2,4};

        for(int i=1;i>=0;i--){
            if(cur >= arr[i]){
                if(t == cur)
                    return true;

                cur -= arr[i];
            }
        }

        return false;
    }

    static boolean isOut(int nx,int ny){
        if(nx < 1 || nx > n || ny < 1 || ny > m)
            return true;

        if(board[nx][ny] == -1)
            return true;

        return false;
    }

}
class Node{
    int x;
    int y;
    int d;

    int f;

    Node(int x,int y,int d,int f){
        this.x = x;
        this.y = y;
        this.d = d;
        this.f = f;
    }
}
