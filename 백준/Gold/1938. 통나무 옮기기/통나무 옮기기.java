import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static char[][] board;
    static int[][] train = new int[4][2];
    static int count = Integer.MAX_VALUE;
    static int idx = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        board = new char[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();

            for(int j= 1 ; j <= n ; j++) {
                board[i][j] = arr[j-1];

                if(board[i][j] == 'B') {
                    train[idx][0] = i;
                    train[idx][1] = j;
                    idx++;
                }
            }
        }

        bfs();

        if(count == Integer.MAX_VALUE)
            count = 0;

        System.out.println(count);


        br.close();
    }


    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static void bfs() {

        int[][][] v = new int[n+1][n+1][2];
        int type = 0;
        Queue<Node> q = new ArrayDeque<>();

        if(train[1][0] == train[2][0] && train[2][0] == train[3][0]) {
            // row 가로 열차
            type = 0;
        }

        else if(train[1][1] == train[2][1] && train[2][1] == train[3][1]) {
            // col 세로 열차
            type = 1;
        }

        v[train[2][0]][train[2][1]][type] = 1;
        q.add(new Node(train,type));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(find(cur.train)) {

                count = Math.min(count, v[cur.train[2][0]][cur.train[2][1]][cur.type] - 1);
                continue;
            }

            for(int i = 0 ; i< 4 ; i++) {
                boolean tmp = canMove(i,cur.train);

                if(!tmp)
                    continue;

                int nextCount = v[cur.train[2][0]][cur.train[2][1]][cur.type] + 1;

                int nx = cur.train[2][0] + dx[i];
                int ny = cur.train[2][1] + dy[i];

                if(v[nx][ny][cur.type] == 0 || v[nx][ny][cur.type] > nextCount) {
                    v[nx][ny][cur.type] = nextCount;

                    int[][] nTrain = new int[4][2];

                    for(int j = 1 ; j <= 3 ; j++) {
                        nTrain[j][0] = cur.train[j][0] + dx[i];
                        nTrain[j][1] = cur.train[j][1] + dy[i];
                    }

                    q.add(new Node(nTrain,cur.type));
                }
            }

            if(canTurn(cur.train[2][0], cur.train[2][1])) {


                int nextM = v[cur.train[2][0]][cur.train[2][1]][cur.type] + 1;
                int nextT = change(cur.type);

                if(v[cur.train[2][0]][cur.train[2][1]][nextT] == 0 ||
                        v[cur.train[2][0]][cur.train[2][1]][nextT] > nextM) {
                    v[cur.train[2][0]][cur.train[2][1]][nextT] = nextM;
                    if(cur.type == 0) {
                        // garo -> sero
                        int[][] nTrain = new int[4][2];

                        nTrain[1][0] = cur.train[2][0] - 1;
                        nTrain[1][1] = cur.train[2][1];
                        nTrain[2][0] = cur.train[2][0];
                        nTrain[2][1] = cur.train[2][1];
                        nTrain[3][0] = cur.train[2][0] + 1;
                        nTrain[3][1] = cur.train[2][1];

                        q.add(new Node(nTrain,nextT));
                    }else {
                        // sero -> garo
                        int[][] nTrain = new int[4][2];

                        nTrain[1][0] = cur.train[2][0];
                        nTrain[1][1] = cur.train[2][1] - 1;
                        nTrain[2][0] = cur.train[2][0];
                        nTrain[2][1] = cur.train[2][1];
                        nTrain[3][0] = cur.train[2][0];
                        nTrain[3][1] = cur.train[2][1] + 1;

                        q.add(new Node(nTrain,nextT));
                    }


                }

            }

        }

       // print(v);

    }

    static boolean find(int[][] train) {
        if(board[train[1][0]][train[1][1]] == 'E' && board[train[2][0]][train[2][1]] == 'E' && board[train[3][0]][train[3][1]] == 'E') {
            return true;
        }

        return false;
    }


    static int[] tx = {0,0,-1,1,1,1,-1,-1};
    static int[] ty = {1,-1,0,0,1,-1,1,-1};

    static boolean canTurn(int x,int y) {
        for(int i = 0 ; i < 8 ; i++) {
            int nx = x + tx[i];
            int ny = y + ty[i];

            if(isOut(nx,ny))
                return false;

            if(board[nx][ny] == '1')
                return false;
        }

        return true;
    }


    static int change(int type) {
        if(type == 1)
            return 0;

        return 1;
    }


    static boolean canMove(int dir,int[][] train) {
        for(int j = 1 ; j <= 3 ; j++) {
            int nx = train[j][0] + dx[dir];
            int ny = train[j][1] + dy[dir];

            if(isOut(nx,ny))
                return false;

            if(board[nx][ny] == '1')
                return false;
        }


        return true;
    }


    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y> n)
            return true;

        return false;
    }

    static void print(int[][][] v) {
        for(int i = 1; i <= n ; i++) {
            for(int j =1 ; j <= n ; j++) {
                System.out.print("("+v[i][j][0] +","+ v[i][j][1]+") ");
            }System.out.println();
        }System.out.println();
    }
}

class Node{
    int[][] train;
    int type;

    Node(int[][] train,int type){

        this.train = new int[4][2];

        for(int i = 1 ; i <= 3 ; i++) {
            this.train[i][0] = train[i][0];
            this.train[i][1] = train[i][1];
        }

        this.type = type;
    }
}


