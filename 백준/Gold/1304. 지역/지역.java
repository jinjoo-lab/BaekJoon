import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] root;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = 1;

        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if(v1 > v2) {
                for(int j = v1 ; j >= v2 ; j--) {
                    union(v2,j);
                }
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            if(n % i != 0)
                continue;

            boolean tmp = go(i);

            if(tmp) {
                result = Math.max(result,(n / i));
            }
        }

        System.out.println(result);
        br.close();
    }

    static boolean go(int count) {

        for(int i = count ; i <= n ; i += count) {
            if(i + 1 <= n) {
                if(find(i) == find(i + 1))
                    return false;
            }
        }


        return true;
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            System.out.print(find(i)+" ");
        }
        System.out.println();
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
