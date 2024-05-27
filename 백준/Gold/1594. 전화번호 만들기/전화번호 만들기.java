import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static char[] arr;

    static int[] v;
    static String start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        start = br.readLine();
        char[] input = start.toCharArray();

        n = input.length;
        arr = new char[n+1];
        v = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            arr[i] = input[i-1];
            v[i] = -1;
        }

        if(n == 2 || n == 3) {
            System.out.println(start);
        }else{
            go(1,0,"");
            System.out.println(reString);
        }

        br.close();
    }

    static int[] dx = {1,2};

    static String reString;
    static int result = -1;
    static void go(int idx,int num,String cur) {

        if(idx > n) {
            if(result < num){
                result = num;
                reString = cur;
            }
            return;
        }

        if(v[idx] >= num)
            return;

        v[idx] = num;

        for(int i = 0 ; i <= 1 ; i++) {
            int nIdx = idx + dx[i];

            if(nIdx > n)
                break;

            int tmpNum = i == 0 ? calDouble(idx,nIdx) : calTriple(idx,idx+1,idx+2);

            String tmpString = cur + start.substring(idx -1,nIdx);

            if(nIdx < n)
                tmpString += "-";

            go(nIdx + 1,num + tmpNum,tmpString);
        }
    }

    static int calTriple(int sIdx,int dIdx,int lIdx) {
        if(arr[sIdx] == arr[dIdx] && arr[dIdx] == arr[lIdx]) {
            return 2;
        }

        else if(arr[sIdx] == arr[dIdx] || arr[sIdx] == arr[lIdx] || arr[dIdx] == arr[lIdx])
            return 1;

        return 0;
    }

    static int calDouble(int sIdx,int lIdx) {
        if(arr[sIdx] == arr[lIdx])
            return 2;

        return 0;
    }

}
