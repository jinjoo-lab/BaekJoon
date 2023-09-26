import java.io.*;
import java.util.*;
/*
 * 백준 1987 알파벳
 * 세로 R , 가로 C
 * 말은 상하좌우 이동 -> bfs 생각 열어두기
 * 새로 이동한 칸의 알파벳은 중복 불가
 * 좌측 상단 (1,1) -> 출발점이 주어진다
 * 말이 최대 몇칸 지날 수 있나? , 출발점 포함 , visit[1][1] = 1
 */
public class Main {
    static int n = 0;
    static int m = 0;
    static int result =-1;
    static int[] dx ={0,0,-1,1};
    static int[] dy ={1,-1,0,0};
    static char[][] board = new char[21][21];
    static boolean[][] visit =new boolean[21][21];
    static boolean[] isIn = new boolean[27];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        for(int i=1;i<=n;i++)
        {
            line = br.readLine().split("");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = line[j-1].charAt(0);
            }
        }
        visit[1][1] = true;
        isIn[board[1][1]-'A'] = true;
        travel(new point(1,1),1);
        System.out.println(result);
        br.close();
    }

    static void travel(point cur,int count)
    {
        for(int i=0;i<4;i++)
        {
            int nx = cur.x+dx[i];
            int ny = cur.y+dy[i];

            if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
            {
                if(visit[nx][ny]==false&&!isIn[board[nx][ny]-'A'])
                {
                    visit[nx][ny]=true;
                    isIn[board[nx][ny]-'A'] = true;
                    travel(new point(nx,ny),count+1);
                    visit[nx][ny]=false;
                    isIn[board[nx][ny]-'A'] = false;
                }

                else{
                    if(result<count)
                    {
                        result = count;
                    }
                }
            }
        }
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