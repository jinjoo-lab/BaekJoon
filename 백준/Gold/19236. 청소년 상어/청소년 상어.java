import java.io.*;
import java.util.*;
public class Main {
	static int[] dy= {0,0,-1,-1,-1,0,1,1,1};
	static int[] dx= {0,-1,-1,0,1,1,1,0,-1};
	static ArrayList<Integer> count= new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int[][] fish=new int[4][4];
		int[][] direct= new int[4][4];
		ArrayList<Integer> order= new ArrayList<>();
		int idx1=0;
		int idx2=0;
		int result=0;
		for(int i=0;i<4;i++)
		{
			String[] line=br.readLine().split(" ");
			for(int j=0;j<8;j++)
			{
				if(j%2==0)
				{
					fish[i][idx1++]=Integer.parseInt(line[j]);
					order.add(fish[i][idx1-1]);
				}
				else
				{
					direct[i][idx2++]=Integer.parseInt(line[j]);
				}
			}
			idx1=0;
			idx2=0;
		}
		Collections.sort(order);
		result+=fish[0][0];
		order.remove(fish[0][0]-1);
		fish[0][0]=-1;
		dfs(0,0,fish,direct,order,result);
		Collections.sort(count,Collections.reverseOrder());
		System.out.println(count.get(0));
		br.close();
	}
	static void dfs(int x,int y,int[][] fish,int[][] direct,ArrayList<Integer> order,int result)
	{
		count.add(result);
		
		int[][] copy= new int[4][4];
		int[][] copy2=new int[4][4];
		ArrayList<Integer> copy3= new ArrayList<>();
		a_copy(copy,fish);
		a_copy(copy2,direct);
		copy3.addAll(order);
		
		f_move(fish,direct,order);
		
		for(int i=1;i<4;i++)
		{
			int nx=x+(dx[direct[x][y]]*i);
			int ny=y+(dy[direct[x][y]]*i);
			if((nx>=0&&nx<4&&ny>=0&&ny<4)&&fish[nx][ny]!=0)
			{
				
				int nam=fish[nx][ny];
			
				result+=nam;
				
				order.remove(order.indexOf(fish[nx][ny]));
				fish[x][y]=0;
				fish[nx][ny]=-1;
				dfs(nx,ny,fish,direct,order,result);
				
				result-=nam;
				fish[x][y]=-1;
				fish[nx][ny]=nam;
				order.add(nam);
				Collections.sort(order);
			}
		}
		a_copy(fish,copy);
		a_copy(direct,copy2);
	}
	static void a_copy(int[][] fish1,int[][] fish2)
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				fish1[i][j]=fish2[i][j];
			}
		}
	}
	static point find(int[][] fish,int k)
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(fish[i][j]==k)
				{
					return new point(i,j);
				}
			}
		}
		return new point(-1,-1);
	}
	static void f_move(int[][] fish,int[][] direct,ArrayList<Integer> order)
	{
		for(int i=0;i<order.size();i++)
		{
			point cur=find(fish,order.get(i));
			if(cur.x==-1&&cur.y==-1)
				continue;
			int tmp_x=cur.x+dx[direct[cur.x][cur.y]];
			int tmp_y=cur.y+dy[direct[cur.x][cur.y]];
			int tmp_fish;
			int swift=rotate(direct[cur.x][cur.y]);
			if(!(tmp_x>=0&&tmp_x<4&&tmp_y>=0&&tmp_y<4)||fish[tmp_x][tmp_y]==-1)
			{
				while(swift!=direct[cur.x][cur.y])
				{
					tmp_x=cur.x+dx[swift];
					tmp_y=cur.y+dy[swift];
					if(tmp_x>=0&&tmp_x<4&&tmp_y>=0&&tmp_y<4)
					{
						if(fish[tmp_x][tmp_y]!=-1)
						{
							direct[cur.x][cur.y]=swift;
							swift=-100;
							break;
						}
					}
					swift=rotate(swift);
					
				}
			}
			if(swift!=direct[cur.x][cur.y])
			{
				int tmp_direct=direct[tmp_x][tmp_y];
				tmp_fish=fish[tmp_x][tmp_y];
				
				fish[tmp_x][tmp_y]=fish[cur.x][cur.y];
				direct[tmp_x][tmp_y]=direct[cur.x][cur.y];
			
				fish[cur.x][cur.y]=tmp_fish;
				direct[cur.x][cur.y]=tmp_direct;
			}
			
		}
	}
	static int rotate(int i)
	{
		if(i==8)
			return 1;
		else
			return i+1;
	}
	static void print(int[][] arr)
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}
		System.out.println();
	}	
}

class point
{
	int x;
	int y;
	int direction;
	point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	point(int x,int y,int direction)
	{
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
}