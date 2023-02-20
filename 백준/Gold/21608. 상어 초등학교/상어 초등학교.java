import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] board = new int[21][21];
    static int n = 0;
    static Queue<student> q = new LinkedList<>();
    static Queue<point> sq = new LinkedList<>();

    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        for(int i = 1;i<=n*n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.add(new student(num,a,b,c,d));
        }

        where();

        score();
        System.out.println(result);

        br.close();
    }
    static void score()
    {
        while(!sq.isEmpty())
        {
            point cur = sq.poll();
            int num = 0;
            for(int i=0;i<4;i++)
            {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(board[nx][ny]==cur.like[0]||board[nx][ny]==cur.like[1]||board[nx][ny]==cur.like[2]||board[nx][ny]==cur.like[3])
                    {
                        num++;
                    }
                }
            }

            if(num==1)
            {
                result = result + 1;
            }
            else if(num==2)
            {
                result = result + 10;
            }
            else if(num==3)
            {
                result = result + 100;
            }
            else if(num==4)
            {
                result = result + 1000;
            }
        }
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void where()
    {
        while(!q.isEmpty())
        {
            student cur = q.poll();
            int like = 0;
            int empty = 0;
            int nx = -1;
            int ny = -1;
            for(int i=1;i<=n;i++)
            {
                for(int j=1;j<=n;j++)
                {
                    int tempty = 0;
                    int tlike = 0;
                    if(board[i][j]==0)
                    {
                        for(int k=0;k<4;k++)
                        {
                            int cx = i + dx[k];
                            int cy = j + dy[k];

                            if(cx>=1&&cx<=n&&cy>=1&&cy<=n)
                            {
                                if(board[cx][cy]==0)
                                {
                                    tempty++;
                                }

                                else if(board[cx][cy]==cur.like[0]||board[cx][cy]==cur.like[1]||board[cx][cy]==cur.like[2]||board[cx][cy]==cur.like[3])
                                {
                                    tlike++;
                                }
                            }
                        }

                        if(tlike>like)
                        {
                            like = tlike;
                            empty = tempty;
                            nx = i;
                            ny = j;
                        }
                        else if(tlike==like)
                        {
                            if(tempty>empty)
                            {
                                like = tlike;
                                empty = tempty;
                                nx = i;
                                ny = j;
                            }
                        }
                    }
                }
            }
            if(nx!=-1&&ny!=-1)
            {
                board[nx][ny] = cur.num;
                sq.add(new point(nx,ny,cur.like));
            }
            else if(nx==-1&&ny==-1)
            {
                for1 : for(int i=1;i<=n;i++)
                {
                    for(int j=1;j<=n;j++)
                    {
                        if(board[i][j]==0)
                        {
                            board[i][j]=cur.num;
                            sq.add(new point(i,j,cur.like));
                            break for1;
                        }
                    }
                }
            }

        }
    }
}
class student
{
    int num;
    int[] like = new int[4];

    student(int num,int a,int b,int c,int d)
    {
        this.num = num;
        like[0] = a;
        like[1] = b;
        like[2] = c;
        like[3] = d;
    }
}

class point
{
    int x;
    int y;
    int[] like;

    point(int x,int y,int[] like)
    {
        this.x = x;
        this.y = y;
        this.like = like;
    }
}
