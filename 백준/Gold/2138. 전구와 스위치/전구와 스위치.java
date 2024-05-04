import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int[] first;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        first = new int[n+1];
        result = new int[n+1];

        char[] tmp = br.readLine().toCharArray();
        for(int i = 1 ; i <= n ; i++){
            first[i] = tmp[i-1] - '0';
        }

        tmp = br.readLine().toCharArray();
        for(int i = 1 ; i <= n ; i++){
            result[i] = tmp[i-1] - '0';
        }

        int result = go(true);


        result = Math.min(result,go(false));

        if(result == Integer.MAX_VALUE)
            result = -1;

        System.out.println(result);

        br.close();
    }

    static int go(boolean isFirst){
        int count = 0;
        int[] tmpArr = copy();

        if(isFirst){
            changeAll(1,tmpArr);
            count++;
        }


        for(int i = 2 ; i <= n ; i++){
            if(tmpArr[i-1] != result[i-1]){
                changeAll(i,tmpArr);
                count++;
            }
        }

        count = isIt(tmpArr) ? count : Integer.MAX_VALUE;

        return count;
    }

    static boolean isIt(int[] arr){
        for(int i = 1 ; i <= n ; i++){
            if(arr[i] != result[i])
                return false;
        }

        return true;
    }

    static void print(int[] arr){
        for(int i = 1 ;  i <= n ; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    static int[] copy(){
        int[] tmpArr = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            tmpArr[i] = first[i];
        }
        return tmpArr;
    }

    static void changeAll(int idx,int[] arr){
        if(idx == 1){
            change(idx,arr);
            change(idx + 1,arr);
        }else if(idx == n){
            change(idx-1,arr);
            change(idx,arr);
        }else{
            change(idx-1,arr);
            change(idx,arr);
            change(idx+1,arr);
        }
    }


    static void change(int idx,int[] arr){
        int next = arr[idx] == 0 ? 1 : 0;
        arr[idx] = next;
    }
}
