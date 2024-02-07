import java.util.*;
import java.io.*;

/*
* Segment Tree
* - Complete Binary Tree
* - 상위 노드는 하위 노드를 관라
* - 합 , 최솟값 , 최댓값
* */

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;
    static long[] data;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        data = new long[n];
        tree = new long[n*4+1];

        for(int i=0;i<n;i++){
            data[i] = Long.parseLong(br.readLine());
        }

        makeST(0,n - 1,1);

        for(int i=1; i <= m + k ;i++){
            st = new StringTokenizer(br.readLine()," ");

            int what = Integer.parseInt(st.nextToken());

            if(what == 1){
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long v = Long.parseLong(st.nextToken());

                data[idx] = v;
                updateV(0,n - 1,1,idx,v);
            }else{
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;

                long tmp = getV(0,n - 1,start,end,1);
                sb.append(tmp+"\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    static long makeST(int left,int right,int idx){
        if(left == right) {
            return tree[idx] = data[left];
        }

        int mid = (left + right) / 2;

        return tree[idx] = (makeST(left,mid,idx*2) * makeST(mid+1,right,idx*2+1) % 1_000_000_007);
    }

    static long getV(int start,int end,int left,int right,int idx){

        if(end < left || start > right)
            return 1l;

        if(start >= left && end <= right)
            return tree[idx];

        int mid = (start + end) / 2;

        return (getV(start,mid,left,right,idx*2) * getV(mid+1,end,left,right,idx*2+1) % 1_000_000_007 );
    }


    static long updateV(int start, int end,int idx,int cIdx, long v){

        if(cIdx < start || cIdx > end)
            return tree[idx];

        if(start == end){
            return tree[idx] = v;
        }

        int mid = (start + end) / 2;

        return tree[idx] = (updateV(start,mid,idx*2,cIdx,v) * updateV(mid+1,end,idx*2+1,cIdx,v) % 1_000_000_007);
    }
}