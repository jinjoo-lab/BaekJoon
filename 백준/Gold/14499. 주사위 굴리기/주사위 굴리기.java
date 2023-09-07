import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int x = 0;

    static int y = 0;

    static int k = 0;

    static int[][] board;

    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=k;i++){
            int num = Integer.parseInt(st.nextToken());
            move(num);
        }


        br.close();
    }

    static void swap(int n1,int n2){
        int tmp = dice[n1];
        dice[n1] = dice[n2];
        dice[n2] = tmp;
    }

    static void move(int dir){
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= n || ny >= m)
            return;

        x = nx;
        y = ny;


        if(dir == 1){
            R(x,y);
        }

        else if(dir == 2){
            L(x,y);
        }

        else if(dir == 3){
            U(x,y);
        }

        else{
            D(x,y);
        }

        System.out.println(dice[6]);

    }

    // 1 , 2 , 3 , 4 , 5, 6
    // 바닥 , 오 , 앞 , 뒤 ,왼 , 위

    static void R(int x,int y){

        if(board[x][y] == 0){
            board[x][y] = dice[2];
        }
        else{
            dice[2] = board[x][y];
            board[x][y] = 0;
        }

        int[] tmp = new int[7];
        tmp[5] = dice[1];
        tmp[2] = dice[6];
        tmp[1] = dice[2];
        tmp[6] = dice[5];
        tmp[3] = dice[3];
        tmp[4] = dice[4];

        dice = tmp;
    }

    static void L(int x,int y){
        if(board[x][y] == 0){
            board[x][y] = dice[5];
        }
        else{
            dice[5] = board[x][y];
            board[x][y] = 0;
        }

        int[] tmp = new int[7];
        tmp[5] = dice[6];
        tmp[2] = dice[1];
        tmp[1] = dice[5];
        tmp[6] = dice[2];
        tmp[3] = dice[3];
        tmp[4] = dice[4];

        dice = tmp;
    }

    static void U(int x,int y){
        if(board[x][y] == 0){
            board[x][y] = dice[3];
        }
        else{
            dice[3] = board[x][y];
            board[x][y] = 0;
        }

        int[] tmp = new int[7];
        tmp[3] = dice[6];
        tmp[4] = dice[1];
        tmp[2] = dice[2];
        tmp[5] = dice[5];
        tmp[1] = dice[3];
        tmp[6] = dice[4];

        dice = tmp;
    }

    static void D(int x,int y){
        if(board[x][y] == 0){
            board[x][y] = dice[4];
        }
        else{
            dice[4] = board[x][y];
            board[x][y] = 0;
        }

        int[] tmp = new int[7];
        tmp[4] = dice[6];
        tmp[3] = dice[1];
        tmp[2] = dice[2];
        tmp[5] = dice[5];
        tmp[1] = dice[4];
        tmp[6] = dice[3];

        dice = tmp;
    }
}

