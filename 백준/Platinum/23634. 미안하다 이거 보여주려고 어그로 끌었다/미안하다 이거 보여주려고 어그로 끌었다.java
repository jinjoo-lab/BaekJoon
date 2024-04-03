
import java.util.*;
import java.io.*;

public class Main {

    static int resultCount = 0;
    static Queue<int[]> tmpQ = new ArrayDeque<>();
    static boolean[][] v;
    static int n,m,groupNum;
    static int[][] board;

    static int[][] groupArea;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[] root,will;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        groupArea = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j =1 ; j <= m ; j++){
                board[i][j] = arr[j-1] - '0';
            }
        }

        br.close();
    }

    public static void main(String[] args) throws Exception {

        input();

        groupNum = makeGroup();

        if(groupNum == 0){
            System.out.println("0 0");
            return;
        }

        root = new int[groupNum+1];
        will = new int[groupNum+1];
        for(int i = 1 ;i <=groupNum;i++){
            root[i] = i;
            will[i] = i;
        }

        makeResult();

        int day = 0;

        while(true) {
            if(isOver())
                break;

            go();

            day++;

        }

        System.out.println(day+" "+resultCount);
    }

    static void go(){
        int size = tmpQ.size();
        Queue<int[]> oneMore = new ArrayDeque<>();

        while(size != 0){
            size--;

            int[] cur = tmpQ.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 2)
                    continue;

                if(board[nx][ny] == 1 || board[nx][ny] == 0){
                    if(groupArea[nx][ny] == 0){
                        groupArea[nx][ny] = groupArea[cur[0]][cur[1]];
                        resultCount++;
                        tmpQ.add(new int[]{nx,ny});
                    }else if(groupArea[nx][ny] != 0){
                        union(groupArea[cur[0]][cur[1]] , groupArea[nx][ny] , root);
                    }

                    oneMore.add(new int[]{nx,ny});
                }
            }
        }


        while(!oneMore.isEmpty()){
            int[] tmp = oneMore.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(groupArea[nx][ny] >= 1){
                    union(groupArea[nx][ny],groupArea[tmp[0]][tmp[1]],root);
                }
            }
        }
    }

    static boolean isOver(){
        for(int i = 1 ; i <= groupNum ; i++){
            if (find(i,root) != find(i,will))
                return false;
        }

        return true;
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(groupArea[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int makeGroup(){

        v = new boolean[n+1][m+1];
        int idx = 1;

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(!v[i][j] && board[i][j] == 0){
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});
                    tmpQ.add(new int[]{i,j});
                    v[i][j] = true;
                    groupArea[i][j] = idx;
                    resultCount++;

                    while(!q.isEmpty()) {
                        int[] cur = q.poll();

                        for(int k = 0 ; k < 4 ; k++){
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if(nx < 1 || nx > n || ny < 1 || ny > m)
                                continue;

                            if(board[nx][ny] == 2 || v[nx][ny])
                                continue;

                            v[nx][ny] = true;

                            if(board[nx][ny] == 0){
                                groupArea[nx][ny] = idx;
                                q.add(new int[]{nx,ny});
                                tmpQ.add(new int[]{nx,ny});
                                resultCount++;
                            }
                        }
                    }

                    idx++;
                }
            }
        }

        return idx - 1;
    }

    static void makeResult() {

        boolean[][] v = new boolean[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m; j++){
                if(!v[i][j] && board[i][j] == 0){
                    v[i][j] = true;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j,groupArea[i][j]});

                    while(!q.isEmpty()){
                        int[] cur = q.poll();

                        for(int k = 0 ; k< 4 ; k++){
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if(nx < 1 || nx > n || ny < 1 || ny > m)
                                continue;

                            if(board[nx][ny] == 2 || v[nx][ny])
                                continue;

                            v[nx][ny] = true;

                            if(board[nx][ny] == 1){
                                q.add(new int[]{nx,ny,cur[2]});
                            }else if(board[nx][ny] == 0){
                                union(cur[2],groupArea[nx][ny],will);
                                q.add(new int[]{nx,ny,cur[2]});
                            }
                        }
                    }
                }
            }
        }

    }

    static void printOneRoll(int[] root){
        for(int i = 1 ; i <= groupNum ; i++){
            System.out.print(find(i,root)+" ");
        }
        System.out.println();
    }

    static int find(int x,int[] root) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x] , root);
    }

    static void union(int x,int y,int[] root){
        x = find(x,root);
        y = find(y,root);

        if(x < y){
            root[y] = x;
        }else{
            root[x] = y;
        }
    }
}
