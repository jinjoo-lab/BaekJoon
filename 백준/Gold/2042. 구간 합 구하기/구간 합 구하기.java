import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static long[] data;

    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        data = new long[n+1];
        tree = new long[4*n + 1];

        for(int i=1;i<=n;i++)
        {
            data[i] = Long.parseLong(br.readLine());
        }

        makeST(1,n,1);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m+k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");

            int f = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(f == 1)
            {
                update(1,n,1,a,b - data[a]);
                data[a] = b;
            }

            else{
                long result = getV(1,n,1,a,(int)b);
                sb.append(result+"\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    static long makeST(int left,int right,int index)
    {
        if(left == right)
        {
            tree[index] = data[left];
            return tree[index];
        }

        int mid = (left + right) / 2;

        tree[index] = makeST(left,mid,index*2) + makeST(mid+1,right,index*2 + 1);

        return tree[index];
    }

    static long getV(int start,int end,int index,int left,int right)
    {
        if(end < left || start > right )
            return 0;

        if(start >= left && end <= right)
            return tree[index];

        int mid = (start + end) / 2;

        return getV(start,mid,index*2,left,right) + getV(mid+1,end,index*2 + 1,left,right);
    }

    static void update(int left,int right, int index,int tI , long tV )
    {
        if(tI < left || tI > right)
            return;

        tree[index] += tV;

        if(left == right)
            return;

        int mid = (left + right) / 2;

        update(left,mid,index*2,tI,tV);
        update(mid+1,right,index*2 +1,tI,tV);

    }
}