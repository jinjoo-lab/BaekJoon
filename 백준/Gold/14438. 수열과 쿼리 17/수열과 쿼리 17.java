import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static int m = 0;

    static int data[];

    static int tree[];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        data = new int[n+1];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 1; i <= n ;i ++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[4*n+1];

        makeST(1,n,1);

        st = new StringTokenizer(bf.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int what = Integer.parseInt(st.nextToken());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(what == 2){
                int tmp = get(1,n,left,right,1);
                sb.append(tmp+"\n");
            }else{
                update(1,n,left,1,right);
            }
        }

        System.out.print(sb);

        bf.close();
    }

    static void update(int start,int end,int idx,int tIdx,int v){
        if(idx < start | idx > end)
            return ;

        if(start == end){
            tree[tIdx] = data[idx] = v;
            return;
        }

        int mid = (start + end) / 2;

        update(start,mid,idx,tIdx*2,v);
        update(mid+1,end,idx,tIdx*2+1,v);

        tree[tIdx] = Math.min(tree[tIdx*2],tree[tIdx*2+1]);
    }

    static int get(int start,int end,int left,int right,int idx){
        if(start > right || left > end)
            return Integer.MAX_VALUE;

        if(start >= left && end <= right)
            return tree[idx];

        int mid = (start + end) / 2;

        return Math.min(get(start,mid,left,right,idx*2),get(mid+1,end,left,right,idx*2+1));
    }

    static int makeST(int left,int right,int idx){
        if(left == right){
            return tree[idx] = data[left];
        }

        int mid = (left + right) >> 1;

        return tree[idx] = Math.min(makeST(left,mid,idx*2), makeST(mid+1,right,2*idx + 1));
    }


}
