import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static long[] data;
    static int[] tree;

    static long max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            data = new long[n + 1];
            tree = new int[(n + 1) * 4];

            for (int i = 1; i <= n; i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            makeST(1, n, 1);
            long max = findMax(1,n);
            sb.append(max + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int makeST(int start, int end, int idx) {
        if (start == end) {
            return tree[idx] = start;
        }

        int mid = (start + end) / 2;

        int left = makeST(start, mid, idx * 2);
        int right = makeST(mid + 1, end, idx * 2 + 1);

        if(data[left] < data[right]){
            return tree[idx] = left;
        }

        else{
            return tree[idx] = right;
        }
    }

    static int findIdx(int start,int end,int idx,int left,int right){
        if(end < left || start > right)
            return -1;

        if(start >= left && end <= right)
            return tree[idx];

        int mid = (start + end) / 2;

        int tmp1 = findIdx(start,mid,idx*2,left,right);
        int tmp2 = findIdx(mid+1,end,idx*2+1,left,right);

        if(tmp1 == -1)
            return tmp2;

        else if(tmp2 == -1)
            return tmp1;

        else if(data[tmp1] <= data[tmp2])
            return tmp1;

        else
            return tmp2;
    }
    static long findMax(int start,int end){
        int tmpIdx = findIdx(1,n,1,start,end);
        long tmp1 = (end - start + 1) * data[tmpIdx];

        if(start <= tmpIdx -1) {
            long tmp2 = findMax(start, tmpIdx -1);
            tmp1 = Math.max(tmp1,tmp2);
        }
        if(tmpIdx + 1 <= end) {
            long tmp3 = findMax(tmpIdx + 1, end);
            tmp1 = Math.max(tmp1,tmp3);
        }
        return tmp1;
    }


}