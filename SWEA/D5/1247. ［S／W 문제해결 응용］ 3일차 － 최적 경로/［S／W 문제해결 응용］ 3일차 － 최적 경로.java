import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int t = 0;
    static int n = 0;

    static int sx,sy,lx,ly;

    static int[][] board;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a<=t ;a++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n][2];

            st = new StringTokenizer(br.readLine(), " ");
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            lx = Integer.parseInt(st.nextToken());
            ly = Integer.parseInt(st.nextToken());

            for(int i=0; i<n ;i++){
                board[i][0] = Integer.parseInt(st.nextToken());
                board[i][1] = Integer.parseInt(st.nextToken());
            }

            result = Integer.MAX_VALUE;
            go(0,sx,sy,0,0);
            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void go(int cur,int x,int y,int v,int tmpResult){

        if(cur == n){
            int finalDis = dis(x,y,lx,ly);
            result = Math.min(result, tmpResult + finalDis);
            return;
        }

        for(int i=0; i < n ;i++){
            if((v & 1 << i) != 0)
                continue;

            int tmpDis = dis(x,y,board[i][0],board[i][1]);

            if(result < tmpResult + tmpDis)
                continue;

            go(cur + 1 , board[i][0] , board[i][1] , v | 1 << i , tmpResult + tmpDis);
        }
    }

    static int dis(int x,int y,int nx,int ny){
        return Math.abs(x - nx) + Math.abs(y - ny);
    }
}
