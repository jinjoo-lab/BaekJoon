import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        for(int i = 0 ; i < n - 1 ; i++){
            for(int j = i+1; j < n ; j++){
                int tmpSum = board[i] + board[j];

                find(i,j,tmpSum);
            }
        }

        System.out.println(result);

        br.close();
    }

    static int result = Integer.MAX_VALUE;
    static void find(int tmpX,int tmpY,int tmpSum){

        int l = 0;
        int r = n - 1;

        while(l < r){
            while(l == tmpX || l == tmpY){
                l++;
            }

            while(r == tmpY || r == tmpX){
                r--;
            }

            if(l >= r){
                break;
            }

            int tmp = board[l] + board[r];

            if(tmp == tmpSum){
                result = 0;
                break;
            }else if(tmp > tmpSum){
                result = Math.min(result, Math.abs(tmp - tmpSum));
                r--;
            }else{
                result = Math.min(result, Math.abs(tmp - tmpSum));
                l++;
            }
        }
    }


    static void print(){
        for(int i = 0 ; i <  n ; i++){
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
}
