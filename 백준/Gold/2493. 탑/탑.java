import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int[] board = new int[n+1];
        Stack<Data> stack = new Stack<>() ;

        int idx = n;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++)
        {
            boolean flag = false;

            while(!stack.isEmpty())
            {
                Data cur = stack.pop();

                if(cur.val >= board[i])
                {
                    sb.append(cur.idx+" ");
                    flag = true;
                    stack.push(cur);
                    break;
                }
            }

            if(!flag)
            {
                sb.append(0+" ");
            }

            stack.push(new Data(board[i],i));
        }
        sb.append("\n");
        System.out.println(sb.toString());
        br.close();
    }

}
class Data
{
    int val;
    int idx;

    Data(int val,int idx)
    {
        this.val = val;
        this.idx = idx;
    }
}
