import java.io.IOException;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.InputStreamReader;
public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n= Integer.parseInt(line[0]);
		int k= Integer.parseInt(line[1]);
		int[] arr= new int[2*n+1];
		line=br.readLine().split(" ");
		for(int i=0;i<2*n;i++)
			arr[i+1]=Integer.parseInt(line[i]);
		System.out.println(step(arr,n,k));
		br.close();
	}
	static int step(int[] arr,int n,int k)
	{
		Queue<Integer> robot= new LinkedList<>();
		Queue<Integer> robot2= new LinkedList<>();
		int[] arr2= new int[2*n+1];
		boolean[] is= new boolean[2*n+1];
		boolean[] is2= new boolean[2*n+1];
		int len=2*n+1;
		int result=1;
		while(true) 
		{
		for(int i=1;i<len;i++)
		{
			arr2[n_idx(i,n)]=arr[i];
			is2[n_idx(i,n)]=is[i]; // 벨트 회전
		}
		while(!robot.isEmpty())
		{
			int num= robot.poll();
			if(n_idx(num,n)==n)
			{
				is2[n]=false;
			}
			else
				robot2.add(n_idx(num,n));
		} // step1
		
		while(!robot2.isEmpty())
		{
			int num= robot2.poll();
			int num2= n_idx(num,n);
			if(is2[num2]==false&&arr2[num2]>0)
			{
				arr2[num2]=arr2[num2]-1;
				if(num2!=n) {
					is2[num2]=true;
					robot.add(num2);
				}
				is2[num]=false;
			}
			else
				robot.add(num);
		} // step2
		
		if(is2[1]==false&&arr2[1]!=0)
		{
			robot.add(1);
			is2[1]=true;
			arr2[1]=arr2[1]-1;
		} // step3
		
		int count=0;
		for(int i=1;i<len;i++)
		{
			if(arr2[i]==0)
				count++;
		}
		if(count>=k)
			return result;
		// step4
		
		arr=Arrays.copyOfRange(arr2, 0, len);
		is=Arrays.copyOfRange(is2, 0,len);
		result++;
		}
	}
	
	static int n_idx(int idx,int n)
	{
		if(idx==2*n)
			return 1;
		else
			return idx+1;
	}
	
}