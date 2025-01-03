import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;

    static TreeMap<Integer,Integer> map = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map.put(key,value);
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int order = Integer.parseInt(st.nextToken());

            if(order == 1) {
                int key = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                map.put(key,value);
            }else if(order == 2) {
                int key = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                int w = findKey(key);


                if(w == 0) {
                    int nKey = map.higherKey(key);
                    map.put(nKey,value);
                }else if(w == 1) {
                    int nKey = map.lowerKey(key);
                    map.put(nKey,value);
                }else if(w == 3) {
                    map.put(key,value);
                }
            }else {
                int key = Integer.parseInt(st.nextToken());

               int w = findKey(key);

               if(w == -1) {
                   sb.append(-1).append("\n");
               }else if(w == 2) {
                   sb.append("?").append("\n");
               }else if(w == 0) {
                   sb.append(map.get(map.higherKey(key))).append("\n");
               }else if(w == 1) {
                   sb.append(map.get(map.lowerKey(key))).append("\n");
               }else if(w == 3) {
                   sb.append(map.get(key)).append("\n");
               }
            }
        }

        System.out.print(sb);
        br.close();
    }

    static int findKey(int key) {

        if(map.containsKey(key)) {
            return 3;
        }

        int h = map.higherKey(key) == null ? 2000000001 : map.higherKey(key);
        int l = map.lowerKey(key) == null ? 2000000001 : map.lowerKey(key);

        boolean isH = true;
        boolean isL = true;

        int hm = Math.abs(h - key);
        int lm = Math.abs(l - key);

        if(hm > k) {
            isH = false;
        }

        if(lm> k) {
            isL = false;
        }

        if(isH && isL) {
            if(h == l)
                return 0;

            if(hm == lm)
                return 2;

            if(hm > lm)
                return 1;

            if(hm < lm)
                return 0;
        }

        if(isH && !isL) {
            return 0;
        }

        if(!isH && isL) {
            return 1;
        }


        return -1;
    }
}
