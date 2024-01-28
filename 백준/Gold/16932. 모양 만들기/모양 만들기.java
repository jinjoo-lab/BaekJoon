import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[][] v;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i=1;i<=n;i++){

            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == 1 && v[i][j] == 0){
                    bfs(i,j,num);
                    num ++;
                }
            }
        }

        int result = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == 0){
                    HashSet<Integer> set = new HashSet<>();
                    int tmp = 1;

                    for(int k=0;k<4;k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 1 || nx > n || ny < 1 || ny > m)
                            continue;

                        if(board[nx][ny] == 1 && !set.contains(v[nx][ny])) {
                            set.add(v[nx][ny]);
                            tmp += map.get(v[nx][ny]);
                        }
                    }

                    result = Math.max(result,tmp);
                }
            }
        }

        System.out.println(result);

        bf.close();
    }

    static void bfs(int x,int y,int num){
        Queue<Node> q = new LinkedList<>();
        v[x][y] = num;
        q.add(new Node(x,y));
        int count = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny] != 0 || board[nx][ny] == 0)
                    continue;

                v[nx][ny] = num;
                q.add(new Node(nx,ny));
                count += 1;
            }
        }

        map.put(num,count);
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




