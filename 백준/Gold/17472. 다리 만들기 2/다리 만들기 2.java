
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int[][] board;

    static int[][] v;
    static int n, m;

    static int[] root;

    static Queue<int[]>[] border = new ArrayDeque[7];

    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x,y) -> x[2] - y[2]
    );

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i= 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j= 1 ;j <=m ;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;

        for(int i= 1  ; i <= 6; i++){
            border[i] = new ArrayDeque<>();
        }

        for(int i=1 ; i <= n ;i++){
            for(int j=1 ;j<=m ;j++){
                if(v[i][j] == 0 && board[i][j] == 1){
                    bfs(i,j,num++);
                }
            }
        }

        root = new int[num];
        for(int i= 1 ; i < num ; i++){
            root[i] = i;
        }
        num -= 1;

        for(int i=1  ;i <= num ; i++){
            makeDis(i);
        }

        int result = 0;
        int count = 0;


        while(!pq.isEmpty()){
            int[] tmp = pq.poll();

            if(find(tmp[0]) != find(tmp[1])){
                union(tmp[0],tmp[1]);
                count += 1;
                result += tmp[2];
            }

            if(count == num -1)
                break;
        }

        if(count != num - 1)
            System.out.println(-1);
        else
            System.out.println(result);

        br.close();
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

    static void makeDis(int num){

        int[] dis = new int[7];

        while(!border[num].isEmpty()){
            int[] cur = border[num].poll();

            if(cur[2] == -1){
                for(int i = 0 ; i < 4 ; i++){
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if(nx < 1 || nx > n || ny < 1 || ny > m)
                        continue;

                    if(v[nx][ny] == num)
                        continue;

                    if(board[nx][ny] == 0){
                        border[num].add(new int[]{nx,ny,i,1});
                    }
                }
            }
            else{
                int nx = cur[0] + dx[cur[2]];
                int ny = cur[1] + dy[cur[2]];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny] == num)
                    continue;

                if(board[nx][ny] == 0){
                    border[num].add(new int[]{nx,ny,cur[2],cur[3] + 1});
                }else if(v[nx][ny] != num){
                    if(cur[3] < 2)
                        continue;

                    dis[v[nx][ny]] = (dis[v[nx][ny]] == 0 ) ? cur[3] : Math.min(dis[v[nx][ny]], cur[3]);
                }
            }
        }

        for(int i = 1 ; i <= 6 ; i++){

            if(dis[i] < 2 || i == num)
                continue;

            pq.add(new int[]{num,i,dis[i]});
        }

    }

    static void print(){
        for(int i= 1 ; i<= n ;i++){
            for(int j=1 ; j<=m ;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int x , int y,int num){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        v[x][y] = num;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(v[nx][ny] != 0)
                    continue;

                if(board[nx][ny] == 0){
                    border[num].add(new int[]{cur[0],cur[1],-1});
                    continue;
                }

                v[nx][ny] = num;
                q.add(new int[]{nx,ny});
            }
        }
    }
}