import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        String[] board = new String[n];
        Alpha[] arr = new Alpha[26];

        for(int i=0;i<26;i++)
        {
            arr[i] = new Alpha((char)(i+'A'),0);
        }

        for(int i=0;i<n;i++)
        {
            String line = br.readLine();
            board[i] = line;

            for(int j=0;j<board[i].length();j++)
            {
                arr[board[i].charAt(j) - 'A'] = new Alpha(board[i].charAt(j),arr[board[i].charAt(j) - 'A'].num + (long)Math.pow(10,board[i].length() -(j+1)));
            }
        }

        Arrays.sort(arr,(x,y) ->Long.compare(y.num,x.num));

        int cur = 9;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<10;i++)
        {
            map.put(arr[i].cur,cur);
            cur = cur - 1;
        }

        long result = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<board[i].length();j++)
            {
                char tmp = board[i].charAt(j);
                result += map.get(board[i].charAt(j)) * (long)Math.pow(10,board[i].length() - (j+1));
            }
        }

        System.out.println(result);
        br.close();
    }
}
class Alpha
{
    char cur;
    long num;

    Alpha(char cur,long num)
    {
        this.cur = cur;
        this.num = num;
    }
}