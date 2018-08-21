package arrays;

public class Operations<T> {

	public void transpose2D(T[][] arr) {
		if(arr == null || arr.length == 0)
			return;
		
		for(int i = 0 ; i < arr.length ; i++) {
			for (int j = i ; j < arr[i].length ; j++) {
				T temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}
	}
	
	public void print2DArray(T[][] arr) {
		System.out.println("\n\n");
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[i].length ; j++) 
				System.out.print(arr[i][j] + " , ");
			System.out.println();
		}
	}
}
