import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int[] board = new int[2];

        int cur = Integer.parseInt(line.charAt(0)+"");
        if(cur == 0)
            board[0] = 1;
        else
            board[1] = 1;

        for(int i=1;i<line.length();i++)
        {
             int tmp = Integer.parseInt(line.charAt(i)+"");

             if(tmp != cur)
             {
                 board[tmp] = board[tmp] + 1;
                 cur = tmp;
             }
        }

        System.out.println(Math.min(board[0],board[1]));

        br.close();
    }
}

