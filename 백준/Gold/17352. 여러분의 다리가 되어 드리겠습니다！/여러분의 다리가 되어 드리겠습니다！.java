import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }

        for(int i=1;i<=n-2;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            union(s,e);
        }

        int f = 0;
        int s = 0;

        for(int i=1;i<=n;i++){
            int tmp = find(i);

            if(f == 0){
                f = tmp;
            }

            else if(f != tmp && s == 0){
                s = tmp;
                break;
            }
        }

        System.out.println(f+" "+s);

        br.close();
    }

    static int find(int x){
        if(x == root[x])
            return x;

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;

        else{
            root[x] = y;
        }
    }
}
