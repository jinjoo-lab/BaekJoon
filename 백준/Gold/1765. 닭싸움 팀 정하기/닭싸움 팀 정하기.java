
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static boolean[][] friend = new boolean[1001][1001];
    static boolean[][] enemy = new boolean[1001][1001];
    static int[] visit = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            String first = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(first.equals("E"))
            {
                enemy[x][y] = true;
                enemy[y][x] = true;
            }

            else if(first.equals("F"))
            {
                friend[x][y] = true;
                friend[y][x] = true;
            }
        }
        for(int i=1;i<=n;i++)
        {
            eset(i);
        }
        int team = 1;
        for(int i=1;i<=n;i++)
        {
            if(visit[i]==0)
            {
                search(i,team);
                team = team + 1;
            }
        }
        System.out.println(team-1);
        br.close();
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            System.out.print(visit[i]+" ");
        }
        System.out.println();
    }
    static void print2()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(friend[i][j]||friend[j][i])
                    System.out.print("F ");
                else
                    System.out.print("N ");
            }
            System.out.println();
        }
    }
    static void eset(int x)
    {
        for(int i=1;i<=n;i++)
        {
            if(i==x)
                continue;

            if(enemy[x][i])
            {
                for(int j=1;j<=n;j++)
                {
                    if(j==x||j==i)
                        continue;

                    if(enemy[j][i]||enemy[i][j])
                    {
                        friend[x][j] = true;
                        friend[j][x] = true;
                    }
                }
            }
        }
    }
    static void search(int x,int g)
    {
        if(x>n)
            return;

        visit[x] = g;

        for(int i=1;i<=n;i++) {
            if (i == x)
                continue;

            if (friend[x][i]&&visit[i]==0)
            {
                visit[i] = g;
                search(i,g);
            }
        }
    }
}