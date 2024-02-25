
import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    static int n = 0;

    static int m = 0;

    static int[] root;

    static int[][] dis;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        int start = 3;
        for(int i = 1 ; i <= n ; i++){
            String line = br.readLine();

            for(int j = 1 ;j <= n ;j++){
                char cur = line.charAt(j -1);

                if(cur == '1'){
                    board[i][j] = -1;
                }else if(cur == 'S'){
                    board[i][j] = 2;
                }else if(cur == 'K'){
                    board[i][j] = start;
                    start++;
                }
            }
        }

        root = new int[start+1];
        for(int i=1 ; i <=start ;i++){
            root[i] = i;
        }

        dis = new int[start+1][start+1];

        for(int i = 1 ; i <=n ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(board[i][j] >= 2){
                    bfs(i,j);
                }
            }
        }

        int numOfV = start - 2;


        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        for(int i = 2 ; i < numOfV + 1 ; i++){
            for(int j= i + 1 ; j<=numOfV + 1;j++){

                if(dis[i][j] == 0)
                    continue;

                pq.add(new int[]{i,j,dis[i][j]});
            }
        }

        int result = 0;
        int count = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();


            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                result += cur[2];
                count ++;
            }

            if(count == numOfV -1)
                break;
        }

        if(count != numOfV -1){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

        br.close();
    }

    static void print(int num){
        for(int i = 1 ; i <= num; i++){
            for(int j = 1 ; j <= num; j++){
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int x,int y){
        Queue<Node> q = new LinkedList<>();
        boolean[][] v = new boolean[n+1][n+1];
        q.add(new Node(x,y,0));
        v[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i =0  ;i < 4 ;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                if(v[nx][ny] || board[nx][ny] == -1)
                    continue;

                v[nx][ny] = true;

                if(board[nx][ny] >= 2){
                    dis[board[x][y]][board[nx][ny]] = cur.d + 1;
                    dis[board[nx][ny]][board[x][y]] = dis[board[x][y]][board[nx][ny]];
                }

                q.add(new Node(nx,ny,cur.d + 1));
            }
        }
    }

    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
class Node{
    int x;
    int y;
    int d;

    Node(int x,int y,int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
