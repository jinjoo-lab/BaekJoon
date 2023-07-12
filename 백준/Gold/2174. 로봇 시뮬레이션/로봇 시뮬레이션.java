import java.util.*;
import java.io.*;

public class Main {

    static int a = 0;
    static int b = 0;
    static int n = 0;
    static int m = 0;

    static int[][] board;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0}; // E , S , W ,N

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        b = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());

        board = new int[a+1][b+1];

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        robot[] ra = new robot[n+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            board[x][y] = i;

            char dir = st.nextToken().charAt(0);

            if(dir == 'E')
                ra[i] = new robot(x,y,0);

            else if(dir == 'N')
                ra[i] = new robot(x,y,3);

            else if(dir == 'W')
                ra[i] = new robot(x,y,2);

            else
                ra[i] = new robot(x,y,1);
        }

        boolean tmp = true;

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            char what = st.nextToken().charAt(0);
            int f = Integer.parseInt(st.nextToken());

            tmp = move(num,what,f,ra);

            if(tmp == false)
                break;
        }

        if(tmp)
            System.out.println("OK");

        br.close();
    }

    static boolean move(int num,char what,int f,robot[] ra)
    {
        int curX = ra[num].x;
        int curY = ra[num].y;
        int curD = ra[num].dir;

        if(what == 'F') {
            for (int i = 1; i <= f; i++) {
                int nx = curX + dx[curD];
                int ny = curY + dy[curD];


                if(nx < 1 || ny > b || ny < 1 || nx > a){
                    System.out.println("Robot "+ num +" crashes into the wall");
                    return false;
                }

                else if(board[nx][ny] != 0 && board[nx][ny] != num)
                {
                    System.out.println("Robot "+ num +" crashes into robot "+board[nx][ny]);
                    return false;
                }

                curX = nx;
                curY = ny;
            }

            board[ra[num].x][ra[num].y] = 0;
            board[curX][curY] = num;

        }

        else if(what == 'L')
        {
            int tmp = f % 4;

            for(int i=0;i<tmp;i++)
            {
                curD = turnL(curD);
            }
        }

        else{
            int tmp = f % 4;

            for(int i=0;i<tmp;i++)
            {
                curD = turnR(curD);
            }
        }


        ra[num] = new robot(curX,curY,curD);
        return true;
    }

    // E , S , W ,N

    static int turnR(int dir)
    {
        if(dir == 3)
            return 0;
        else
            return dir + 1;
    }

    static int turnL(int dir)
    {
        if(dir == 0)
            return 3;
        else
            return dir - 1;
    }

    static void print()
    {
        for(int i=1;i<=a;i++)
        {
            for(int j=1;j<=b;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

class robot
{
    int x;
    int y;
    int dir;

    robot(int x,int y,int dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
