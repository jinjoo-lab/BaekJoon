import java.util.*;
import java.io.*;

public class Main {
    static char[][] board;

    static int[] dx = {0,0,-1,1,1,1,-1,-1,0};
    static int[] dy = {1,-1,0,0,1,-1,1,-1,0};

    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[9][9];

        for(int i=1;i<=8;i++){
            String line = br.readLine();

            for(int j=1;j<=8;j++){
                board[i][j] = line.charAt(j-1);
            }
        }

        q.add(new Node(8,1));

        while(true){
            boolean tmp = search();

            if(tmp){
                System.out.println(1);
                break;
            }

            else if(!tmp && q.size() == 0)
            {
                System.out.println(0);
                break;
            }

            move();

        }
        br.close();
    }

    static boolean search(){

        int size = q.size();

        while(size != 0){
            Node cur = q.poll();
            size--;

            if(board[cur.x][cur.y] == '#')
                continue;

            if(cur.x == 1 && cur.y == 8)
                return true;

            for(int i=0;i<9;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > 8 || ny < 1 || ny > 8)
                    continue;

                if(board[nx][ny] == '#')
                    continue;

                q.add(new Node(nx,ny));
            }
        }

        return false;
    }

    static void print(){
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void move(){
        for(int i=8;i>=1;i--){
            for(int j=1;j<=8;j++){
                if(board[i][j] == '#') {
                    int nx = i + dx[3];
                    int ny = j + dy[3];

                    if (nx > 8)
                    {
                        board[i][j] = '.';
                        continue;
                    }

                    else{
                        board[nx][ny] = '#';
                        board[i][j] = '.';
                    }
                }
            }
        }
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