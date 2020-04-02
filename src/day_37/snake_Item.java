/*
 * # 스네이크 게임
 * 1. 10x10 배열을 0으로 채운다.
 * 2. 스네이크는 1234로 표시한다.
 * 3. 상하좌우로 이동이 가능하며, 꼬리가 따라온다.
 * 4. 자기몸하고 부딪히면, 사망한다.
 * 5. 랜덤으로 아이템을 생성해
 *    아이템을 먹으면 꼬리 1개가 자란다.
 * 6. 꼬리는 최대 8개까지 증가할 수 있다.
 */
package day_37;

//핵심) 인덱스 0번째= 새로운 값을 가진다.	나머지 인덱스) 그전 인덱스의 값을 가진다.
//snake.length 안쓰는 이유 생각하기

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
			map[y[i]][x[i]]=i+1;		//snake 보여준다
			snake[i]=map[y[i]][x[i]];	//snake의 위치
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
				System.out.println("자기 몸에 부딪혔습니다.");
				System.out.println("스네이크 게임 종료");
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
			
			//첫번째 인덱스만 옮겨주면 나머지 인덱스들은 그 전 인덱스의 값을 받는다.
			if(select == 1) {move_y = y[0]-1;	move_x = x[0];}				//상
			else if(select == 2) {move_y = y[0]+1;	move_x = x[0];}			//하
			else if(select == 3) {move_y = y[0];	move_x = x[0]-1;}		//좌
			else if(select == 4) {move_y = y[0];	move_x = x[0]+1;}		//우
			
			//map 범위 넘었을때 조건
			if(move_x<0||move_y<0||move_x>=size||move_y>=size) {System.out.println("입력하신 방향으로 움직이지 못합니다.");continue;}

			//계속 오류 났던 이유 -----> if(map[y][x]==item)로 조건 걸었다
			if(map[move_y][move_x]==item)
			{
				if(tail>8) {System.out.println("더 이상 자랄수 없습니다.");continue;}
				
				int[] temp_y = y;		int[] temp_x =x;		//같은 주소 가리킨다
				y = new int[tail+1];		//새로운 주소를 가리킨다
				x = new int[tail+1];		//새로운 주소를 가리킨다
				
				for(int i=0;i<tail;i++)
				{
					y[i]=temp_y[i];			//새로운 주소를 가리키는  y배열에 그전 값 가리키는 temp_y 배열 값 복사
					x[i]=temp_x[i];			//새로운 주소를 가리키는  y배열에 그전 값 가리키는 temp_y 배열 값 복사
				}
				tail++;
			}
			
			// 화면 초기화+아이템일때는 위치 초기화 시키지 않는다.
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

			
			// 이동하기
			// 0번째 인덱스는 값을 정해주기 때문에 i=0을 제외한다
			// 나머지) 현재 인덱스는 그 전인덱스 의 값을 가진다.
			for(int i=tail-1; i>0; i--)
			{
				y[i] = y[i-1];	x[i] = x[i-1];
			}
			
			y[0] = move_y;
			x[0] = move_x;
			
			//자기 몸 먹으면 죽는 조건
			for(int i=1;i<tail;i++)
			{
				if(y[0]==y[i] && x[0]==x[i])
				{
					check=1;
				}
			}
						
			// 뱀 번호
			for(int i=0; i<tail; i++) 
			{
				map[y[i]][x[i]] = i + 1;
			}
		}
	}
}