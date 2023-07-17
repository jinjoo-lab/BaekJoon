import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int[] board = new int[n+1];

        for(int i = 1;i<=n;i++)
        {
            board[i] = Integer.parseInt(br.readLine());
        }

        Stack<Point> stack = new Stack<>();
        long result = 0;
        for(int i= 1 ; i <=n ;i ++)
        {

            while(!stack.isEmpty())
            {
                Point cur= stack.pop();

                if(cur.h > board[i])
                {
                    result = result + stack.size()+1;
                    stack.push(cur);
                    break;
                }
            }

            stack.push(new Point(i,board[i]));
        }

        System.out.println(result);
        br.close();
    }
}
class Point
{
    int idx;
    int h;

    Point(int idx,int h)
    {
        this.idx = idx;
        this.h = h;
    }
}