import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Node[] arr = new Node[n];
        String[] simple = new String[n];

        for(int i=0;i<n;i++)
        {
            String line = br.readLine();
            arr[i] = new Node(line,i);
            simple[i] = line;
        }

        Arrays.sort(arr,(x,y) -> x.word.compareTo(y.word));
        int[] count = new int[n];


        HashMap<String,PriorityQueue<Integer>> map = new HashMap();

        int max = 0;
        for(int i=1;i<n;i++)
        {
            count[i] = compare(arr[i-1].word,arr[i].word);
            max = Math.max(max,count[i]);

            String tmp = arr[i-1].word.substring(0,count[i]);

            if(!map.containsKey(tmp))
            {
                map.put(tmp,new PriorityQueue<>(
                        (x,y) -> x - y
                ));

                map.get(tmp).add(arr[i-1].idx);
                map.get(tmp).add(arr[i].idx);
            }

            else{
                map.get(tmp).add(arr[i-1].idx);
                map.get(tmp).add(arr[i].idx);
            }
        }

        Set<String> set = map.keySet();

        int s = -1;
        int t = -1;

        for(String cur : set)
        {
            if(max == cur.length()) {
                boolean first = false;

                if(s!= -1 && s < map.get(cur).peek())
                    continue;

                while(!map.get(cur).isEmpty())
                {
                    int tmp = map.get(cur).poll();
                    if(!first) {
                        s = tmp;
                        first = true;
                    }

                    else if(s != tmp && first){
                        t = tmp;
                        break;
                    }
                }
            }
        }

        System.out.println(simple[s]);
        System.out.println(simple[t]);

        br.close();
    }

    static int compare(String a,String b)
    {
        int count = 0;
        int aidx = 0;
        int bidx = 0;

        while(aidx < a.length() && bidx < b.length())
        {
            if(a.charAt(aidx) == b.charAt(bidx))
            {
                count = count + 1;
                aidx = aidx + 1;
                bidx = bidx + 1;
            }

            else{
                return count;
            }
        }

        return count;
    }
}
class Node
{
    String word;
    int idx;

    Node(String word, int idx)
    {
        this.word = word;
        this.idx = idx;
    }
}
