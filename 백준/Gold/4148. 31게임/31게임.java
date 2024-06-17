import java.util.*;
import java.io.*;

public class Main {

    static char[] arr;

    static String line;
    static int n;
    static int[] num = new int[7];

    static int[][] dp = new int[32][7];
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            line = br.readLine();

            if(line == null)
                break;

            arr = line.toCharArray();
            n = arr.length;
            boolean turn = true;
            result = 31;
            dp = new int[32][7];

            for(int i = 1 ; i <= 6 ; i++) {
                num[i] = 4;
            }

            for(int i = 0 ; i < n ; i++) {
                result -= arr[i] - '0';
                num[arr[i] - '0'] -= 1;
                turn = !turn;
            }

            char re = go(turn,result) == 1 ? 'A' : 'B';
            sb.append(line+" "+re+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static int go(boolean turn,int rest) {
        if(rest == 0) {
            if(turn) {
                return 2;
            }else {
                return 1;
            }
        }

        int result = 0;

        for(int i = 1 ; i <= 6; i ++) {
            if(num[i] >= 1 && rest - i >= 0) {

                int tmpRest = rest - i;
                num[i] -= 1;
                int tmp = go(!turn,tmpRest);
                num[i] += 1;
                if(turn) {
                    if(result == 0 || result == 2) {
                        result = tmp;
                    }
                }else {
                    if(result == 0 || result == 1) {
                        result = tmp;
                    }
                }
            }else {
                if(turn) {
                    if(result == 0 || result == 2) {
                        result = 2;
                    }
                }else {
                    if(result == 0 || result == 1) {
                        result = 1;
                    }
                }
            }
        }

        return result;
    }
}
