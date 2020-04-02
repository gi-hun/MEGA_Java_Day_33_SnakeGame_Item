/*
 * # ������ũ ����
 * 1. 10x10 �迭�� 0���� ä���.
 * 2. ������ũ�� 1234�� ǥ���Ѵ�.
 * 3. �����¿�� �̵��� �����ϸ�, ������ ����´�.
 * 4. �ڱ���ϰ� �ε�����, ����Ѵ�.
 * 5. �������� �������� ������
 *    �������� ������ ���� 1���� �ڶ���.
 * 6. ������ �ִ� 8������ ������ �� �ִ�.
 */
package day_37;

//�ٽ�) �ε��� 0��°= ���ο� ���� ������.	������ �ε���) ���� �ε����� ���� ������.
//snake.length �Ⱦ��� ���� �����ϱ�

import java.util.Scanner;
import java.util.Random;

public class snake_Item {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		int size=10;		int tail=4;
		int check=-1;		int item=10;
		int[][] map=new int[size][size];
		
		int[] x=new int[4];		int[] y=new int[4];		int[] snake=new int[4];
		
		for(int i=0;i<4;i++)
		{
			x[i]=i;
			map[y[i]][x[i]]=i+1;		//snake �����ش�
			snake[i]=map[y[i]][x[i]];	//snake�� ��ġ
		}
	
		int item_count=0;
		while(item_count<10)
		{
			int num1 = ran.nextInt(10);
			int num2 = ran.nextInt(10);
			if(map[num1][num2]==0)
			{
				map[num1][num2]=item;
			}
			
			item_count++;
		}
		
		while(true)
		{
			if(check==1)
			{
				System.out.println("�ڱ� ���� �ε������ϴ�.");
				System.out.println("������ũ ���� ����");
				break;
			}
			
			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					if(map[i][j]==item)
					{
						System.out.print("I ");
					}
					else
					{
						System.out.print(map[i][j]+" ");
					}
				}
				System.out.println();
			}
			System.out.println();
			
			System.out.print("1)up 2)down 3)left 4)right: ");
			int select = sc.nextInt();
			
			int move_y =0;			int move_x =0;
			
			//ù��° �ε����� �Ű��ָ� ������ �ε������� �� �� �ε����� ���� �޴´�.
			if(select == 1) {move_y = y[0]-1;	move_x = x[0];}				//��
			else if(select == 2) {move_y = y[0]+1;	move_x = x[0];}			//��
			else if(select == 3) {move_y = y[0];	move_x = x[0]-1;}		//��
			else if(select == 4) {move_y = y[0];	move_x = x[0]+1;}		//��
			
			//map ���� �Ѿ����� ����
			if(move_x<0||move_y<0||move_x>=size||move_y>=size) {System.out.println("�Է��Ͻ� �������� �������� ���մϴ�.");continue;}

			//��� ���� ���� ���� -----> if(map[y][x]==item)�� ���� �ɾ���
			if(map[move_y][move_x]==item)
			{
				if(tail>8) {System.out.println("�� �̻� �ڶ��� �����ϴ�.");continue;}
				
				int[] temp_y = y;		int[] temp_x =x;		//���� �ּ� ����Ų��
				y = new int[tail+1];		//���ο� �ּҸ� ����Ų��
				x = new int[tail+1];		//���ο� �ּҸ� ����Ų��
				
				for(int i=0;i<tail;i++)
				{
					y[i]=temp_y[i];			//���ο� �ּҸ� ����Ű��  y�迭�� ���� �� ����Ű�� temp_y �迭 �� ����
					x[i]=temp_x[i];			//���ο� �ּҸ� ����Ű��  y�迭�� ���� �� ����Ű�� temp_y �迭 �� ����
				}
				tail++;
			}
			
			// ȭ�� �ʱ�ȭ+�������϶��� ��ġ �ʱ�ȭ ��Ű�� �ʴ´�.
			for(int i=0; i<size; i++) 
			{
				for(int j=0; j<size; j++) 
				{
					if(map[i][j]==item)
					{
						map[i][j]=item;
					}
					else
					{
						map[i][j] = 0;
					}
				}
			}

			
			// �̵��ϱ�
			// 0��° �ε����� ���� �����ֱ� ������ i=0�� �����Ѵ�
			// ������) ���� �ε����� �� ���ε��� �� ���� ������.
			for(int i=tail-1; i>0; i--)
			{
				y[i] = y[i-1];	x[i] = x[i-1];
			}
			
			y[0] = move_y;
			x[0] = move_x;
			
			//�ڱ� �� ������ �״� ����
			for(int i=1;i<tail;i++)
			{
				if(y[0]==y[i] && x[0]==x[i])
				{
					check=1;
				}
			}
						
			// �� ��ȣ
			for(int i=0; i<tail; i++) 
			{
				map[y[i]][x[i]] = i + 1;
			}
		}
	}
}