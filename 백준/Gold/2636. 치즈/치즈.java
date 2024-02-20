import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static Queue<int[]> q = new LinkedList<>();

    static Queue<int[]> tmp = new LinkedList<>();

    static boolean[][] v;

    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        v = new boolean[n+1][m+1];
        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == 1){
                    count++;
                }
            }
        }

        q.add(new int[]{1,1});
        int sec = 0;
        int tmp = 0;
        while(count > 0){
            tmp = bfs();
            sec += 1;

            count -= tmp;
        }

        System.out.println(sec);
        System.out.println(tmp);
        br.close();
    }

    static int bfs(){

        int tmpCount = 0;

        tmp.clear();

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny])
                    continue;

                v[nx][ny] = true;

                if(board[nx][ny] == 1){
                    tmp.add(new int[]{nx,ny});
                    tmpCount += 1;
                }else{
                    q.add(new int[]{nx,ny});
                }
            }
        }

        while(!tmp.isEmpty()){
            q.add(tmp.poll());
        }

        return tmpCount;
    }
}

