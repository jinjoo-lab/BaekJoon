import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] data;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n+1];
        tree = new int[4*n + 1];
        for(int i=1;i<=n;i++)
        {
            data[i] = Integer.parseInt(br.readLine());
        }

        makeST(1,n,1);

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int tmp = find(1,n,a,b,1);
            sb.append(tmp+"\n");
        }

        System.out.println(sb);

        br.close();
    }

    static int makeST(int left,int right,int index)
    {
        if(left == right)
        {
            tree[index] = data[left];
            return tree[index];
        }

        int mid = (left + right) / 2;

        tree[index] = Math.min(makeST(left,mid,index*2) , makeST(mid+1,right,index*2 +1));
        return tree[index];
    }

    static int find(int start,int end,int left,int right,int index)
    {
        if(start > right || end < left)
            return Integer.MAX_VALUE;

        if(start >= left && end <= right)
            return tree[index];

        int mid = (start + end) / 2;

        int tl = find(start,mid,left,right,index*2);
        int tr = find(mid+1,end,left,right,index*2+1);

        return Math.min(tl,tr);

    }
}