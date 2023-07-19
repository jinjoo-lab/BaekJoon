import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int[] h = new int[n+1];
        int[] num = new int[n+1];
        int[][] s = new int[n+1][2];

        for(int i=1;i<=n;i++)
        {
            s[i][1] = Integer.MAX_VALUE;
        }

        st = new StringTokenizer(br.readLine(), " ");

        for(int i=1;i<=n;i++)
        {
            h[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Point> stack = new Stack<>();

        for(int i=1;i<=n;i++)
        {
            while(!stack.isEmpty())
            {
                Point cur = stack.peek();

                if(cur.h <= h[i])
                {
                    stack.pop();
                }

                else{
                    if(Math.abs(cur.idx - i) < s[i][1])
                    {
                        s[i][0] = cur.idx;
                        s[i][1] = Math.abs(cur.idx - i);
                    }
                    num[i] += stack.size();
                    break;
                }

            }

            stack.add(new Point(i,h[i]));
        }

        stack.clear();

        for(int i=n;i>=1;i--)
        {
            while(!stack.isEmpty())
            {
                Point cur = stack.peek();

                if(cur.h <= h[i])
                {
                    stack.pop();
                }

                else{
                    if(Math.abs(cur.idx - i) < s[i][1])
                    {
                        s[i][0] = cur.idx;
                        s[i][1] = Math.abs(cur.idx - i);
                    }

                    else if(Math.abs(cur.idx - i) == s[i][1])
                    {
                        s[i][0] = Math.min(cur.idx , s[i][0]);
                    }
                    num[i] += stack.size();
                    break;
                }
            }

            stack.add(new Point(i,h[i]));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++) {
           if(num[i] == 0)
                sb.append(0+"\n");

           else
               sb.append(num[i]+" "+s[i][0]+"\n");

        }
        System.out.print(sb);
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