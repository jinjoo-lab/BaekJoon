import java.util.*;
import java.io.*;

public class Main {

    static int a,b,c;

    static int[] arrA,arrB,arrC;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arrA = new int[a];
        arrB = new int[b];
        arrC = new int[c];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < a ; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < b ; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < c ; i++) {
            arrC[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);
        Arrays.sort(arrC);

        int result = Integer.MAX_VALUE;

        for(int i = 0 ; i < a ; i++) {
            for(int j = 0 ; j < b ; j++) {

                int tmpA = arrA[i];
                int tmpB = arrB[j];

                if(find(Math.max(tmpA,tmpB),Math.min(tmpA,tmpB),arrC)) {
                    result = Math.min(result,Math.abs(tmpA - tmpB));
                }
            }
        }

        for(int i= 0 ; i < a ; i++) {
            for(int j = 0 ; j < c ; j++) {
                int tmpA = arrA[i];
                int tmpB = arrC[j];

                if(find(Math.max(tmpA,tmpB),Math.min(tmpA,tmpB),arrB)) {
                    result = Math.min(result,Math.abs(tmpA - tmpB));
                }
            }
        }

        for(int i= 0 ; i < b ; i++) {
            for(int j = 0 ; j < c ; j++) {
                int tmpA = arrB[i];
                int tmpB = arrC[j];

                if(find(Math.max(tmpA,tmpB),Math.min(tmpA,tmpB),arrA)) {
                    result = Math.min(result,Math.abs(tmpA - tmpB));
                }
            }
        }


        System.out.println(result);

        br.close();
    }

    static boolean find(int max,int min,int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            if(arr[mid] >= min && arr[mid] <= max) {
                return true;
            }

            if(arr[mid] < min) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        return false;
    }
}
