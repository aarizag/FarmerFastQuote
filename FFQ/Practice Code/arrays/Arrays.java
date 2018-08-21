package arrays;

public class Arrays {
	public static void main(String[] args) {
		Operations<Integer> o = new Operations();
		
		int count = 0;
		Integer[][] arr = new Integer[10][10];
		for(int i = 0 ; i < 10 ; i++) {
			for (int j = 0 ; j < 10 ; j++) {
				arr[i][j] = count;
				count++;
			}
		}
		
		o.print2DArray(arr);
		o.transpose2D(arr);
		o.print2DArray(arr);
	}
}
