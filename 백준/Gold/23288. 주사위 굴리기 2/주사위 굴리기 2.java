import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0}; // R D L U

    static int[][] board;
    static int[][] score;

    static int result = 0;

    static int[] dice = {6,2,3,4,5,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        score = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        score();
        move();
        br.close();
    }

    static void score(){
        boolean[][] v = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(!v[i][j]){
                    v[i][j] = true;
                    bfs(i,j,v);
                }
            }
        }
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(score[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void bfs(int x,int y,boolean[][] v){
        Queue<Node> q = new LinkedList<>();
        Queue<Node> result = new LinkedList<>();
        q.add(new Node(x,y));
        result.add(new Node(x,y));

        int count = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(!v[nx][ny] && board[x][y] == board[nx][ny]){
                    v[nx][ny] = true;
                    q.add(new Node(nx,ny));
                    result.add(new Node(nx,ny));
                    count = count + 1;
                }
            }
        }

        int totalSum = count * board[x][y];
        while(!result.isEmpty()){
            Node tmp = result.poll();
            score[tmp.x][tmp.y] = totalSum;
        }
    }

    static void move(){
        Node start = new Node(1,1,0);
        Queue<Node> q= new LinkedList<>();
        q.add(start);

        for(int i=1;i<=k;i++){
            Node cur = q.poll();
            int dir = cur.dir;
            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if(nx < 1 || nx > n || ny < 1 || ny > m)
            {
                dir = OP(dir);
                nx = cur.x + dx[dir];
                ny = cur.y + dy[dir];
            }
            diceRoll(dir);
            result += score[nx][ny];

            int diceNum = dice[0];
            int boardNum = board[nx][ny];


            if(diceNum > boardNum){
                dir = RC(dir);
            }else if(diceNum < boardNum){
                dir = LC(dir);
            }


            q.add(new Node(nx,ny,dir));
        }

        System.out.println(result);
    }
    static void printDice(){
        for(int i=0;i<6;i++){
            System.out.print(dice[i]+" ");
        }
        System.out.println();
    }
    static void diceRoll(int dir){
        if(dir == 0){
            R();
        }else if(dir == 1){
            D();
        }else if(dir == 2){
            L();
        }else{
            U();
        }
    }

    // 바닥 앞 오 왼 뒤 위
    static void L(){
        int[] tmp = new int[6];
        tmp[0] = dice[3];
        tmp[1] = dice[1];
        tmp[2] = dice[0];
        tmp[3] = dice[5];
        tmp[4] = dice[4];
        tmp[5] = dice[2];

        dice = tmp;
    }

    static void D(){
        int[] tmp = new int[6];
        tmp[0] = dice[4];
        tmp[1] = dice[0];
        tmp[2] = dice[2];
        tmp[3] = dice[3];
        tmp[4] = dice[5];
        tmp[5] = dice[1];


        dice = tmp;
    }

    static void R(){
        int[] tmp = new int[6];
        tmp[0] = dice[2];
        tmp[1] = dice[1];
        tmp[2] = dice[5];
        tmp[3] = dice[0];
        tmp[4] = dice[4];
        tmp[5] = dice[3];


        dice = tmp;
    }

    static void U(){
        int[] tmp = new int[6];
        tmp[0] = dice[1];
        tmp[1] = dice[5];
        tmp[2] = dice[2];
        tmp[3] = dice[3];
        tmp[4] = dice[0];
        tmp[5] = dice[4];

        dice = tmp;
    }

    // R D L U
    static int RC(int cur){
        if(cur == 3)
            return 0;
        return cur + 1;
    }

    static int LC(int cur){
        if(cur == 0)
            return 3;
        return cur - 1;
    }

    static int OP(int cur){
        if(cur == 1)
            return 3;

        if(cur == 2)
            return 0;

        if(cur == 3)
            return 1;

        return 2;
    }
}
class Node{
    int x;
    int y;

    int dir;

    Node(int x,int y){
        this.x = x;
        this.y = y;
        this.dir = 0;
    }

    Node(int x,int y,int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
