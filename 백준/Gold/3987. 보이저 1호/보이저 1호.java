import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dx = {-1,0,1,0};

    static int[] dy = {0,1,0,-1};

    static int sx = 0;
    static int sy = 0;

    static int[][] board;

    static boolean[][][] v;

    static int result = 1;

    static int resultDir = 0;

    static boolean infinite = false;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=m;j++){
                char tmp = line.charAt(j-1);

                if(tmp == '/'){
                    board[i][j] = 2;
                }

                else if(tmp == '\\'){
                    board[i][j] = 3;
                }

                else if(tmp == 'C'){
                    board[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        for(int i=0;i<4;i++){
            v = new boolean[n+1][m+1][4];
            v[sx][sy][i] = true;
            move(i,i,sx,sy,1);

            if(infinite)
                break;
        }

        if(infinite){
            System.out.println(change(resultDir));
            System.out.println("Voyager");
        }else{
            System.out.println(change(resultDir));
            System.out.println(result);
        }

        br.close();
    }
    static char change(int num){
        if(num == 0){
            return 'U';
        }else if(num == 1){
            return 'R';
        }else if(num == 2){
            return 'D';
        }else return 'L';
    }

    static void move(int sd,int d,int x,int y,int num){

        int nx = x + dx[d];
        int ny = y + dy[d];

        if(nx < 1 || nx > n || ny < 1 || ny > m || board[nx][ny] == -1) {
            if(result < num){

                if(infinite){
                    return;
                }

                result = num;
                resultDir = sd;
            }
            return;
        }

        if(v[nx][ny][d] ){
            infinite = true;
            resultDir = sd;
            return;
        }

        v[nx][ny][d] = true;

        if(board[nx][ny] == 0){
            move(sd,d,nx,ny,num+1);
        }else if(board[nx][ny] == 2){
            d = slash1(d);
            move(sd,d,nx,ny,num+1);
        }else if(board[nx][ny] == 3){
            d = slash2(d);
            move(sd,d,nx,ny,num+1);
        }
    }

    // U - R - D - L
    static int slash1(int dir){
        if(dir == 0){
            return 1;
        }else if(dir == 1){
            return 0;
        }else if(dir == 2){
            return 3;
        }else{
            return 2;
        }
    }
    // U - R - D - L
    static int slash2(int dir){
        if(dir == 0){
            return 3;
        }else if(dir == 1){
            return 2;
        }else if(dir == 2){
            return 1;
        }else{
            return 0;
        }
    }
}
