import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int[] board = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        int[] result = new int[n+1];
        Stack<Point> stack = new Stack<>();
        for(int i=1;i<=n;i++)
        {

            while(!stack.isEmpty())
            {
                Point cur = stack.pop();

                if(board[i] <= cur.h)
                {
                    result[i] = cur.idx;
                    stack.push(cur);
                    break;
                }
            }

            stack.push(new Point(i,board[i]));
        }
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n;i++)
        {
           sb.append(result[i]+" ");
        }

        System.out.println(sb);
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