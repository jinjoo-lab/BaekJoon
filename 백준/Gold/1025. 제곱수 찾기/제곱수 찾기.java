import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static String[][] board;

    static int[] dx = {1,-1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,-1,1,1,-1,1,-1};

    static int result  = -1;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new String[n+1][m+1];
        v = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = br.readLine();
            for(int j=1;j<=m;j++){
                board[i][j] = line.charAt(j-1)+"";
            }
        }

        int maxP = Math.max(n,m);

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j].equals("0")){
                    result = Math.max(result,0);
                }

                else{
                        for(int ip = 1;ip<=maxP;ip++){
                            for(int jp = 1;jp<=maxP;jp++){
                                if(ip == 0 && jp == 0)
                                    continue;

                                for(int d=0;d<8;d++){
                                    travel(i,j,d,ip,jp,board[i][j]);
                                }
                            }
                        }
                    }
                }
        }

        System.out.println(result);
        br.close();
    }

    static void travel(int x,int y,int dir,int sx,int sy,String cur){
        cal(cur);

        int nx = x + (sx * dx[dir]);
        int ny = y + (sy * dy[dir]);

        if(nx < 1 || nx > n || ny < 1 || ny > m)
            return;

        travel(nx,ny,dir,sx,sy,cur+board[nx][ny]);
    }

    static void cal(String cur){
        int tmp = Integer.parseInt(cur);

        double tmpD = Math.sqrt(tmp);
        int tmpI = (int)tmpD;

        if(tmpD == tmpI){
            result = Math.max(result,tmp);
        }
    }
}
