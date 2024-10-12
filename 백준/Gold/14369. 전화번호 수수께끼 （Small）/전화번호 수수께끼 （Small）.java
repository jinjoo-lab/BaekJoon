import java.io.*;
import java.util.*;
import java.io.BufferedReader;

public class Main {

    static boolean keep;
    static int tt;
    static int[] num;
    static String result;

    static char[][] alpha = {
            {'Z', 'E', 'R', 'O'},
            {'O', 'N', 'E'},
            {'T', 'W', 'O'},
            {'T', 'H', 'R', 'E', 'E'},
            {'F','O','U','R'},
            {'F','I','V','E'},
            {'S','I','X'},
            {'S','E','V','E','N'},
            {'E','I','G','H','T'},
            {'N','I','N','E'}
    };

    static int[][] alphaCount = new int[10][27];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tt = Integer.parseInt(br.readLine());

        for(int i = 0 ; i <= 9 ; i++) {
            for(int j = 0 , size = alpha[i].length; j < size ; j++) {
                alphaCount[i][alpha[i][j] - 'A']++;
            }
        }

        for(int t = 1 ; t <= tt ; t++) {
            num = new int[27];
            keep = false;

            char[] arr = br.readLine().toCharArray();
            for(int j = 0 ; j < arr.length ; j++) {
                num[arr[j] - 'A']++;
            }

            go("");

            sb.append("Case "+"#"+t+": "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isIt() {
        for(int i = 0 ; i<= 26 ; i++) {
            if(num[i] != 0)
                return false;
        }

        return true;
    }

    static void go(String cur) {

        if(keep)
            return;

        if(isIt()) {
            keep = true;
            result = cur;
            return;
        }

        for(int i = 0 ; i < 10 ; i++) {
            if(can(i)) {
                minus(i);
                go(cur+i);
                plus(i);
            }
        }
    }

    static void minus(int idx) {
        for(int i = 0 ; i <= 26 ; i++) {
            num[i] -= alphaCount[idx][i];
        }
    }

    static void plus(int idx) {
        for(int i = 0 ; i <= 26 ; i++) {
            num[i] += alphaCount[idx][i];
        }
    }

    static boolean can(int idx) {
        for(int i = 0 ; i <= 26 ; i++) {
            if(alphaCount[idx][i] == 0)
                continue;

            if(num[i] < alphaCount[idx][i]) {
                return false;
            }
        }
        return true;
    }
}
