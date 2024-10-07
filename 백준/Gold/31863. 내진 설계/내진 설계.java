import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int cc;

    static int[][] v;

    static int n,m;

    static char[][] board;

    static int sx,sy;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] tmp = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = tmp[j - 1];

                if(board[i][j] == '@') {
                    sx = i;
                    sy = j;
                }

                if(board[i][j] == '*' || board[i][j] == '#') {
                    cc++;
                }
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sx,sy));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(board[cur.x][cur.y] == '@') {
                for(int i = 0 ; i < 4 ; i++) {
                    for(int j = 1 ; j <= 2 ; j++) {
                        int nx = cur.x + dx[i] * j;
                        int ny = cur.y + dy[i] * j;
                        
                        if(isOut(nx,ny))
                            break;

                        if(board[nx][ny] == '|')
                            break;

                        if(board[nx][ny] == '*') {
                            board[nx][ny] = '.';
                            result++;
                            q.add(new Node(nx,ny));
                        }else if(board[nx][ny] == '#') {
                            v[nx][ny] += 1;

                            if(v[nx][ny] == 2) {
                                board[nx][ny] = '.';
                                result++;
                                q.add(new Node(nx,ny));
                            }
                        }
                    }
                }
            }else {
                for(int i = 0 ; i < 4 ; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(isOut(nx,ny))
                        continue;

                    if(board[nx][ny] == '*') {
                        board[nx][ny] = '.';
                        result++;
                        q.add(new Node(nx,ny));
                    }else if(board[nx][ny] == '#') {
                        v[nx][ny] += 1;

                        if(v[nx][ny] == 2) {
                            board[nx][ny] = '.';
                            result++;
                            q.add(new Node(nx,ny));
                        }
                    }
                }
            }
        }


        System.out.println(result+" "+(cc - result));
        br.close();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
