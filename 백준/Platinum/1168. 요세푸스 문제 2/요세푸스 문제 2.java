import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int data[];

    static int tree[];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n+1];
        tree = new int[4*n+1];
        makeST(1,n,1);

        int start = m;

        sb.append("<");
        for(int i= 0;i <n ;i++){

            if(start > tree[1]) {
                if (start % tree[1]  == 0)
                    start = tree[1];
                else
                    start %= tree[1];
            }


            int deleteNum = getV(1,n,1,start);

            if(i < n - 1)
                sb.append(deleteNum+", ");
            else{
                sb.append(deleteNum);
            }
            updateZero(1,n,1,deleteNum);
            start += (m - 1);
        }sb.append(">");

        System.out.println(sb);
        bf.close();
    }
    // listIdx = 가변 인덱스
    static int getV(int start,int end,int idx, int moveIdx){

        if(start == end)
            return start;

        int mid = (start + end) / 2;

        if(tree[2*idx] >= moveIdx){
            return getV(start,mid,idx*2,moveIdx);
        }else{
            return getV(mid+1,end,idx*2+1,moveIdx - tree[2*idx]);
        }
    }

    // deleteNum : 고정 인덱스
    // 고정 인덱스로 가변 삭제
    static void updateZero(int start,int end,int idx,int stayIdx){
        tree[idx] -= 1;

        if(start == end){
            return;
        }

        int mid = (start + end) / 2;

        if(mid >= stayIdx) {
            updateZero(start, mid, idx * 2, stayIdx);
        }else {
            updateZero(mid + 1, end, idx * 2 + 1, stayIdx);
        }
    }

    static int makeST(int start,int end,int idx){

        if(start == end) {
            return tree[idx] = 1;
        }

        int mid = (start + end) / 2;

        return tree[idx] = makeST(start,mid,idx*2) + makeST(mid + 1,end,idx*2+1);
    }

}
