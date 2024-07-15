import java.util.*;
import java.io.*;

public class Main {

    static int tmpNum;
    static int[] root;
    static int n,m;
    static int[][] board;
    static int[][] group;

    static Queue<Node> myQ = new ArrayDeque<>();
    static Queue<Node> nextQ = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        group = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = arr[j-1] - '0';
            }
        }

        int num = 1;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(board[i][j] != 0 && group[i][j] == 0) {
                    findGroup(i,j,num);
                    num++;
                }
            }
        }

        root = new int[num];
        num -= 1;
        tmpNum = num;

        for(int i = 1 ; i <= num ; i++) {
            root[i] = i;
        }

        int sec = 0;

        if(num == 1) {
            System.out.println(0);
        }else {
            while (true) {
                go();
                sec++;

                if(tmpNum == 1)
                    break;
            }

            System.out.println(sec);
        }
        br.close();
    }

    static void print() {
        for(int i =1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                System.out.print(group[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static void go() {
        int size = myQ.size();

        for(int i = 0 ; i < size ; i++) {
            Node cur = myQ.poll();

            for(int a = - cur.d ; a <= cur.d ; a++) {
                for(int b = - cur.d ; b <= cur.d ; b++) {
                    int nx = cur.x + a;
                    int ny = cur.y + b;

                    if(isOut(nx,ny))
                        continue;

                    if(board[nx][ny] == 0) {
                        board[nx][ny] = cur.d;
                        group[nx][ny] = cur.num;
                        nextQ.add(new Node(nx,ny,cur.num,cur.d));
                    }else if(board[nx][ny] <= cur.d) {
                        if(find(group[nx][ny]) != find(cur.num)) {
                            board[nx][ny] = cur.d;
                            group[nx][ny] = cur.num;
                            nextQ.add(new Node(nx,ny,cur.num,cur.d));
                        }
                    }
                }
            }
        }

        while(!nextQ.isEmpty()) {
            Node cur = nextQ.poll();

            if(cur.d < board[cur.x][cur.y])
                continue;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(group[nx][ny] != 0) {
                    if(find(group[nx][ny]) != find(cur.num)) {
                        union(group[nx][ny],cur.num);
                        tmpNum--;
                    }
                }
            }

            myQ.add(cur);
        }
    }

    static void printRoot() {
        for(int i =1 ; i< root.length ; i++) {
            System.out.print(find(i)+" ");
        }
        System.out.println();
    }

    static void findGroup(int x,int y,int num) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y,num,board[x][y]));
        group[x][y] = num;
        myQ.add(new Node(x,y,num,board[x][y]));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i = 0; i < 4 ; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(isOut(nx,ny) || board[nx][ny] == 0 || group[nx][ny] != 0)
                    continue;

                group[nx][ny] = num;
                q.add(new Node(nx,ny,num,board[nx][ny]));
                myQ.add(new Node(nx,ny,num,board[nx][ny]));
            }
        }
    }

    static class Node {
        int x;
        int y;
        int num;
        int d;

        Node(int x,int y,int num,int d) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.d = d;
        }
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m) {
            return true;
        }

        return false;
    }

    static int find(int x) {
        if(root[x] == x)
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
