import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int w = 0;
    static int s = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        boolean[][] visit = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();

            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1);

                if(board[i][j] == 'v')
                    w+=1;

                else if(board[i][j] =='o')
                    s+=1;
            }
        }


        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(!visit[i][j] && board[i][j] == 'o'){
                    search(i,j,visit);
                }
            }
        }

        System.out.println(s+" "+w);

        br.close();
    }

    static void search(int x,int y,boolean[][] visit){
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y));
        visit[x][y] = true;
        int stmp = 1;
        int wtmp = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(!visit[nx][ny] && board[nx][ny] != '#'){
                    if(board[nx][ny] == 'o'){
                        stmp += 1;
                    }

                    else if(board[nx][ny] == 'v'){
                        wtmp += 1;
                    }

                    visit[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }

        if(stmp > wtmp){
            w -= wtmp;
        }

        else{
            s -= stmp;
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
