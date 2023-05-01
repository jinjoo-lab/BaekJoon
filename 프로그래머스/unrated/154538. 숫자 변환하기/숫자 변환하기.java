import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        int[] visit = new int[1000001];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visit[x] = 1;
        
        while(!q.isEmpty())
        {
            int cur = q.poll();
            if(cur==y)
            {
                answer = visit[cur] - 1;
            }
            
            int[] num = new int[3];
            num[0] = cur + n;
            num[1] = cur*2;
            num[2] = cur*3;
            
            for(int i=0;i<3;i++)
            {
                if(num[i]<=y)
                {
                    if(visit[num[i]]==0||visit[cur]+1<visit[num[i]])
                    {
                        visit[num[i]] = visit[cur] + 1;
                        q.add(num[i]);
                    }
                }
            }
        }
        return answer;
    }
}