import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int p = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        root = new int[n+1];
        for(int i=1;i<=n;i++){
            root[i] = i;
        }


        int[] plist = new int[p];

        for(int i=1;i<=p;i++){
            int tmp = Integer.parseInt(br.readLine());
            plist[i-1] = tmp;
        }

        int result = 0;
        for(int i=0;i<p;i++){
            int tmp = find(plist[i]);

            if(tmp == 0){
                break;
            }

            result = result + 1;
            union(tmp,tmp-1);

        }

        System.out.println(result);

        br.close();
    }

    static int find(int x){
        if(root[x] == x)
            return root[x];

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
