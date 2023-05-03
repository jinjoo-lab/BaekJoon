import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<name.length;i++)
        {
            map.put(name[i],yearning[i]);
        }
        
        for(int i=0;i<photo.length;i++)
        {
            String[] line = photo[i];
            int result = 0;
            for(int j=0;j<line.length;j++)
            {
                String cur = line[j];
                if(map.containsKey(cur))
                {
                    result += map.get(cur);
                }
            }
            
            answer[i] = result;
        }
        
        return answer;
    }
}