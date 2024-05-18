import java.util.*;
import java.io.*;

public class Main {

    static long s;
    static long e;

    static char[] op = {'*','+','-','/'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        s = Long.parseLong(st.nextToken());
        e = Long.parseLong(st.nextToken());

        String result = "0";

        if(s != e) {
            result = go();
        }

        System.out.println(result);
        br.close();
    }

    static String go(){
        String result = "-1" ;
        Queue<Node> q = new ArrayDeque<>();
        HashSet<Long> set = new HashSet<>();
        set.add(s);
        q.add(new Node(s,""));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.cur == e) {
                result = cur.opList;
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                if(i == 3 && cur.cur == 0l)
                    continue;

                long next = cal(cur.cur,op[i]);
                
                if(next > 1000000000)
                    continue;

                if(!set.contains(next)) {
                    set.add(next);
                    q.add(new Node(next,cur.opList+op[i]));
                }
            }
        }

        return result;
    }

    static long cal(long cur, char op) {
        if(op == '+')
            return cur + cur;

        else if(op == '-')
            return cur - cur;

        else if(op == '*')
            return cur * cur;

        else
            return cur / cur;
    }

    static class Node{
        long cur;
        String opList;

        Node(long cur,String opList) {
            this.cur = cur;
            this.opList = opList;
        }
    }
}
