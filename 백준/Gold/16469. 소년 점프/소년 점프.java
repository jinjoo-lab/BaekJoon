import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] arr;
    static int[][][] v;

    static int[] dx  = {0,0,-1,1};
    static int[] dy  = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=m;j++){
                arr[i][j] = line.charAt(j-1) - '0';
            }
        }

        v = new int[n+1][m+1][3];

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            bfs(x,y,i);
        }


        int num = 0;
        int sec = Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(arr[i][j] == 1)
                    continue;

                if(v[i][j][0] == 0 || v[i][j][1] == 0 || v[i][j][2] == 0)
                    continue;

                int tmp = Math.max(Math.max(v[i][j][0],v[i][j][1]),v[i][j][2]);

                if(sec > tmp){
                    sec = tmp;
                    num = 1;
                }else if(sec == tmp){
                    num += 1;
                }

            }
        }

        if(sec != Integer.MAX_VALUE) {
            System.out.println(sec - 1);
            System.out.println(num);
        }else{
            System.out.println(-1);
        }

        br.close();

    }

    static void print(int num){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                System.out.print(v[i][j][num]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int x,int y,int num){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        v[x][y][num] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(arr[nx][ny] == 1)
                    continue;


                if(v[nx][ny][num] == 0){
                    v[nx][ny][num] = v[cur.x][cur.y][num] + 1;
                    q.add(new Node(nx,ny));
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