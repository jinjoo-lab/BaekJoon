import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();
        char[] s = new char[2*n+1];

        for(int i=0;i<2*n+1;i++){
            if(i % 2 == 0){
                s[i] = 'I';
            }else{
                s[i] = 'O';
            }
        }

        int count = 0;

        for(int i=0;i<m;i++){
            if(arr[i] == 'I'){
                int len = i + 2*n;

                if(len < m){
                    boolean find = true;

                    for(int j = i;j<=len;j++){
                        if(arr[j] != s[j - i]){
                            find = false;
                            break;
                        }
                    }

                    if(find){count += 1;}
                }
            }

        }

        System.out.println(count);
        br.close();
    }

}
