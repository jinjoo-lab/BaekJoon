import java.util.*;
import java.io.*;

public class Main {

    static int resultDay = -1;
    static int isSun = 0;
    static int n,m;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        if(resultDay == -1){
            System.out.println(-1);
        }else{
            System.out.println(resultDay+" "+(isSun == 0 ? "sun" : "moon"));
        }

        br.close();
    }

    static void bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.day == y.day){
                        if(x.isDay == y.isDay){
                            return x.move - y.move;
                        }
                        return x.isDay - y.isDay;
                    }

                    return x.day - y.day;
                }
        );

        pq.add(new Node(1,1,1,0,0));

        boolean[][][][] v = new boolean[n+1][n+1][2][m+1];
        v[1][1][0][0] = true;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.x == n && cur.y == n){

                if(resultDay == -1 || resultDay > cur.day){
                    resultDay = cur.day;
                    isSun = cur.isDay;
                }

                else if(resultDay == cur.day){
                    if(isSun == 1){
                        isSun = cur.isDay;
                    }
                }

                break;
            }

            if(cur.isDay == 0){
                for(int i = 0 ; i < 4 ; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(isOut(nx,ny))
                        continue;

                    if(board[nx][ny] == 1)
                        continue;

                    int tmpMove = cur.move + 1;

                    if(tmpMove == m){
                        if(!v[nx][ny][1][0]){
                            v[nx][ny][1][0] = true;
                            pq.add(new Node(nx,ny,cur.day,0,1));
                        }
                    }else{
                        if(!v[nx][ny][0][tmpMove]){
                            v[nx][ny][0][tmpMove] = true;
                            pq.add(new Node(nx,ny,cur.day,tmpMove,0));
                        }
                    }
                }
            }else{
                for(int i = 0 ; i < 4 ; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(isOut(nx,ny))
                        continue;

                    int tmpMove = cur.move + 1;

                    if(board[nx][ny] == 0){
                        if(tmpMove == m){
                            if(!v[nx][ny][0][0]){
                                v[nx][ny][0][0] = true;
                                pq.add(new Node(nx,ny,cur.day + 1,0,0));
                            }
                        }else{
                            if(!v[nx][ny][1][tmpMove]){
                                v[nx][ny][1][tmpMove] = true;
                                pq.add(new Node(nx,ny,cur.day,tmpMove,1));
                            }
                        }
                    }else{
                        int idx = findJump(nx,ny,i);

                        if(idx == -1)
                            continue;

                        nx = nx + idx*dx[i];
                        ny = ny + idx*dy[i];


                        if(tmpMove == m){
                            if(!v[nx][ny][0][0]){
                                v[nx][ny][0][0] = true;
                                pq.add(new Node(nx,ny,cur.day + 1,0,0));
                            }
                        }else{
                            if(!v[nx][ny][1][tmpMove]){
                                v[nx][ny][1][tmpMove] = true;
                                pq.add(new Node(nx,ny,cur.day,tmpMove,1));
                            }
                        }
                    }
                }
            }
        }

    }

    static int findJump(int x,int y,int dir){
        int idx = 1;

        while(true){
            int nx = x + idx*dx[dir];
            int ny = y + idx*dy[dir];

            if(isOut(nx,ny)){
                break;
            }

            if(board[nx][ny] == 0){
                return idx;
            }

            idx++;
        }

        return -1;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;
        return false;
    }

    static class Node{
        int x;
        int y;
        int day;
        int move;
        int isDay;

        Node(int x,int y,int day,int move,int isDay){
            this.x = x;
            this.y = y;
            this.day = day;
            this.move = move;
            this.isDay = isDay;
        }
    }
}
