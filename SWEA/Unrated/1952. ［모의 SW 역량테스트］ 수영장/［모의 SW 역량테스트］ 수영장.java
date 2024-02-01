import java.util.*;
import java.io.*;

public class Solution {
    static int t = 0;
    static int[] money = new int[4];
    static int[] month = new int[13];

    static boolean[] v = new boolean[13];

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {

            v = new boolean[13];

            st = new StringTokenizer(bf.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }

            result = money[3];

            st = new StringTokenizer(bf.readLine(), " ");
            for (int i = 1; i <= 12; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }

            travel(1,0);
            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);

        bf.close();
    }

    static void travel(int curMonth, int curMoney) {
        if(curMonth > 12){
            result = Math.min(result,curMoney);
            return;
        }

        if (month[curMonth] == 0) {
            v[curMonth] = true;
            travel(curMonth + 1, curMoney);
        } else {
            if (!v[curMonth]) {
                v[curMonth] = true;
                int tmpMoney = Math.min(money[0] * month[curMonth], money[1]);
                travel(curMonth + 1, curMoney + tmpMoney);
                v[curMonth] = false;

                if (isPossible(curMonth)) {
                    for (int i = 0; i <= 2; i++) {

                        if(curMonth + i > 12)
                            continue;

                        v[curMonth + i] = true;
                    }
                    travel(curMonth + 3, curMoney + money[2]);
                    for (int i = 0; i <= 2; i++) {

                        if(curMonth + i > 12)
                            continue;

                        v[curMonth + i] = false;
                    }
                }
            } else{
                travel(curMonth + 1,curMoney);
            }
        }
    }

        static boolean isPossible(int curMonth){
            boolean keep = true;
            for(int i=1;i<=2;i++) {
                if(curMonth + i > 12)
                    continue;

                if (month[curMonth + i] > 0) {
                    continue;
                }
                keep = false;
            }

            return keep;
        }
}

