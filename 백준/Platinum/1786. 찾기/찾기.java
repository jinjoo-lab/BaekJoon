import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static int count = 0;

    static ArrayList<Integer> LIST = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String t = br.readLine();
        String p = br.readLine();

        KMP(t,p);
        sb.append(count+"\n");
        for(int next : LIST){
            sb.append(next+" ");
        }sb.append("\n");


        System.out.print(sb);
        br.close();
    }

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx=0;
        for(int i=1; i<n; i++) {

            while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }

            if(pattern.charAt(i) == pattern.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }
        return table;
    }

    static void KMP(String parent, String pattern) {
        int[] table = makeTable(pattern);

        int n1 = parent.length();
        int n2 = pattern.length();

        int idx = 0;
        for(int i=0; i< n1; i++) {

            while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }


            if(parent.charAt(i) == pattern.charAt(idx)) {
                if(idx == n2-1) {
                    count++;
                    LIST.add(i-idx+1);
                    idx =table[idx];
                }else {
                    idx += 1;
                }
            }
        }
    }
}
