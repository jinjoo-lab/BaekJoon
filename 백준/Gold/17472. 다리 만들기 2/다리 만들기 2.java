import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;
    static int[][] land;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static Queue<Point>[] landPoint = new LinkedList[7];

    static PriorityQueue<Point> pq = new PriorityQueue<>(
            (x,y) -> x.d - y.d
    );

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        land = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=6;i++){
            landPoint[i] = new LinkedList<Point>();
        }

        int num = 1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == 1 && land[i][j] == 0){
                    find(num,i,j);
                    num = num + 1;
                }
            }
        }

        root = new int[num+1];

        num = num - 1;
        for(int i=1;i<=num;i++){
            root[i] = i;
            dis(i);
        }

        int count = 0;
        int result = 0;
        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(find(cur.x) != find(cur.y)){
                count += 1;
                result += cur.d;
                union(cur.x,cur.y);
            }

            if(count == num -1)
                break;
        }

        if(count == num -1){
            System.out.println(result);
        }

        else{
            System.out.println(-1);
        }

        br.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(land[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void find(int num,int x,int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        land[x][y] = num;
        landPoint[num].add(new Point(x,y,-1));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(land[nx][ny] == 0 && board[nx][ny] == 1){
                    land[nx][ny] = num;
                    q.add(new Node(nx,ny));
                    landPoint[num].add(new Point(nx,ny,-1));
                }
            }
        }
    }
    static int find(int x){
        if(root[x] == x)
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y){
            root[y] = x;
        }

        else{
            root[x] = y;
        }
    }

    static void dis(int num){
        int[][][] dis = new int[n+1][m+1][4];
        while(!landPoint[num].isEmpty()){
            Point cur = landPoint[num].poll();

            if(cur.d == -1){
                for(int i =0;i < 4;i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 1 || nx > n || ny < 1 || ny > m)
                        continue;

                    if(land[nx][ny] != 0)
                        continue;

                    if(dis[nx][ny][i] == 0){
                        dis[nx][ny][i] = 1;
                        landPoint[num].add(new Point(nx,ny,i));
                    }
                }
            }

            else{
                int nx = cur.x + dx[cur.d];
                int ny = cur.y + dy[cur.d];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(land[nx][ny] == num)
                    continue;

                if(dis[nx][ny][cur.d] == 0){
                   if(land[nx][ny] == 0){
                       dis[nx][ny][cur.d] = dis[cur.x][cur.y][cur.d] + 1;
                       landPoint[num].add(new Point(nx,ny,cur.d));
                   }

                   else{
                       if(dis[cur.x][cur.y][cur.d] >= 2){
                           pq.add(new Point(num,land[nx][ny],dis[cur.x][cur.y][cur.d]));
                       }
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
class Point{
    int x;int y;int d;

    Point(int x,int y,int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}