import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static Deque<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            char[] cal = br.readLine().toCharArray();

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(),",[]");
            q = new ArrayDeque<>();
            for(int j=1;j<=m;j++){
                q.add(Integer.parseInt(st.nextToken()));
            }

            boolean dir = true;
            boolean error = false;
            for(int j=0;j<cal.length;j++){
                if(cal[j] == 'R'){
                    dir = !dir;
                }

                else{
                    if(q.isEmpty()){
                        sb.append("error\n");
                        error = true;
                        break;
                    }

                    if(dir){
                        q.removeFirst();
                    }

                    else{
                        q.removeLast();
                    }
                }
            }

            if(!error){
                StringBuilder tmp = new StringBuilder();
                tmp.append('[');
                if(dir){
                    while(!q.isEmpty()){
                        tmp.append(q.removeFirst());

                        if(!q.isEmpty()){
                            tmp.append(",");
                        }
                    }
                }

                else{
                    while(!q.isEmpty()){
                        tmp.append(q.removeLast());

                        if(!q.isEmpty()){
                            tmp.append(",");
                        }
                    }
                }
                tmp.append("]\n");

                sb.append(tmp.toString());
            }
        }

        System.out.print(sb);
        br.close();
    }
}
