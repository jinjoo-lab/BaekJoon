
import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static HashSet<String> isIt = new HashSet<>();
    static HashMap<String,Integer> complete = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        boolean find = false;
        for(int i = 1  ; i <= n ; i++){
            String line = br.readLine();
            find = false;

            for(int  j = 1 ; j <= line.length(); j++){

                String tmp = line.substring(0,j);

                if(!isIt.contains(tmp)){
                    isIt.add(tmp);

                    if(!find){
                        find = true;
                        sb.append(tmp+"\n");
                    }
                }
            }

            if(!complete.containsKey(line)){
                complete.put(line,1);
            }else{
                complete.put(line,complete.get(line) + 1);
            }


            if(!find) {
                int count = complete.get(line);

                if(count == 1){
                    sb.append(line+"\n");
                }else{
                    sb.append(line+count+"\n");
                }
            }
        }

        System.out.print(sb);
        br.close();
    }
}
