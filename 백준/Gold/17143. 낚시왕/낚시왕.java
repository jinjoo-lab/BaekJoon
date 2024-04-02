
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int result = 0;
    static int n,m,k;
    static Point[][] board;

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new Point[n+1][m+1];

        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x =  Integer.parseInt(st.nextToken());
            int y =  Integer.parseInt(st.nextToken());
            int s =  Integer.parseInt(st.nextToken());
            int d =  Integer.parseInt(st.nextToken());
            int z =  Integer.parseInt(st.nextToken());

            board[x][y] = new Point(x,y,s,d,z);
        }

        for(int i = 1 ; i <= m ; i++){
            take(i);
            go();
        }

        System.out.println(result);
        br.close();
    }

    static Point move(Point cur){
        int rowNum = (n - 2) * 2 + 2;
        int calNum = (m - 2) * 2 + 2;

        int moveCount = 0;
        int nx = cur.x;
        int ny = cur.y;
        int nd = cur.d;

        if(cur.d == 1 || cur.d == 2){
            moveCount = cur.s % rowNum;
        }else{
            moveCount = cur.s % calNum;
        }

        int idx = 1;
        while(idx <= moveCount){
            nx = nx + dx[nd];
            ny = ny + dy[nd];

            if(isOut(nx,ny)){
                nx = nx - dx[nd];
                ny = ny - dy[nd];
                nd = changeD(nd);
                continue;
            }

            idx++;
        }

        return new Point(nx,ny,cur.s,nd,cur.z);
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static void go(){
        Point[][] tmpBoard = new Point[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(board[i][j] != null){
                    Point tmp = move(board[i][j]);

                    if(tmpBoard[tmp.x][tmp.y] == null || tmpBoard[tmp.x][tmp.y].z < tmp.z){
                        tmpBoard[tmp.x][tmp.y] = tmp;
                    }
                }
            }
        }

        copy(tmpBoard);
    }

    static void copy(Point[][] tmp){
        for(int i = 1 ; i <=n ; i++){
            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = tmp[i][j];
            }
        }
    }

    static void take(int idx){

        int tIdx = 0;

        for(int i = 1 ; i <= n ; i++){
            if(board[i][idx] != null){
                tIdx = i;
                break;
            }
        }


        if(tIdx == 0)
            return;

        result += board[tIdx][idx].z;
        board[tIdx][idx] = null;
    }

    static int changeD(int cur){
        if(cur == 1)
            return 2;

        else if(cur == 2)
            return 1;

        else if(cur == 3)
            return 4;

        else
            return 3;
    }

    static class Point{

        int x;
        int y;
        int s;
        int d;
        int z;

        Point(int x,int y,int s,int d,int z){
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
