import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int sx,sy;

    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int result = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] == 'T'){
                    sx = i;
                    sy = j;
                }
            }
        }

        search();
        System.out.println(result);

        bf.close();
    }

    static void search(){
        int[][] v = new int[n+1][m+1];
        boolean[][] visit = new boolean[n+1][m+1];


        Queue<Node> q = new LinkedList<>();
        visit[sx][sy] = true;
        q.add(new Node(sx,sy));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for(int i=0;i < 4 ;i ++){
                int idx = 1;
                int tmp = 0;

                while(true){
                    int nx = cur.x + (idx * dx[i]);
                    int ny = cur.y + (idx * dy[i]);

                    if(isOut(nx,ny))
                        break;

                    if(board[nx][ny] == 'H' || board[nx][ny] =='T')
                        break;

                    if(board[nx][ny] == 'E'){


                        if(result == -1){
                            result =  v[cur.x][cur.y] + tmp;
                        }

                        else{
                            result = Math.min(result,v[cur.x][cur.y] + tmp);
                        }
                    }

                    if(board[nx][ny] == 'R'){
                        nx -= dx[i];
                        ny -= dy[i];

                        if(!visit[nx][ny] || v[nx][ny] > v[cur.x][cur.y] + tmp){
                            visit[nx][ny] = true;
                            v[nx][ny] = v[cur.x][cur.y] + tmp;
                            q.add(new Node(nx,ny));
                        }
                        break;
                    }

                    tmp += board[nx][ny] - '0';
                    idx++;
                }
            }
        }

    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }
}
class Node{
    int x;
    int y;

    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}



