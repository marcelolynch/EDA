package tp1_eda;

public class Ejercicio2 {
	static boolean isAscending(int[] arr){
		boolean sorted = true;
		for(int i = 0, times=arr.length - 1; sorted && i<times; i++){
			sorted = arr[i] <= arr[i+1];
		}
		
		return sorted;
	}
}
