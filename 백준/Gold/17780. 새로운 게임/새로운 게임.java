import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    static Node[] cur;

    static ArrayList<Integer>[][] chess;

    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cur = new Node[m+1];
        board = new int[n+1][n+1];

        chess = new ArrayList[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                chess[i][j] = new ArrayList<>();
            }
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            cur[i] = new Node(x,y,dir);
            chess[x][y].add(i);
        }

        boolean tmp = true;
        for(int i=1;i<=1000;i++){
            tmp = turn();

            if(!tmp)
            {
                System.out.println(i);
                break;
            }
        }

        if(tmp)
            System.out.println(-1);

        br.close();
    }

    static int changeDir(int cur){
        if(cur == 1)
            return 2;
        else if(cur == 2)
            return 1;
        else if(cur == 3)
            return 4;
        else
            return 3;
    }

    static boolean turn(){
        boolean re = true;

        for(int i=1;i<=m;i++){
            int x = cur[i].x;
            int y = cur[i].y;
            int d = cur[i].dir;

            if(chess[x][y].get(0) != i)
                continue;

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 1 || nx > n || ny < 1 || ny > n){
                re = moveB(x,y,d,i);
            }

            else if(board[nx][ny] == 2){
                re = moveB(x,y,d,i);
            }

            else if(board[nx][ny] == 0){
                re =  moveW(x,y,nx,ny,d,i);
            }

            else if(board[nx][ny] == 1){
                re = moveR(x,y,nx,ny,d,i);
            }

            if(!re)
                return false;
        }


        return true;
    }

    static boolean moveW(int x,int y,int nx,int ny,int dir,int num){
        ArrayList<Integer> tmp = new ArrayList<>();

        for(int next : chess[x][y]){
            chess[nx][ny].add(next);
            cur[next] = new Node(nx,ny,cur[next].dir);
        }

        if(chess[nx][ny].size() >= 4)
            return false;

        chess[x][y] = tmp;
        cur[num] = new Node(nx,ny,dir);

        return true;
    }

    static boolean moveR(int x,int y,int nx,int ny,int dir,int num){
        ArrayList<Integer> tmp = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        for(int cur : chess[x][y]){
            stack.push(cur);
        }

        while(!stack.isEmpty()){
            int next = stack.pop();

            chess[nx][ny].add(next);
            cur[next] = new Node(nx,ny,cur[next].dir);
        }

        if(chess[nx][ny].size() >= 4)
            return false;

        chess[x][y] = tmp;
        cur[num] = new Node(nx,ny,dir);
        return true;
    }

    static boolean moveB(int x,int y,int dir,int num){

        int nd = changeDir(dir);

        int nx = x + dx[nd];
        int ny = y + dy[nd];

        if(nx < 1 || nx > n || ny < 1 || ny > n){
            cur[num] = new Node(x,y,nd);

            if(chess[x][y].size() >= 4)
                return false;
        }

        else if(board[nx][ny] == 2){
            cur[num] = new Node(x,y,nd);

            if(chess[x][y].size() >= 4)
                return false;
        }

        else if(board[nx][ny] == 0){
            return moveW(x,y,nx,ny,nd,num);
        }

        else if(board[nx][ny] == 1){
            return moveR(x,y,nx,ny,nd,num);
        }

        return true;
    }


    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(chess[i][j].size()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Node{
    int x;
    int y;
    int dir;

    Node(int x,int y,int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}