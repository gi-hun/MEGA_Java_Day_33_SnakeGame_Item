package day_37;

import java.util.Random;
import java.util.Scanner;

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

public class snake_Item_answer {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Random ran = new Random();
		
		int size = 10;
		int[][] map = new int[size][size];
		
		int[] x = new int[4];
		int[] y = new int[4];
		int snakeSize = 4;	
		// 아이템 수량
		int itemCount = 8;
		int itemNum = 9;
		
		for(int i=0; i< snakeSize; i++) {
			x[i] = i;
			map[y[i]][x[i]] = i + 1;			
		}
		
		// 아이템 생성
		while(true) {
			int rY = ran.nextInt(size);
			int rX = ran.nextInt(size);
			
			if(map[rY][rX] == 1 || map[rY][rX] == 2 || 
					map[rY][rX] == 3 || map[rY][rX] == 4) {
				continue;
			}
			map[rY][rX] = itemNum;				
			
			itemCount -= 1;
			
			if(itemCount == 0) { break;	}
		}
		
		while(true) {
			
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					if(map[i][j] == itemNum) {
						System.out.print("* ");
					}else {
						System.out.print(map[i][j] + " ");
					}
				}
				System.out.println();
			}
			System.out.println();
			
			System.out.print("1)left 2)right 3)up 4)down : ");
			int sel = scan.nextInt();
			
			int yy = 0;
			int xx = 0;
			if(sel == 1) {
				yy = y[0];
				xx = x[0] - 1;
			}
			else if(sel == 2) {
				yy = y[0];
				xx = x[0] + 1;
			}
			else if(sel == 3) {
				yy = y[0] - 1;
				xx = x[0];
			}
			else if(sel == 4) {
				yy = y[0] + 1;
				xx = x[0];
			}

		    if(yy < 0 || xx < 0) { continue; }
			if(yy >= size || xx >= size) { continue; }
			
			if(map[yy][xx] != 0 && map[yy][xx] != itemNum) { continue; }

			if(map[yy][xx] == itemNum) {
				if(snakeSize >= 8) continue;
				
				int[] ty = y;
				int[] tx = x;
				y = new int[snakeSize + 1];
				x = new int[snakeSize + 1];
				for(int i = 0; i < snakeSize; i++) {
					y[i] = ty[i];
					x[i] = tx[i];
				}
				snakeSize += 1;
			}
			
			//System.out.println("뱀길이 = " + snakeSize);
			
			for(int i=0; i<snakeSize; i++) {
				map[y[i]][x[i]] = 0;
			}
			
			for(int i=snakeSize-1; i>0; i--) {
				y[i] = y[i - 1];
				x[i] = x[i - 1];
			}
			
			x[0] = xx;
			y[0] = yy;
			
			for(int i=0; i<snakeSize; i++) {
				map[y[i]][x[i]] = i + 1;
			}
			
		}
		
	}
}