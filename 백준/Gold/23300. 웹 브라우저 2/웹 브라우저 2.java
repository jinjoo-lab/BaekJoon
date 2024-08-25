import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static ArrayDeque<Integer> front = new ArrayDeque<>();
    static ArrayDeque<Integer> back = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int cur = 0;
        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            String next = st.nextToken();

            if(next.equals("A")) {
                front.clear();
                if(cur != 0) {
                    back.addLast(cur);
                }

                cur = Integer.parseInt(st.nextToken());
            }else if(next.equals("B")) {
                if(back.isEmpty()){
                    continue;
                }else {
                    front.addFirst(cur);
                    cur = back.removeLast();
                }
            }else if (next.equals("C")) {
                ArrayDeque<Integer> tmpB = new ArrayDeque<>();

                while(!back.isEmpty()) {
                    int tmpCur = back.removeFirst();

                    if(tmpB.isEmpty()) {
                        tmpB.addLast(tmpCur);
                    } else if (tmpCur != tmpB.peekLast()) {
                        tmpB.addLast(tmpCur);
                    }
                }

                back = tmpB;

            }else {
                if(front.isEmpty()){
                    continue;
                }else {
                    back.addLast(cur);
                    cur = front.removeFirst();
                }
            }


        }

        System.out.println(cur);

        StringBuilder sb = new StringBuilder();

        if(back.isEmpty()){
            sb.append("-1\n");
        }else {
            while(!back.isEmpty()) {
                sb.append(back.removeLast()).append(" ");
            }sb.append("\n");
        }
        System.out.print(sb);

        sb = new StringBuilder();
        if(front.isEmpty()){
            sb.append("-1\n");
        }else {
            while(!front.isEmpty()) {
                sb.append(front.removeFirst()).append(" ");
            }sb.append("\n");
        }
        System.out.print(sb);

        br.close();
    }
}
