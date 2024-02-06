import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0}; // -> , <- , ^ , v

    static char[][] board;

    static boolean[][] visit;

    static Queue<Node> q = new LinkedList<>();

    static Light[] light;

    static boolean find = false;
    static int lightNum = -1;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0)
                 break;

            board = new char[n+1][m+1];
            visit = new boolean[n+1][m+1];
            int max = -1;
            q = new ArrayDeque<>();


            for(int i=1 ; i <= n ; i++){
                String line = br.readLine();

                for(int j=1;j<=m;j++){
                    board[i][j] = line.charAt(j-1);

                    if(board[i][j] >= '0' && board[i][j] <= '9'){
                        max = Math.max(max,board[i][j] - '0');
                    }

                    if(board[i][j] == 'A'){
                        visit[i][j] = true;
                        q.add(new Node(i,j,0));
                    }
                }
            }

            light = new Light[max + 1];
            lightNum = max;

            for(int i = 0; i <= max ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int lightNum = Integer.parseInt(st.nextToken());
                char cur = st.nextToken().charAt(0);
                int rowTime = Integer.parseInt(st.nextToken());
                int calTime = Integer.parseInt(st.nextToken());

                int startTime =0;
                if(cur == '-')
                    startTime = rowTime;
                else
                    startTime = calTime;

                light[i] = new Light(rowTime,calTime,startTime,cur);
            }

            find = false;
            boolean keep = true;

            while(keep){
                keep = search();
            }

            if(find) {
                sb.append(result + "\n");
            }else{
                sb.append("impossible\n");
            }

            br.readLine();
        }

        System.out.print(sb);

        br.close();
    }

    static void timePlus(){

        for(int i=0; i <= lightNum ; i++){

            light[i].curTime -= 1;

            if(light[i].curTime == 0){
                if(light[i].curPos == '-'){
                    light[i].curTime = light[i].calTime;
                    light[i].curPos = '|';
                }else{
                    light[i].curTime = light[i].rowTime;
                    light[i].curPos = '-';
                }
            }

        }

    }

    static boolean search(){

        int size = q.size();

        while(size != 0){
            size--;
            Node cur = q.poll();


            if (board[cur.x][cur.y] == 'B') {
                result = cur.count;
                find = true;
                return false;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == '.')
                    continue;

                if((board[nx][ny] == '#' || board[nx][ny] == 'B')&& !visit[nx][ny]){
                    visit[nx][ny] = true;
                    q.add(new Node(nx,ny,cur.count + 1));
                }

                else if(board[nx][ny] >= '0' && board[nx][ny] <= '9'){

                    if(!visit[nx][ny]) {
                        int posNum = board[nx][ny] - '0';

                        if (light[posNum].curPos == '|') {

                            if (i >= 0 && i <= 1) {
                                q.add(new Node(cur.x, cur.y, cur.count + 1));
                            } else {
                                q.add(new Node(nx, ny, cur.count + 1));
                                visit[nx][ny] = true;
                            }

                        } else {
                            if (i >= 0 && i <= 1) {
                                q.add(new Node(nx, ny, cur.count + 1));
                                visit[nx][ny] = true;
                            } else {
                                q.add(new Node(cur.x, cur.y, cur.count + 1));
                            }
                        }
                    }
                }
            }
        }

        timePlus();

        if(q.size() == 0)
            return false;

        return true;
    }
}
class Node{
    int x;
    int y;

    int count;
    Node(int x,int y,int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
class Light{
    int rowTime;
    int calTime;
    int curTime;

    char curPos;

    Light(){};

    Light(int rowTime,int calTime,int curTime,char curPos){
        this.rowTime = rowTime;
        this.calTime = calTime;
        this.curTime = curTime;
        this.curPos = curPos;
    }
}