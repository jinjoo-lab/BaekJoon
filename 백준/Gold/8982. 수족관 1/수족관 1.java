import java.util.*;
import java.io.*;

public class Main {

    static int n,m,ex,ey;

    static int[][] board = new int[40001][2];

    // 0 -> 용량

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i < n / 2 ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());

            for(int j = sx ; j < lx ; j++) {
                board[j][0] = ly;
            }
        }
        st = new StringTokenizer(br.readLine()," ");
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        Node[] input = new Node[m];

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());

            input[i-1] = new Node(sx,sy,lx,ly);
        }

        Arrays.sort(input,(x,y) -> {
            return x.sx - y.sx;
        });

        for(int i = 0 ; i < m ; i ++) {
            cal(input[i].sx,input[i].sy,input[i].lx,input[i].ly);
        }


        int result = getResult();

        System.out.println(result);

        br.close();
    }

    static void cal(int sx,int sy,int lx,int ly) {

        int tmpH = sy;

        for(int j = sx - 1; j >= 0 ; j--) {
            tmpH = Math.min(board[j][0],tmpH);
            board[j][1] = Math.max(board[j][1],tmpH);
        }

        tmpH = sy;

        for(int j = sx ; j <= lx ; j++) {
            tmpH = Math.min(board[j][0],tmpH);
            board[j][1] = Math.max(board[j][1],tmpH);
        }

        tmpH = sy;

        for(int j = lx + 1; j < ex ; j++) {
            tmpH = Math.min(board[j][0],tmpH);
            board[j][1] = Math.max(board[j][1],tmpH);
        }
    }

    static int getResult() {
        int result = 0;

        for(int i = 0 ; i < ex ; i++) {
            result += (board[i][0] - board[i][1]);
        }

        return result;
    }

    static void print() {
        for(int i = 0 ; i <= ex ; i++) {
            System.out.print(board[i][0]+" ");
        }
        System.out.println();
    }

    static void printTwo() {
        for(int i = 0 ; i < ex ; i++) {
            System.out.print((board[i][0]+" "+board[i][1])+", ");
        }
        System.out.println();
    }

    static class Node{
        int sx;
        int sy;
        int lx;
        int ly;

        Node(int sx,int sy,int lx,int ly) {
            this.sx = sx;
            this.sy = sy;
            this.lx = lx;
            this.ly = ly;
        }
    }
}
