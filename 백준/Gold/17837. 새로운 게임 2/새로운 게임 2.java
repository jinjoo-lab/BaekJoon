
import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[][] board;

    static Point[] chess;

    static ArrayList<Integer>[][] cBoard;
    static int dx[] = {0,0,0,-1,1};
    static int dy[] = {0,1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        cBoard = new ArrayList[n+1][n+1];

        for(int i = 1 ; i <= n ; i ++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                cBoard[i][j] = new ArrayList<>();
            }
        }

        chess = new Point[k+1];

        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            chess[i] = new Point(x,y,d);
            cBoard[x][y].add(i);
        }

        int result = 0;

        while(true) {
            result += 1;

            if(move())
                break;

            if(result > 1000)
                break;

        }

        if(result > 1000) {
            result = -1;
        }

        System.out.println(result);

        br.close();
    }

    static boolean isOut(int nx,int ny){
        if(nx < 1 || nx > n || ny < 1 || ny > n)
            return true;

        return false;
    }

    static void print(){
        for(int i =1 ; i <= n ;i++){
            for(int j =1 ; j <= n ;j++){
                System.out.print(cBoard[i][j].size()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean move(){
        for(int i = 1 ; i <= k ; i++){
            int nx = chess[i].x + dx[chess[i].d];
            int ny = chess[i].y + dy[chess[i].d];

            if(isOut(nx,ny) || board[nx][ny] == 2){
                int nd = changeDir(chess[i].d);

                int n2x = chess[i].x + dx[nd];
                int n2y = chess[i].y + dy[nd];

                if(isOut(n2x,n2y) || board[n2x][n2y] == 2){
                    chess[i].d = nd; // 방향만 바꾸기

                    if(isOver(chess[i].x,chess[i].y))
                        return true;
                }else{
                    chess[i].d = nd;

                    if(board[n2x][n2y] == 0){
                        if(moveW(i,chess[i].x,chess[i].y,n2x,n2y))
                            return true;
                    }else if(board[n2x][n2y] == 1){
                        if(moveR(i,chess[i].x,chess[i].y,n2x,n2y))
                            return true;
                    }
                }
            } else if(board[nx][ny] == 0){
                if(moveW(i,chess[i].x,chess[i].y,nx,ny))
                    return true;

            } else if(board[nx][ny] == 1) {
                if(moveR(i,chess[i].x,chess[i].y,nx,ny))
                    return true;
            }
        }
        return false;
    }

    static boolean isOver(int x,int y){
        if(cBoard[x][y].size() >=4)
            return true;

        return false;
    }

    static boolean moveR(int idx,int x,int y,int nx,int ny) {
        ArrayList<Integer> curList = new ArrayList<>();
        Stack<Integer> tmpList = new Stack<>();

        boolean start = false;

        for(int i = 0 , size = cBoard[x][y].size(); i < size ; i++){

            int num = cBoard[x][y].get(i);

            if(num == idx) {
                start = true;
            }

            if(start){
                chess[num].x = nx;
                chess[num].y = ny;
                tmpList.add(num);
            }else{
                curList.add(num);
            }
        }

        cBoard[x][y] = curList;

        while(!tmpList.isEmpty()){
            cBoard[nx][ny].add(tmpList.pop());
        }

        return cBoard[x][y].size() >= 4 || cBoard[nx][ny].size() >= 4;
    }

    static boolean moveW(int idx,int x,int y,int nx,int ny) {
        ArrayList<Integer> curList = new ArrayList<>();
        ArrayList<Integer> tmpList = new ArrayList<>();

        boolean start = false;

        for(int i = 0 , size = cBoard[x][y].size(); i < size ; i++){

            int num = cBoard[x][y].get(i);

            if(num == idx) {
                start = true;
            }

            if(start){
                chess[num].x = nx;
                chess[num].y = ny;
                tmpList.add(num);
            }else{
                curList.add(num);
            }
        }

        cBoard[x][y] = curList;
        cBoard[nx][ny].addAll(tmpList);

        return cBoard[x][y].size() >= 4 || cBoard[nx][ny].size() >= 4;
    }

    static int changeDir(int cur){
        if(cur == 1)
            return 2;
        if(cur == 2)
            return 1;
        if(cur == 3)
            return 4;

        return 3;
    }

    static class Point {
        int x;
        int y;
        int d;

        Point(int x,int y,int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
