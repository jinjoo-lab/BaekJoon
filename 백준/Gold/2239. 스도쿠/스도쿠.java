import java.io.*;
import java.util.*;

public class Main {
    static boolean keep = true;
    static int[][] board = new int[10][10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1;i<=9;i++){
            String line = br.readLine();
            for(int j=1;j<=9;j++){
                board[i][j] = line.charAt(j-1) - '0';
            }
        }

        travel(1,1);
        br.close();
    }

    static void travel(int x,int y){

        if(y > 9){
            x = x + 1;
            y = 1;

            if(x > 9 && keep){
                print();
                keep = false;
                return;
            }
        }

        if(board[x][y] != 0)
        {
            travel(x,y+1);
        }

        else {
            boolean[] v = new boolean[10];

            for (int i = 1; i <= 9; i++) {
                v[board[x][i]] = true;
                v[board[i][y]] = true;
            }

            int r = (x - 1) / 3 * 3 + 1;
            int c = (y - 1) / 3 * 3 + 1;

            for (int i = r; i <= r + 2; i++) {
                for (int j = c; j <= c + 2; j++) {
                    v[board[i][j]] = true;
                }
            }

            for (int i = 1; i <= 9; i++) {
                if (!v[i] && keep) {
                    v[i] = true;
                    board[x][y] = i;
                    travel(x, y + 1);
                    board[x][y] = 0;
                    v[i] = false;
                }
            }
        }
    }



    static void print(){
        for(int i=1;i<=9;i++){
            int sum = 0;
            for(int j=1;j<=9;j++){
                sum += board[i][j];
            }

            if(sum != 45)
                return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=9;i++){
            for(int j=1;j<=9;j++){sb.append(board[i][j]);}
            sb.append("\n");
        }
        System.out.println(sb);
    }
}