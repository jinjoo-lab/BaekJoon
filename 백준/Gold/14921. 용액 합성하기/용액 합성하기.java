import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static ArrayList<Integer> minus,plus;
    static int ms,ps,zc;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        minus = new ArrayList<>();
        plus = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine()," ");

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp < 0) {
                minus.add(tmp);
            }else if(tmp > 0) {
                plus.add(tmp);
            }else if (tmp == 0) {
                zc++;
            }
        }

        Collections.sort(minus,(x,y) -> y - x);;

        if(zc >= 2) {
            System.out.println(0);
        }else {
            int ml = 0;
            int pl = 0;
            int ms = minus.size();
            int ps = plus.size();
            int result = Integer.MAX_VALUE;

            if(zc == 1) {
                if(ps >= 1) {
                    if(Math.abs(result) > Math.abs(plus.get(0))) {
                        result = plus.get(0);
                    }
                }

                if(ms >= 1) {
                    if(Math.abs(result) > Math.abs(minus.get(0))) {
                        result = minus.get(0);
                    }
                }

            }

            if(ps >= 2) {
                if(Math.abs(result) > Math.abs(plus.get(0) + plus.get(1))) {
                    result = plus.get(0) + plus.get(1);
                }
            }

            if(ms >= 2) {
                if(Math.abs(result) > Math.abs(minus.get(0) + minus.get(1))) {
                    result = minus.get(0) + minus.get(1);
                }
            }

            while(pl < ps && ml < ms) {
                int curP = plus.get(pl);
                int curM = minus.get(ml);

                if (Math.abs(result) > Math.abs(curP + curM)) {
                    result = curP + curM;
                }

                if (curP + curM > 0) {
                    ml++;
                } else if (curP + curM < 0) {
                    pl++;
                } else {
                    break;
                }
            }
            System.out.println(result);
        }

        br.close();
    }
}
