import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=k;i++){
            int what = Integer.parseInt(st.nextToken());

            if(what == 1){
                first();
            }

            else if(what == 2){
                second();
            }

            else if(what == 3){
                three();
            }

            else if(what == 4){
                four();
            }

            else if(what == 5){
                five();
            }

            else{
                six();
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sb.append(board[i][j]+" ");
            }sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void first(){
        int[][] tmp = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmp[i][j] = board[n-1-i][j];
            }
        }

        board = tmp;
    }

    static void second(){
        int[][] tmp = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmp[i][j] = board[i][m-1-j];
            }
        }

        board = tmp;
    }

    static void three(){
        int[][] tmp = new int[m][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmp[j][n-1-i] = board[i][j];
            }
        }

        board = tmp;
        int tmp2 = n;
        n = m;
        m = tmp2;
    }

    static void four(){
        int[][] tmp = new int[m][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmp[m-1-j][i] = board[i][j];
            }
        }

        board = tmp;
        int tmp2 = n;
        n = m;
        m = tmp2;
    }

    static void five(){
        int[][] tmp = new int[n][m];
        int rh = n/2;
        int ch = m/2;

        for(int i=0;i<rh;i++){
            for(int j=0;j<ch;j++){
                tmp[i][j] = board[i+rh][j];
            }
        }

        for(int i=0;i<rh;i++){
            for(int j=ch;j<m;j++){
                tmp[i][j] = board[i][j-ch];
            }
        }

        for(int i=rh;i<n;i++){
            for(int j=ch;j<m;j++){
                tmp[i][j] = board[i-rh][j];
            }
        }

        for(int i=rh;i<n;i++){
            for(int j=0;j<ch;j++){
                tmp[i][j] = board[i][j + ch];
            }
        }

        board = tmp;
    }

    static void six(){
        int[][] tmp = new int[n][m];
        int rh = n/2;
        int ch = m/2;

        for(int i=0;i<rh;i++){
            for(int j=0;j<ch;j++){
                tmp[i][j] = board[i][j+ch];
            }
        }

        for(int i=0;i<rh;i++){
            for(int j=ch;j<m;j++){
                tmp[i][j] = board[i+rh][j];
            }
        }

        for(int i=rh;i<n;i++){
            for(int j=ch;j<m;j++){
                tmp[i][j] = board[i][j - ch];
            }
        }

        for(int i=rh;i<n;i++){
            for(int j=0;j<ch;j++){
                tmp[i][j] = board[i-rh][j];
            }
        }

        board = tmp;
    }


    static void print(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
