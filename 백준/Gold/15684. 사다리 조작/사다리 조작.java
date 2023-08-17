import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[][] board;

    static int result = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[m+2][n+1];

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
            board[x][y+1] = 2;
        }

        tracking(0, 1);

        if(result >= 4)
            result = -1;

        System.out.println(result);
        br.close();
    }

    static void tracking(int num, int cur){

        if(result < num){
            return;
        }


        if(num <= 3){
            boolean tmp = play();

            if(tmp){
                result = Math.min(result , num);
            }
        }

        else{
            return;
        }
        for(int j=cur;j<=n;j++){
            for(int i=1;i<=m;i++){
                if(board[i][j] == 0){
                    int left = j - 1;
                    if(left >=1 && board[i][left] == 0){
                        board[i][j] = 2;
                        board[i][left] = 1;
                        tracking(num + 1, j);
                        board[i][j] = 0;
                        board[i][left] = 0;
                    }
                }
            }
        }
    }


    static boolean play(){

        for(int i=1;i<=n;i++){
            int row = 1;
            int col = i;

            while(row <= m){

                if(board[row][col] == 1){
                    col = col + 1;
                }

                else if(board[row][col] == 2){
                    col = col - 1;
                }

                row = row + 1;
            }

            if(col != i){
                    return false;
            }
        }
        return true;
    }
}
