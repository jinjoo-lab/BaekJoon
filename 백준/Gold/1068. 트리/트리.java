import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int target = 0;
    static int[] p;

    static boolean[] visit;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        p = new int[n+1];
        visit = new boolean[n+1];

        ArrayList<Integer>[] tree = new ArrayList[n+1];
        for(int i=0;i<n;i++)
        {
            tree[i] = new ArrayList<>();
        }

        int root = 0;
        for(int i=0;i<n;i++)
        {
            p[i] = Integer.parseInt(st.nextToken());

            if(p[i] == -1) {
                root = i;
                continue;
            }

            tree[p[i]].add(i);
        }
        st = new StringTokenizer(br.readLine(), " ");
        target = Integer.parseInt(st.nextToken());

        visit[target] = true;
        Fs(target , tree);
        Ss(root,tree);

        System.out.println(result);

        br.close();
    }

    static void Fs(int cur,ArrayList<Integer>[] tree)
    {
        for(int next : tree[cur])
        {
            if(!visit[next])
            {
                visit[next] = true;
                Fs(next,tree);
            }
        }
    }

    static void Ss(int cur,ArrayList<Integer>[] tree)
    {
        if(visit[cur])
            return;

        if(tree[cur].size() == 0)
        {
            result = result + 1;
            return;
        }

        int count = 0;
        for(int next : tree[cur])
        {
            if(visit[next]) {
                count = count + 1;
                continue;
            }

            Ss(next,tree);
        }

        if(count == tree[cur].size())
        {
            result = result + 1;
        }
    }

    static void print()
    {
        for(int i=0;i<n;i++)
        {
            if(visit[i])
                System.out.print(1+" ");
            else
                System.out.print(0+" ");
        }
        System.out.println();
    }
}
