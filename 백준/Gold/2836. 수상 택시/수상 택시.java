import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static ArrayList<long[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());

            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            if(end < start) {
                list.add(new long[]{start,end});
            }
        }

        Collections.sort(
                list, (x,y) -> {
                    if(x[1] == y[1])
                        return Long.compare(x[0],y[0]);

                    return Long.compare(x[1],y[1]);
                }
        );

        long result = m;
        long ss = list.get(0)[1];
        long ll = list.get(0)[0];

        for(int i = 1, size = list.size() ; i < size ; i++) {
            long start = list.get(i)[1];
            long end = list.get(i)[0];

            if(start <= ll) {
                ll = Math.max(end, ll);
            } else {
                result += 2 * (ll - ss);
                ss = start;
                ll = end;
            }
        }

        result += 2 * (ll - ss);


        System.out.println(result);
        br.close();
    }
}
