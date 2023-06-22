import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static boolean[] truth;
    static int[] root;
    static boolean[] true_room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        root = new int[m+1];
        true_room = new boolean[m+1];

        for(int i=1;i<=m;i++)
        {
            root[i] = i;
        }

        st = new StringTokenizer(br.readLine(), " ");

        int num = Integer.parseInt(st.nextToken());
        truth = new boolean[n+1];

        for(int i=1;i<=num;i++)
        {
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        boolean[][] visit = new boolean[n+1][m+1];


        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            num = Integer.parseInt(st.nextToken());

            for(int j=1;j<=num;j++)
            {
                int tmp = Integer.parseInt(st.nextToken());

                if(truth[tmp]){
                    true_room[i] = true;
                }

                visit[tmp][i] = true;
            }
        }
        
        for(int i=1;i<=n;i++)
        {
            int idx = -1 ;
            for(int j=1;j<=m;j++)
            {
                if(visit[i][j])
                {
                    if(idx!=-1)
                        union(idx, j);
                    idx = j;
                }
            }

        }

        int count = 0;
        for(int i=1;i<=m;i++)
        {
            if(!true_room[find(i)])
                count = count + 1;
        }


        System.out.println(count);
        br.close();
    }


    static int find(int x)
    {
        if(root[x] == x)
            return x;

        else
            return root[x] = find(root[x]);
    }

    static void union(int x,int y)
    {
        int nx = find(x);
        int ny = find(y);


        if(nx < ny) {
            root[ny] = nx;
        }
        else{
            root[nx] = ny;
        }

        if(true_room[nx] || true_room[ny])
        {
            true_room[nx] = true;
            true_room[ny] = true;
        }
    }

}

