import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] board;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++){
            board[i] = Integer.parseInt(st.nextToken());
            sum += board[i];
        }

        if(sum % 3 == 0){
            long tmp = 0;

            for(int i=0;i<n;i++){
                tmp += board[i] / 2;
            }

            if(tmp >= sum / 3)
                System.out.println("YES");

            else{
                System.out.println("NO");
            }
        }

        else{
            System.out.println("NO");
        }


        br.close();
    }

    static void print()
    {
        for(int i=1;i<=n;i++){
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
}

