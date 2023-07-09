import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<point> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.l == y.l)
                        return x.s - y.s;
                    else
                        return x.l - y.l;
                }
        );

        for(int i=0;i<n;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            pq.add(new point(s,l));
        }

        int tmpl = -1;
        int count = 0;
        while(!pq.isEmpty())
        {
            point cur = pq.poll();

            if(tmpl <= cur.s)
            {
                tmpl = cur.l;
                count = count + 1;
            }
        }

        System.out.println(count);
        br.close();
    }
}
class point
{
    int s;
    int l;

    point(int s,int l)
    {
        this.s = s;
        this.l = l;
    }
}
