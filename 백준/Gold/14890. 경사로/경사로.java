import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];
        boolean[][] visit = new boolean[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for(int i=1;i<=n;i++){
            if(sero(i,visit)){
                count = count + 1;
            }
        }

        visit = new boolean[n+1][n+1];
        for(int i=1;i<=n;i++){
            if(garo(i,visit)){
                count = count + 1;
            }
        }

        System.out.println(count);
        br.close();
    }

    static boolean sero(int col,boolean[][] visit)
    {
        boolean[] reset = new boolean[n+1];
        int idx = 1;
        boolean result = false;
        while(idx <= n){

            if(idx == n) {
                result = true;
                break;
            }

            int minus = Math.abs(board[idx][col] - board[idx+1][col]);

            if(minus == 0) {
                idx = idx + 1;
            }

            else if(minus == 1){

                if(board[idx][col] > board[idx+1][col]){
                    int des = idx + m;
                    int num = board[idx+1][col];

                    if(des > n)
                        break;

                    boolean keep = true;

                    for(int i = idx+1;i<=des;i++){

                        if(visit[i][col]) {
                            keep = false;
                            break;
                        }

                        if(board[i][col] == num) {
                            visit[i][col] = true;
                            reset[i] = true;
                        }

                        else{
                            keep = false;
                            break;
                        }
                    }

                    if(!keep) {
                        break;
                    }
                }

                else{
                    int des = idx + 1 - m;
                    int num = board[idx][col];

                    if(des < 1)
                        break;

                    boolean keep = true;

                    for(int i = idx;i>= des;i--){

                        if(visit[i][col]) {
                            keep = false;
                            break;
                        }

                        if(board[i][col] == num) {
                            visit[i][col] = true;
                            reset[i] = true;
                        }

                        else{
                            keep = false;
                            break;
                        }
                    }

                    if(!keep) {
                        break;
                    }
                }

                idx = idx + 1;
            }

            else{
                result = false;
                break;
            }
        }

        if(!result){
            for(int i=1;i<=n;i++){
                if(reset[i])
                {
                    visit[i][col] = false;
                }
            }
        }

        return result;
    }

    static boolean garo(int row,boolean[][] visit){
        boolean[] reset = new boolean[n+1];
        int idx = 1;
        boolean result = false;
        while(idx <= n){

            if(idx == n) {
                result = true;
                break;
            }

            int minus= Math.abs(board[row][idx] - board[row][idx+1]);

            if(minus == 0){
                idx = idx + 1;
            }

            else if(minus == 1){

                if(board[row][idx] > board[row][idx+1]){
                    int des = idx + m;
                    int num = board[row][idx+1];

                    if(des > n)
                        break;

                    boolean keep = true;

                    for(int i = idx+1;i<=des;i++){

                        if(visit[row][i]) {
                            keep = false;
                            break;
                        }

                        if(board[row][i] == num) {
                            visit[row][i] = true;
                            reset[i] = true;
                        }

                        else{
                            keep = false;
                            break;
                        }
                    }

                    if(!keep) {
                        break;
                    }
                }

                else{
                    int des = idx + 1 - m;
                    int num = board[row][idx];

                    if(des < 1)
                        break;

                    boolean keep = true;

                    for(int i = idx;i>= des;i--){

                        if(visit[row][i]) {
                            keep = false;
                            break;
                        }

                        if(board[row][i] == num) {
                            visit[row][i] = true;
                            reset[i] = true;
                        }

                        else{
                            keep = false;
                            break;
                        }
                    }

                    if(!keep) {
                        break;
                    }
                }

                idx = idx + 1;
            }

            else{
                result = false;
                break;
            }

        }

        if(!result){
            for(int i=1;i<=n;i++){
                if(reset[i])
                {
                    visit[row][i] = false;
                }
            }
        }

        return result;
    }

    static void print(boolean[][] visit){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++) {
                if (visit[i][j])
                    System.out.print(1 + " ");

                else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        } System.out.println();
    }
}