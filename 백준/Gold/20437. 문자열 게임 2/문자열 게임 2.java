import java.util.*;
import java.io.*;

public class Main {
    static int k = 0;
    static int[] count = new int[27];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int j=1;j<=n;j++) {
            String word = br.readLine();
            k = Integer.parseInt(br.readLine());

            count = new int[27];
            ArrayList<Integer>[] pos = new ArrayList[27];
            for (int i = 0; i < 27; i++) {
                pos[i] = new ArrayList<>();
            }

            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a'] += 1;
                pos[word.charAt(i) - 'a'].add(i);
            }

            int max = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 27; i++) {
                if (count[i] >= k)
                {
                    int size = count[i];
                    int l = 0;
                    int r = 0;

                    while(l < size && r < size)
                    {
                        if(r - l +1 == k)
                        {
                            min = Math.min(min , pos[i].get(r) - pos[i].get(l) +1);
                            max = Math.max(max,pos[i].get(r) - pos[i].get(l) +1);

                            l = l+1;
                        }

                        else if(r-l+1 < k)
                        {
                            r = r + 1;
                        }
                    }
                }
            }

            if(max == -1)
            {
                sb.append(-1+"\n");
            }

            else
            {
                sb.append(min+" "+max+"\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}