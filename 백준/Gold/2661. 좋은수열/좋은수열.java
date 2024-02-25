
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static boolean find = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());


        go(0,"");
        br.close();
    }

    static void go(int idx,String cur){

        
        
        if(idx == n){
            if(find)
                return;

            find = true;
            System.out.println(cur);
            return;
        }

        for(int i = 1  ; i <= 3 ; i++){
            if(isCan(cur+i)){

                if(find)
                    return;
                
                go(idx+1,cur+i);
            }
        }

    }

    static boolean isCan(String s){
        int len = s.length() / 2;

        for(int i = 1 ; i <= len ; i++){
            if(s.substring(s.length() - i).equals(s.substring(s.length() - 2*i,s.length() - i))){
                return false;
            }
        }

        return true;
    }
}
