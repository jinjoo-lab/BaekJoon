import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=k;i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int ki = Integer.parseInt(st.nextToken());

            int tmp = x;
            while(tmp <= n){
                turn(tmp,d,ki);
                tmp += x;
            }

            delete();
        }

        int result = sum();
        System.out.println(result);

        br.close();
    }

    static int sum(){
        int sum = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                sum += board[i][j];
            }
        }

        return sum;
    }

    static void delete(){
        boolean keep = false;
        boolean[][] v = new boolean[n+1][m+1];
        int sum = 0;
        int num = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(board[i][j] == 0)
                    continue;

                for(int a = 0;a<4;a++){
                    int nx = i + dx[a];
                    int ny = j + dy[a];

                    if(nx < 1 || nx > n)
                        continue;

                    if(ny < 1)
                        ny = m;

                    if(ny > m)
                        ny = 1;

                    if(board[i][j] == board[nx][ny]){
                        v[i][j] = true;
                        v[nx][ny] = true;
                        keep = true;
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(v[i][j]) {
                    board[i][j] = 0;
                }

                if(board[i][j] != 0){
                    sum += board[i][j];
                    num +=1;
                }
            }
        }

        if(sum == 0 || num ==0)
            return;

        if(!keep){
            double tmp2 = (double)sum / num;
            int tmp3 = (int)tmp2;
            if(tmp2 > (double)tmp3){
                plus(tmp3);
            }

            else{
                plusTwo(tmp3);
            }
        }


    }

    static void plusTwo(int num){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(board[i][j] == 0 || board[i][j] == num)
                    continue;

                if(board[i][j] > num){
                    board[i][j] -=1;
                }

                else if(board[i][j] < num){
                    board[i][j] +=1;
                }
            }
        }
    }

    static void plus(int num){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(board[i][j] == 0)
                    continue;

                if(board[i][j] > num){
                    board[i][j] -=1;
                }

                else if(board[i][j] <= num){
                    board[i][j] +=1;
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

    static void turn(int idx,int dir,int how){

        int[] tmp = new int[m+1];

        if(dir == 0){
            for(int i=1;i<=m;i++){

                int tmpIdx = i + how;

                if(tmpIdx <= m){
                    tmp[tmpIdx] = board[idx][i];
                }

                else{
                    tmp[tmpIdx - m] = board[idx][i];
                }
            }
        }

        else{
            for(int i=1;i<=m;i++){

                int tmpIdx = i - how;

                if(tmpIdx >=1){
                    tmp[tmpIdx] = board[idx][i];
                }

                else{
                    tmp[tmpIdx + m] = board[idx][i];
                }

            }

        }

        board[idx] = tmp;
    }

}

/*
* System.out.println();
            for(int i=1;i<=m;i++){
                System.out.print(tmp[i]+" ");
            }
            System.out.println();*/
