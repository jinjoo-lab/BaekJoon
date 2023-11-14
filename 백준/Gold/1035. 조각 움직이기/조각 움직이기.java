import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static ArrayList<Node> num = new ArrayList<>();

    static int result = -1;

    static int count = 1;

    static int[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[6][6];

        for(int i=1;i<=5;i++){
            String line = br.readLine();

            for(int j=1;j<=5;j++){

                if(line.charAt(j-1) == '*'){
                    board[i][j] = count;
                    num.add(new Node(i,j));
                    count += 1;
                }
            }
        }
        count -= 1;


        for(int k=1;k<=count;k++) {
            Node cur = num.get(k-1);

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    int tmp = 0;
                    if(board[i][j] == k || board[i][j] == 0){
                        tmp += dis(cur.x,cur.y,i,j);

                        board[cur.x][cur.y] = 0;
                        board[i][j] = k;
                        tmp += travel(k);
                        board[i][j] = 0;
                        board[cur.x][cur.y] = k;


                        if(result == -1 || result > tmp){
                                result = tmp;
                        }
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static void force(int stand,int[][] tmp){
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                if(tmp[i][j] == stand){
                    boolean[][] v = new boolean[6][6];
                    Queue<Node> q = new LinkedList<>();
                    v[i][j] = true;
                    q.add(new Node(i,j));

                    while(!q.isEmpty()){

                        Node cur = q.poll();

                        for(int k = 0; k < 4; k ++){
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];

                            if(nx < 1 || nx > 5 || ny < 1 || ny > 5)
                                continue;

                            if(!v[nx][ny] && tmp[nx][ny] != 0){
                                v[nx][ny] = true;
                                tmp[nx][ny] = stand;
                                q.add(new Node(nx,ny));
                            }
                        }
                    }
                }
            }
        }
    }

    static int travel(int stand){
        int[][] tmp = new int[6][6];

        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                tmp[i][j] = board[i][j];
            }
        }

        force(stand,tmp);

        int count = 0;

        for(Node cur : num){
            if(tmp[cur.x][cur.y] == 0 || tmp[cur.x][cur.y] == stand)
                continue;

            int[][] v = new int[6][6];

            Queue<Node> q = new LinkedList<>();
            v[cur.x][cur.y] = 1;
            q.add(cur);

            boolean find = false;

            while(!q.isEmpty()){
                Node t = q.poll();

                for(int i=0;i<4;i++){
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];

                    if(nx < 1 || nx > 5 || ny < 1 || ny > 5)
                        continue;

                    if(v[nx][ny] == 0 && tmp[nx][ny] == 0){
                        v[nx][ny] = v[t.x][t.y] + 1;
                        q.add(new Node(nx,ny));
                    }

                    if(tmp[nx][ny] == stand && !find){
                        find = true;
                        tmp[cur.x][cur.y] = 0;
                        tmp[t.x][t.y] = stand;
                        count += v[t.x][t.y] - 1;
                        break;
                    }
                }
            }

        }


        return count;
    }

    static int dis(int x,int y,int lx,int ly){
        v = new int[6][6];
        Queue<Node> q = new LinkedList<>();
        v[x][y] = 1;
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == lx && cur.y == ly){
                return v[lx][ly] - 1 ;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > 5 || ny < 1 || ny > 5)
                    continue;

                if(v[nx][ny] == 0){
                    v[nx][ny] = v[cur.x][cur.y] + 1;
                    q.add(new Node(nx,ny));
                }
            }
        }

        return 0;
    }

    static void print(int[][] board){
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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
