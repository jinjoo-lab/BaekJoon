import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static ArrayList<ArrayList<Integer>> board= new ArrayList<>();
    static boolean[] visit = new boolean[2001];
    static boolean result = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++)
        {
            board.add(new ArrayList<>());
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board.get(x).add(y);
            board.get(y).add(x);
        }

        for(int i=0;i<n;i++)
        {
            if(!result)
            {
                visit[i] = true;
                dfs(i,0);
                visit[i] = false;
            }
        }

        if(result)
        {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
        br.close();
    }

    static void dfs(int x,int num)
    {
        if(result)
            return;

        if(x>n-1)
            return;

        if(num==4)
        {
            result = true;
            return;
        }

        for(int y : board.get(x))
        {
            if(!visit[y])
            {
                visit[y] = true;
                dfs(y,num+1);
                visit[y] = false;
            }
        }
    }
}