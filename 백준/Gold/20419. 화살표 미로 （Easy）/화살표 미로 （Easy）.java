import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;

    // R D L U
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                if(arr[j-1] == 'R'){
                    board[i][j] = 0;
                }else if(arr[j-1] == 'D'){
                    board[i][j] = 1;
                }else if(arr[j-1] == 'L'){
                    board[i][j] = 2;
                }else{
                    board[i][j] = 3;
                }
            }
        }

        bfs();

        br.close();
    }

    static void bfs(){
        String result = "No";

        int[][][][] v = new int[n+1][m+1][k+1][k+1];
        v[1][1][k][k] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1,1,k,k));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == n && cur.y == m){
                result = "Yes";
                break;
            }

            int dir = board[cur.x][cur.y];

            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if(!isOut(nx,ny) && v[nx][ny][cur.l][cur.r] == 0){
                v[nx][ny][cur.l][cur.r] = v[cur.x][cur.y][cur.l][cur.r] + 1;
                q.add(new Node(nx,ny,cur.l,cur.r));
            }

            int tmpR = cur.r;
            int dirR = board[cur.x][cur.y];

            for(int i = 1 ; i <= 3 ; i++){
                if(tmpR <= 0)
                    break;

                dirR = changeR(dirR);
                tmpR--;
                nx = cur.x + dx[dirR];
                ny = cur.y + dy[dirR];

                if(!isOut(nx,ny) && v[nx][ny][cur.l][tmpR] == 0){
                    v[nx][ny][cur.l][tmpR] = v[cur.x][cur.y][cur.l][cur.r] + 1;
                    q.add(new Node(nx,ny,cur.l,tmpR));
                }
            }

            int tmpL = cur.l;
            int dirL = board[cur.x][cur.y];

            for(int i = 1 ; i <= 3 ; i++){
                if(tmpL <= 0)
                    break;

                dirL = changeL(dirL);
                tmpL--;
                nx = cur.x + dx[dirL];
                ny = cur.y + dy[dirL];

                if(!isOut(nx,ny) && v[nx][ny][tmpL][cur.r] == 0){
                    v[nx][ny][tmpL][cur.r] = v[cur.x][cur.y][cur.l][cur.r] + 1;
                    q.add(new Node(nx,ny,tmpL,cur.r));
                }
            }
        }

        System.out.println(result);
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;
        return false;
    }


    static void print(){
        for(int i =1  ; i <= n ; i++){
            for(int j =1 ; j <= m ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    static int changeR(int cur){
        return cur + 1 > 3 ? 0 : cur + 1;
    }

    static int changeL(int cur){
        return cur -1 < 0 ? 3 : cur - 1;
    }

    static class Node{
        int x;
        int y;
        int l;
        int r;

        Node(int x,int y,int l,int r){
            this.x = x;
            this.y = y;
            this.l = l;
            this.r = r;
        }
    }
}
