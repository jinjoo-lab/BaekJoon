import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static char[] arr;

    static HashMap<Character,Integer> map = new HashMap();

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = br.readLine().toCharArray();


        int l = 0;
        int r = 0;

        while(l <= r && r < arr.length) {
            char next = arr[r];

            if(!map.containsKey(next)) {
                map.put(next,1);
            }else {
                map.put(next,map.get(next) + 1);
            }

            if(map.size() <= n) {
                result = Math.max(result, r - l + 1);
            }else {
                while(map.size() > n) {
                    int nextNum = map.get(arr[l]) - 1;

                    if(nextNum == 0) {
                        map.remove(arr[l]);
                    }else {
                        map.put(arr[l],nextNum);
                    }

                    l++;
                }
            }

            r++;
        }

        System.out.println(result);

        br.close();
    }
}
