
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int sx,sy,lx,ly;

    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};

    static int[][] v;

    static int min = -1;
    static int count  = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        lx = Integer.parseInt(st.nextToken());
        ly = Integer.parseInt(st.nextToken());

        v = new int[n+1][n+1];

        bfs(sx,sy);

        System.out.println(v[lx][ly] - 1);
        br.close();
    }

    static void bfs(int x,int y){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        v[x][y] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 6 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if(v[nx][ny] == 0 || v[nx][ny] > v[cur[0]][cur[1]] + + 1){
                    v[nx][ny] = v[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }
}
