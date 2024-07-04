import java.util.*;
import java.io.*;

public class Main {

    static Queue<Integer> aQ = new ArrayDeque<>();
    static Queue<Integer> bQ = new ArrayDeque<>();

    static char arr[];

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();

        for(int i = 0 , size = arr.length ; i < size ; i++) {

            if(arr[i] == 'C') {
                if(!bQ.isEmpty() && bQ.peek() < i) {
                    bQ.poll();
                    result++;
                }
            }else if(arr[i] == 'B'){
                bQ.add(i);
            }else {
                aQ.add(i);
            }

        }

        while(!aQ.isEmpty() && !bQ.isEmpty()){
            if(aQ.peek() < bQ.peek()) {
                aQ.poll();
                bQ.poll();
                result++;
            }else {
                bQ.poll();
            }
        }

        System.out.println(result);

        br.close();
    }
}
