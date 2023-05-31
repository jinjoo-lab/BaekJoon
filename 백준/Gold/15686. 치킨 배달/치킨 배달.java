import java.io.*;
import java.util.*;
/*
 * 백준 15686 치킨 배달
 * 치킨 거리 : 집가 가장 가까운 치킨집까지 거리
 * 도시 치킨 거리 : 치킨 거리 총
 * m개를 폐업 시킬때 치킨 거리가 가장 작을까?
 * */
public class Main {
    static int n = 0;
    static int m = 0;
    static int result = 25000000;
    static int count = 1;
    static int count2 = 1;
    static point[] chicken = new point[14];
    static point[] home = new point[101];
    static int[][] board = new int[51][51];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 0은 빈 칸, 1은 집, 2는 치킨집
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==2)
                {
                    chicken[count]= new point(i,j);
                    count++;
                }
                if(board[i][j]==1)
                {
                    home[count2] = new point(i,j);
                    count2++;
                }
            }
        }
        travel(1,0,1);
        System.out.println(result);
        br.close();
    }
    static int minus(point x,point y)
    {
        int nx = Math.abs(x.x - y.x);
        int ny = Math.abs(x.y - y.y);

        return nx+ny;
    }
    static void travel(int k,int choose,int now)
    {
        if(k==count)
        {
            int num = 0;
            if(choose==m) {
                for(int i=1;i<count2;i++)
                {
                    int dis = 25000000;
                    point cur = home[i];
                    for(int j=1;j<count;j++)
                    {
                        if(board[chicken[j].x][chicken[j].y]==0)
                            continue;
                        int tmp = minus(cur,chicken[j]);
                        if(dis>tmp) {
                            dis = tmp;
                        }
                    }
                    num = num+dis;
                }

                if (result > num) {
                    result = num;
                }
            }
            return;
        }

        board[chicken[now].x][chicken[now].y] = 0;
        travel(k + 1, choose,now+1);
        board[chicken[now].x][chicken[now].y] = 2;
        travel(k + 1, choose + 1,now+1);

    }
}
class point
{
    int x;
    int y;
    point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}