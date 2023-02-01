import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[5][10];
    static LinkedList<turn> list = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1;i<=4;i++)
        {
            String[] line = br.readLine().split("");
            for(int j=1;j<=8;j++)
            {
                board[i][j] = Integer.parseInt(line[j-1]);
            }
        }
        int m = Integer.parseInt(br.readLine());

        for(int i=1;i<=m;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            list.add(new turn(num,dir));
        }
        for(turn cur : list)
        {
            damage(cur.num,cur.dir);
        }
        int result = score();
        System.out.println(result);
        br.close();
    }
    static int score()
    {
        int result = 0;
        if(board[1][1]==1)
        {
            result = result + 1;
        }
        if(board[2][1]==1)
        {
            result = result + 2;
        }
        if(board[3][1]==1)
        {
            result = result+ 4;
        }
        if(board[4][1]==1)
        {
            result = result +8;
        }

        return result;
    }
    static int change(int dir)
    {
        if(dir==1) {
            return -1;
        }
        else {
            return 1;
        }
    }
    static void damage(int num,int dir)
    {
        int[] isMove = new int[5];
        if(num==1)
        {
            isMove[1] = dir;
            if(board[num][3]!=board[num+1][7])
            {
                int cdir = change(dir);
                isMove[2] = cdir;

                if(board[num+1][3]!=board[num+2][7])
                {
                    cdir = change(cdir);
                    isMove[3] = cdir;

                    if(board[num+2][3]!=board[num+3][7])
                    {
                        cdir = change(cdir);
                        isMove[4] = cdir;
                    }
                }
            }
        }
        else if(num==2)
        {
            isMove[num] = dir;
            int cdir = change(dir);

            if(board[num-1][3]!=board[num][7])
            {
                isMove[1] = cdir;
            }
            if(board[num][3]!=board[num+1][7])
            {
                isMove[3] = cdir;
                if(board[num+1][3]!=board[num+2][7])
                {
                    isMove[4] = change(cdir);
                }
            }
        }

        else if(num==3)
        {
            isMove[3] = dir;
            int cdir = change(dir);

            if(board[num][7]!=board[num-1][3])
            {
                isMove[2] = cdir;

                if(board[num-1][7]!=board[num-2][3])
                {
                    isMove[1] = change(cdir);
                }
            }

            if(board[num][3]!=board[num+1][7])
            {
                isMove[4] = cdir;
            }
        }

        else if(num==4)
        {
            isMove[4] = dir;
            int cdir = change(dir);
            if(board[num][7]!=board[num-1][3])
            {
                isMove[3] = cdir;
                if(board[num-1][7]!=board[num-2][3])
                {
                    cdir =change(cdir);
                    isMove[2] = cdir;

                    if(board[num-2][7]!=board[num-3][3])
                    {
                        cdir = change(cdir);
                        isMove[1] = cdir;
                    }
                }
            }
        }

        for(int i=1;i<=4;i++)
        {
            if(isMove[i]!=0)
            {
                move(i,isMove[i]);
            }
        }
    }
    static void move(int num,int dir)
    {
        int tmp[] = new int[9];
        if(dir==1)
        {
            int last = board[num][8];
            for(int i=1;i<=7;i++)
            {
                tmp[i+1] = board[num][i];
            }
            tmp[1] = last;
        }
        else if(dir==-1)
        {
            int last = board[num][1];
            for(int i=2;i<=8;i++)
            {
               tmp[i-1] = board[num][i];
            }
            tmp[8] = last;
        }
        for(int i=1;i<=8;i++)
        {
            board[num][i] = tmp[i];
        }
    }
}
class turn
{
    int num;
    int dir;

    turn(int num,int dir)
    {
        this.num = num;
        this.dir = dir;
    }
}