import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){

            String line = br.readLine();

            for(int j=1;j<=n;j++){
                board[i][j] = line.charAt(j-1) - '0';
            }
        }

        go(n,1,1);

        System.out.println(sb);
        br.close();
    }

    static void go(int size ,int x,int y){

        int first = board[x][y];
        boolean check = true;

        loop : for(int i = x; i< x+ size; i++){
            for(int j = y; j < y + size; j++){
                if(board[i][j] != first){
                    check = false;
                    break loop;
                }
            }
        }

        if(check){
            sb.append(first);
            return;
        }

        sb.append("(");
        go(size/2,x,y);
        go(size/2,x,y+size/2);
        go(size/2,x+size/2,y);
        go(size/2,x+size/2,y+size/2);
        sb.append(")");
    }
}
