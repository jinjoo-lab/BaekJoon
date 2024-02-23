
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int d = 0;
    static int k = 0;
    static int c = 0;

    static int[] board;

    static HashMap<Integer,Integer> set = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        for(int i=1 ; i<=n ; i++){
            board[i] = Integer.parseInt(br.readLine());
        }


        int l = 1;
        int r = k;
        int max = 0;

        for(int i = 1 ; i <= k ;i++){
            put(board[i]);
            max = Math.max(max , set.containsKey(c) ? set.size() : set.size() + 1);
        }

        while( l <= n){
            remove(board[l]);
            l += 1;
            int nextR = nextR(r);
            put(board[nextR]);

            max = Math.max(max , set.containsKey(c) ? set.size() : set.size() + 1);
            r = nextR;
        }

        System.out.println(max);
        br.close();
    }

    static void put(int data){
        if(set.containsKey(data)){
            set.put(data,set.get(data) + 1);
        }else{
            set.put(data,1);
        }
    }

    static void remove(int data){
        if(set.get(data) == 1){
            set.remove(data);
        }else{
            set.put(data,set.get(data) -1);
        }
    }

    static int nextR(int r){
        if(r >= n)
            return r - n + 1;
        else
            return r + 1;
    }
}
