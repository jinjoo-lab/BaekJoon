
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;

    static int[] dx = {0,1,1};
    static int[] dy = {1,0,1};

    static int[][] board;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1;  i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1,2,0);
        System.out.println(count);

        br.close();
    }

    static void dfs(int x,int y,int dir){

        if(x == n && y == n){
            count++;
            return;
        }

        int nx = 0;
        int ny = 0;

        if(dir == 0){
            // g -> g
            // g -> c
            boolean can = true;

            for(int i = 0 ; i < 3 ; i++){

                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) {
                    can = false;
                    continue;
                }

                if(board[nx][ny] == 1) {
                    can = false;
                    continue;
                }

                if(i == 0){
                    dfs(nx,ny,0);
                }else if(i == 2 && can){
                    dfs(nx,ny,2);
                }
            }
        }else if(dir == 1){
            // s -> s
            // s -> c
            boolean can = true;

            for(int i = 0 ; i < 3 ; i++){

                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) {
                    can = false;
                    continue;
                }

                if(board[nx][ny] == 1) {
                    can = false;
                    continue;
                }

                if(i == 1){
                    dfs(nx,ny,1);
                }else if(i == 2 && can){
                    dfs(nx,ny,2);
                }
            }
        }else{
            // s -> s
            // s -> c
            boolean can = true;

            for(int i = 0 ; i < 3 ; i++){

                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) {
                    can = false;
                    continue;
                }

                if(board[nx][ny] == 1) {
                    can = false;
                    continue;
                }

                if(i == 0 || i == 1){
                    dfs(nx,ny,i);
                }else if(i == 2 && can){
                    dfs(nx,ny,2);
                }
            }
        }
    }

}