import java.util.*;
import java.io.*;

public class Main {

    static int n,k;

    static HashSet<Long> set = new HashSet<>();
    static ArrayList<Long> w = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            long cur = Integer.parseInt(st.nextToken());
            w.add(cur);
            set.add(cur);
        }

        int count = 0;
        long dis = 1;
        long result = 0;

        Queue<Long> q = new ArrayDeque<>();

        for(long next : w) {
            q.add(next);
        }

        loop : while(true) {
            int size = q.size();

            while (size > 0) {
                long next = q.poll();
                size--;

                if (!set.contains(next - 1)) {
                    set.add(next - 1);
                    q.add(next - 1);
                    count++;
                    result += dis;
                }

                if(count == k)
                    break loop;

                if (!set.contains(next + 1)) {
                    set.add(next + 1);
                    q.add(next + 1);
                    count++;
                    result += dis;
                }

                if(count == k)
                    break loop;
            }

            dis += 1;
        }

        System.out.println(result);

        br.close();
    }
}
