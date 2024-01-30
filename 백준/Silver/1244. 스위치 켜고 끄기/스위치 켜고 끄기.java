import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];

        st = new StringTokenizer(bf.readLine()," ");

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine()," ");

            int w = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(w == 1){
                reverse(num);
            }else{
                search(num);
            }
        }

        for(int i=1;i<=n;i++){
            sb.append(board[i]+" ");
            
            if(i % 20 == 0){
                sb.append("\n");
            }
        }

        System.out.println(sb);

        bf.close();
    }

    static void search(int num){
        int[] d = {-1,1};
        oneReverse(num);
        int s = num;
        int e = num;

        while(true){

            s -= 1;
            e += 1;

            if(s < 1 || e > n){
                break;
            }

            if(board[s] != board[e]){
                break;
            }

            oneReverse(s);
            oneReverse(e);
        }


    }

    static void oneReverse(int num) {
        if (board[num] == 1){
            board[num] = 0;
            return;
        }

        board[num] = 1;
    }
    static void reverse(int num){

        for(int i = num ; i <= n; i += num){
            if(board[i] == 1){
                board[i] = 0;
            }else
                board[i] = 1;
        }
    }
}