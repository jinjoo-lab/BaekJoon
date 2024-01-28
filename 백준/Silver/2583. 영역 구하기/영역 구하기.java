import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static int m = 0;

    static int k = 0;

    static int[][] board = new int[101][101];

    static boolean[][] v = new boolean[101][101];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();


        // 행 렬 반 대

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int t = 1 ; t <= k ; t++){
            st = new StringTokenizer(bf.readLine(), " ");

            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int lx = Integer.parseInt(st.nextToken());

            for(int i = sx ; i < lx ; i++){
                for(int j = sy ; j < ly ; j ++){
                    board[i][j] = 1;
                }
            }
        }

        int count = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!v[i][j] && board[i][j] == 0){
                    int tmp = bfs(i,j);
                    count += 1;
                    pq.add(tmp);
                }
            }
        }


        sb.append(count+"\n");
        while(!pq.isEmpty()){
            sb.append(pq.poll()+" ");
        }sb.append("\n");

        System.out.print(sb);
        bf.close();
    }

    static int bfs(int x,int y){

        int count = 1;
        Queue<Node> q = new LinkedList();
        q.add(new Node(x,y));
        v[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(board[nx][ny] == 1 || v[nx][ny])
                    continue;

                v[nx][ny]= true;
                q.add(new Node(nx,ny));
                count ++;
            }
        }

        return count;
    }

    static void print(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
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



