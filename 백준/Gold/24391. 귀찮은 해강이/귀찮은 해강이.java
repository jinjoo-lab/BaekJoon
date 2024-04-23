
import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            root[i] = i;
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if(find(v1) != find(v2)){
                union(v1,v2);
            }
        }
        
        for(int i = 1 ; i <= n ; i++){
            find(i);
        }

        int[] board = new int[n];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 0 ; i < n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for(int i = 1 ; i < n ; i++){
            if(find(board[i]) != find(board[i-1]))
                count++;
        }
        System.out.println(count);

        br.close();
    }
    static void print(){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(root[i]+" ");
        }
        System.out.println();
    }
    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
