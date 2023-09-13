import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static long[] data;

    static long[] tree;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new long[n+1];
        tree = new long[(n+1)*4];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        makeST(1,n,1);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            long result1 = getV(1,n,Math.min(x,y),Math.max(x,y),1);
            sb.append(result1+"\n");

            long minus = data[c] - v;
            data[c] = v;

            change(1,n,c,1,minus);
        }
        System.out.print(sb);
        br.close();
    }

    static long makeST(int start,int end,int idx){
        if(start == end){
            return tree[idx] = data[start];
        }

        int mid = (start + end) / 2;

        return tree[idx] = makeST(start,mid,idx*2) + makeST(mid+1,end,idx*2+1);
    }

    static long getV(int start,int end,int left,int right,int idx){
        if(start > right || end < left)
            return 0;

        if(start >= left && end <= right)
            return tree[idx];

        int mid = (start + end) / 2;

        return getV(start,mid,left,right,idx*2) + getV(mid+1,end,left,right,idx*2+1);
    }

    static void change(int start,int end,int what,int idx,long v){
        if(what < start || what > end)
            return;

        tree[idx] -= v;

        if(start == end)
        {
            return;
        }


        int mid = (start + end) / 2;

        change(start,mid,what,idx*2,v);
        change(mid+1,end,what,idx*2+1,v);

    }
}