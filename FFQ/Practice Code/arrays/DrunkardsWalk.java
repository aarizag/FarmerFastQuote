package arrays;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * The Drunkard's Walk. 
 * A drunkard in a grid of streets randomly picks one of four directions and stumbles to the next intersection,
 * then again randomly picks one of four directions, and so on. 
 * You might think that on average the drunkard doesn't move very far because the choices 
 * cancel each other out, but that is actually not the case.
 * 
 * Represent locations as integer pairs (x,y). 
 * Implement the drunkard's walk over 100 intersections, 
 * starting at (0,0) , and print the ending location.
 */
public class DrunkardsWalk {
	public void walk() {
		Queue<Integer[]> path = new LinkedList<>();
		Random r = new Random();
		int decision, xPos = 0, xNeg = 0, yPos = 0, yNeg = 0;
		int x = 0;
		int y = 0;
		Integer[] currentPos = {0,0};
		
		for(int i = 0 ; i < 75 ; i++) {
			decision = r.nextInt(4);
			path.add(currentPos);
			currentPos = new Integer[] {currentPos[0], currentPos[1]};
			
			switch(decision) {
			case 0:
				if(xPos == 0) {
					x++;
					xNeg++;
				}
				else 
					xPos--;
				currentPos[0] --;
				break;
			case 1:
				if(xNeg == 0) {
					x++;
					xPos++;
				}
				else 
					xNeg--;
				currentPos[0] ++;
				break;
			case 2:
				if(yPos == 0) {
					y++;
					yNeg++;
				}
				else
					yPos--;
				currentPos[1] --;
				break;
			case 3:
				if(yNeg == 0) {
					y++;
					yPos++;
				}
				else
					yNeg--;
				currentPos[1] ++;
				break;
			}
		}
		
		int[][] streets = new int[x][y];
		streets[x / 2] [y / 2] += 7;
		
		while(!path.isEmpty()) {
			Integer[] i = path.poll();
			streets[i[0]+x/2][i[1]+y/2]++;
			print(streets);
//			System.out.println(i[0] + ", " + i[1]);
		}
	}
	
	private void print(int[][] streets) {
		for (int i = 0 ; i < 75 ; i++)
			System.out.println();
		
		for(int[] i : streets) {
			for(int j : i)
				System.out.print("[" + j + "]  ");
			System.out.println();
		}
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DrunkardsWalk dw = new DrunkardsWalk();
		dw.walk();
	}
}
