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
        tree = new long[4*(n+1)];

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int cal = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(cal == 0){
                int min = Math.min(x,y);
                int max = Math.max(x,y);
                long tmp = getSum(1,n,min,max,1);
                sb.append(tmp+"\n");
            }

            else{
                long cv = data[x] - y;
                change(1,n,1,x,cv);
                data[x] = y;
            }
        }

        System.out.print(sb);
        br.close();
    }
    static void print(){
        for(int i=1;i<4*(n+1);i++){
            System.out.print(tree[i]+" ");
        }        System.out.println();
    }
    static long getSum(int start,int end,int left,int right,int idx){
        if(start > right || end < left)
            return 0;

        if(start >= left && end <= right)
            return tree[idx];

        int mid = (start + end) / 2;

        return getSum(start,mid,left,right,idx*2) + getSum(mid+1,end,left,right,idx*2 + 1);
    }

    static void change(int start,int end,int idx,int cidx,long cv){
        if(cidx < start || cidx > end)
            return;

        tree[idx] -= cv;

        if(start == end)
            return;

        int mid = (start + end) / 2;

        change(start,mid,idx*2,cidx,cv);
        change(mid+1,end,idx*2+1,cidx,cv);
    }
}
