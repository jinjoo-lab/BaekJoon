import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;

    static int m = 0;

    static int k = 0;

    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[] playerDps = new int[27];

    static int sx = 0;
    static int sy = 0;

    static int[] dis = new int[27];
    static int bossHealth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for(int i = 0 ; i < n ; i++){

            String line = br.readLine();

            for(int j = 0 ; j < m ; j++){
                board[i][j] = line.charAt(j);

                if(board[i][j] == 'B'){
                    sx = i;
                    sy = j;
                }
            }
        }

        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine()," ");

            char playerName = st.nextToken().charAt(0);
            int damage = Integer.parseInt(st.nextToken());

            playerDps[playerName - 'a'] = damage;
        }

        st = new StringTokenizer(br.readLine()," ");
        bossHealth = Integer.parseInt(st.nextToken());

        bfs();
        game();
        br.close();
    }

    static void game(){
        Queue<Point> q = new LinkedList<>();

        for(int i = 0 ; i < 26 ; i++){
            if(playerDps[i] > 0){
                q.add(new Point((char)('a' + i), dis[i]));
            }
        }

        int totalDps = 0;
        int result = 0;

        while(bossHealth > 0){
            int size = q.size();

            while(size > 0){
                size--;
                Point cur = q.poll();

                if(cur.dis - 1  ==  0){
                    totalDps += playerDps[cur.name - 'a'];
                    result += 1;
                }else{
                    q.add(new Point(cur.name,cur.dis - 1));
                }
            }

            bossHealth -= totalDps;

        }

        System.out.println(result);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[][] v = new boolean[n][m];
        q.add(new Node(sx,sy,0));
        v[sx][sy] = true;


        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(board[nx][ny] == 'X' || v[nx][ny])
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx,ny,cur.c + 1));

                if(board[nx][ny] >= 'a' && board[nx][ny] <= 'z'){
                    dis[board[nx][ny] - 'a'] = cur.c + 1;
                }
            }
        }


    }
}
class Point{
    char name;
    int dis;

    Point(char name,int dis){
        this.name = name;
        this.dis = dis;
    }
}
class Node{
    int x;
    int y;
    int c;

    Node(int x,int y,int c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
