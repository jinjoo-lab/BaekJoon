import java.util.*;
import java.io.*;
public class Main {

    static int result = -1;

    static int n = 0;
    static int m = 0;

    static int dx[] = {0,0,-1,1,0,0};
    static int dy[] = {1,-1,0,0,0,0};
    static int dz[] = {0,0,0,0,-1,1};

    static int[][][] board;

    static int[] num = new int[6];
    static int[] spinNum = new int[6];
    static boolean[] v= new boolean[6];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[6][6][6];

        for(int i=1;i<=5;i++){

            for(int j=1;j<=5;j++){
                st = new StringTokenizer(bf.readLine(), " ");

                for(int k=1;k<=5;k++){
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        travel(1);
        System.out.println(result);
        bf.close();

    }

    static void tmpPrint(int[][][] board){
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                System.out.print(board[1][i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[][][] copy(){
        int[][][] copy = new int[6][6][6];

        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                for(int k=1;k<=5;k++){
                    copy[i][j][k] = board[num[i]][j][k];
                }
            }
        }

        return copy;
    }

    static void bfs(int[][][] board){
        int[][][] v = new int[6][6][6];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,1,1));
        v[1][1][1] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == 5 && cur.y == 5 && cur.z == 5){

                if(result == -1 || result > v[5][5][5] - 1){
                    result = v[5][5][5] - 1;
                    return;
                }
            }


            for(int i=0;i<6;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if(nx < 1 || nx > 5 || ny < 1 || ny > 5 || nz < 1 || nz > 5)
                    continue;

                if(board[nx][ny][nz] == 0)
                    continue;


                if(v[nx][ny][nz] == 0 || v[nx][ny][nz] > v[cur.x][cur.y][cur.z] + 1){
                    v[nx][ny][nz] = v[cur.x][cur.y][cur.z] + 1;
                    q.add(new Node(nx,ny,nz));

                }
            }
        }
    }

    static void spin(int where,int[][][] board){
        int[][] tmp = new int[6][6];

        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                tmp[i][j] = board[where][j][6 - i];
            }
        }

        board[where] = tmp;
    }

    static void print(int[][][] board){
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                for(int k=1;k<=5;k++){
                    System.out.print(board[i][j][k]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    static void go(int[][][] tmp){
        for(int a=1;a<=4;a++){
            spin(1,tmp);
            for(int b=1;b<=4;b++){
                spin(2,tmp);
                for(int c=1;c<=4;c++){
                    spin(3,tmp);
                    for(int d=1;d<=4;d++){
                        spin(4,tmp);
                        for(int e=1;e<=4;e++){
                            spin(5,tmp);

                            if(tmp[1][1][1] == 0 || tmp[5][5][5] == 0){
                                continue;
                            }

                            bfs(tmp);
                        }
                    }
                }
            }
        }
    }


    static void travel(int d){
        if(d == 6){
            int[][][] tmp = copy();
            go(tmp);
            return;
        }

        for(int i=1;i<=5;i++){
            if(!v[i]){
                v[i] = true;
                num[d] = i;
                travel(d + 1);
                v[i] = false;
            }
        }
    }
}
class Node{
    int x;
    int y;

    int z;

    Node(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
