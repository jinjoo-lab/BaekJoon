import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board;
    static Node[] where;
    static ArrayList<Integer>[][] map;

    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        map = new ArrayList[n+1][n+1];
        where = new Node[m+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x= Integer.parseInt(st.nextToken());
            int y= Integer.parseInt(st.nextToken());
            int d= Integer.parseInt(st.nextToken());

            where[i] = new Node(x,y,d);
            map[x][y].add(i);
        }

        int result = -1;
        for(int i=1;i<=1000;i++)
        {
            boolean tmp = turn();

            if(!tmp){
                result = i;
                break;
            }
        }

        System.out.println(result);
        br.close();
    }
    static boolean turn(){

        for(int i=1;i<=m;i++)
        {
            boolean tmp = true;
            Node cur = where[i];

            int nx = cur.x + dx[cur.d];
            int ny = cur.y + dy[cur.d];

            if(nx < 1 || nx > n || ny < 1 || ny > n)
            {
                tmp = moveB(cur.x,cur.y,i);
            }

            else if(board[nx][ny] == 0)
            {
               tmp =  moveW(cur.x,cur.y,nx,ny,i);
            }

            else if(board[nx][ny] == 1){
               tmp =  moveR(cur.x,cur.y,nx,ny,i);
            }

            else if(board[nx][ny] == 2){
               tmp =  moveB(cur.x,cur.y,i);
            }

            if(!tmp)
                return false;
        }

        return true;
    }

    static int changeD(int d)
    {
        if(d == 1)
            return 2;
        if(d == 2)
            return 1;
        if(d == 4)
            return 3;

        return 4;
    }

    static boolean moveR(int x,int y,int nx,int ny,int num)
    {
        ArrayList<Integer> tmp = new ArrayList<>();
        Stack<Integer> q = new Stack<>();

        boolean start = false;
        for(int i=0;i<map[x][y].size();i++)
        {
            int cur = map[x][y].get(i);

            if(cur == num)
            {
                start = true;
            }

            if(start)
            {
                q.add(cur);
                where[cur] = new Node(nx,ny,where[cur].d);
            }

            else{
                tmp.add(cur);
            }
        }

        while(!q.isEmpty())
        {
            map[nx][ny].add(q.pop());
        }

        map[x][y] = tmp;

        if(map[nx][ny].size() >= 4)
            return false;

        return true;
    }

    static boolean moveW(int x,int y,int nx,int ny,int num)
    {
        ArrayList<Integer> tmp = new ArrayList<>();
        boolean start = false;
        for(int i=0;i<map[x][y].size();i++)
        {
            int cur = map[x][y].get(i);

            if(cur == num)
            {
                start = true;
            }

            if(start)
            {
                map[nx][ny].add(cur);
                where[cur] = new Node(nx,ny,where[cur].d);
            }

            else{
                tmp.add(cur);
            }
        }

        map[x][y] = tmp;

        if(map[nx][ny].size() >= 4)
            return false;

        return true;
    }

    static boolean moveB(int x,int y,int num){
        int cd = changeD(where[num].d);
        int n2x = x + dx[cd];
        int n2y = y + dy[cd];

        if(n2x < 1 || n2x > n || n2y < 1 || n2y > n)
        {
            where[num] = new Node(where[num].x,where[num].y,cd);
        }

        else if(board[n2x][n2y] == 2)
        {
            where[num] = new Node(where[num].x,where[num].y,cd);
        }

        else{
            where[num] = new Node(where[num].x,where[num].y,cd);

            if(board[n2x][n2y] == 0)
            {
                boolean tmp = moveW(x,y,n2x,n2y,num);
                return tmp;
            }

            else if(board[n2x][n2y] == 1)
            {
                boolean tmp = moveR(x,y,n2x,n2y,num);
                return tmp;
            }
        }

        return true;
    }

    static void print(){
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(map[i][j].size()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Node
{
    int x;
    int y;
    int d;

    Node(int x,int y,int d)
    {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
