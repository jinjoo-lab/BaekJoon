import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0}; // L R U D

    static String result2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        board = new char[n+1][m+1];
        for(int i=1;i<=n;i++){
            String line = br.readLine();
            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] == 'B'){
                    bx = i;
                    by = j;
                }

                else if(board[i][j] == 'R'){
                    rx = i;
                    ry = j;
                }
            }
        }

        int result = bfs(rx,ry,bx,by);

        if(result == -1){
            System.out.println(result);
        }

        else{
            System.out.println(result);
            System.out.println(result2);
        }
        br.close();
    }

    static int bfs(int rx,int ry,int bx,int by){
        int[][][][] v = new int[n+1][m+1][n+1][m+1];
        v[rx][ry][bx][by] = 1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(rx,ry,bx,by,false,false));

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.rg || cur.bg){
                if(cur.rg && !cur.bg) {
                    StringBuilder sb = new StringBuilder();
                    for (char tmpChar : cur.move) {
                        sb.append(tmpChar);
                    }

                    result2 = sb.toString();
                    return v[cur.rx][cur.ry][cur.bx][cur.by] - 1;
                }

                continue;
            }

            if(v[cur.rx][cur.ry][cur.bx][cur.by] >= 11){
                continue;
            }

            // L R U D
            for(int i=0;i<4;i++){
                Point tmp;
                char d;

                if(i == 0){
                    if(cur.ry < cur.by){
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,true,0);
                    }

                    else{
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,false,0);
                    }

                    d = 'L';
                }

                else if(i == 1){
                    if(cur.ry > cur.by){
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,true,1);
                    }

                    else{
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,false,1);
                    }

                    d = 'R';
                }

                else if(i == 2){
                    if(cur.rx < cur.bx){
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,true,2);
                    }

                    else{
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,false,2);
                    }

                    d = 'U';
                }

                else{
                    if(cur.rx > cur.bx){
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,true,3);
                    }

                    else{
                        tmp = move(cur.rx,cur.ry,cur.bx,cur.by,false,3);
                    }

                    d = 'D';
                }

                if(v[tmp.rx][tmp.ry][tmp.bx][tmp.by] == 0){
                    v[tmp.rx][tmp.ry][tmp.bx][tmp.by] = v[cur.rx][cur.ry][cur.bx][cur.by] + 1;
                    tmp.move.addAll(cur.move);
                    tmp.move.add(d);
                    q.add(tmp);
                }
            }
        }

        return -1;
    }

    static Point move(int rx,int ry,int bx,int by,boolean who,int dir){
        int r2x = rx;
        int r2y = ry;
        int b2x = bx;
        int b2y = by;
        boolean r2g = false;
        boolean b2g = false;

        if(who){
            while(true){
                r2x = r2x + dx[dir];
                r2y = r2y + dy[dir];

                if(board[r2x][r2y] == 'O'){
                    r2g = true;
                    break;
                }

                else if(board[r2x][r2y] == '#'){
                    r2x = r2x - dx[dir];
                    r2y = r2y - dy[dir];
                    break;
                }

            }

            while(true){
                b2x = b2x + dx[dir];
                b2y = b2y + dy[dir];

                if(board[b2x][b2y] == 'O'){
                    b2g = true;
                    break;
                }

                else if(board[b2x][b2y] == '#' || (b2x == r2x && b2y == r2y)){
                    b2x = b2x - dx[dir];
                    b2y = b2y - dy[dir];
                    break;
                }

            }
        }

        else{
            while(true){
                b2x = b2x + dx[dir];
                b2y = b2y + dy[dir];

                if(board[b2x][b2y] == 'O'){
                    b2g = true;
                    break;
                }

                else if(board[b2x][b2y] == '#'){
                    b2x = b2x - dx[dir];
                    b2y = b2y - dy[dir];
                    break;
                }
            }

            while(true){
                r2x = r2x + dx[dir];
                r2y = r2y + dy[dir];

                if(board[r2x][r2y] == 'O'){
                    r2g = true;
                    break;
                }

                else if(board[r2x][r2y] == '#'||(b2x == r2x && b2y == r2y)){
                    r2x = r2x - dx[dir];
                    r2y = r2y - dy[dir];
                    break;
                }
            }
        }

        return new Point(r2x,r2y,b2x,b2y,r2g,b2g);
    }

    // L R U D
}
class Point{
    int rx;
    int ry;
    int bx;
    int by;

    boolean rg;
    boolean bg;
    ArrayList<Character> move;

    Point(int rx,int ry,int bx,int by,boolean rg,boolean bg){
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.rg = rg;
        this.bg = bg;
        move = new ArrayList<>();
    }
}