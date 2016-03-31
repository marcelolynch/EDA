package tp1_eda;

import java.util.Random;

public class Ejercicio1 {
	private static Random random = new Random();
	
	private static int[] randomIntegerArray(int size){
		int[] arr = new int[size];
		for(int i=0; i<size; i++){
			arr[i] = random.nextInt(200);
		}
		return arr;
	}
	
	private static void swap(int[] arr, int i, int j){
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	private static void bubbleSort(int[] arr){
		int size = arr.length;
		for(int i = 1; i < size ; i++){
			for(int j = 0; j < size - i; j++){
				if(arr[j] > arr[j+1]){
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	
	public static double arraySorter(int n){
		double time = System.nanoTime() / 1.0;
		int[] arr = randomIntegerArray(n);

		bubbleSort(arr);

		time  = System.nanoTime()/1.0 - time;
		return time;

	}
	
	public static void main(String[] args) {
		/* Ejercicio 1
		 * 
		System.out.println(arraySorter(6000)/arraySorter(3000)); //Duplico la entrada
		System.out.println(arraySorter(4000)/arraySorter(2000)); 
		System.out.println(arraySorter(40000)/arraySorter(20000)); //Si el tiempo es cuadratico
		System.out.println(arraySorter(80000)/arraySorter(40000)); //estos deberian dar 4
		*
		*/

		/*Ejercicio 3
		 * 

		
		int[] arr = new int[]{1,2,3,4,5,6,7,8,9,9,10,11};
		
		System.out.println(Ejercicio3.repeatedOrdered(arr));
		*/
		
		int[] arr = new int[]{1,3,5,7,9,11,13};
		int[] arr2 = new int[]{2,4,6,8,10,12,14};
		
		for(int i: Ejercicio6.orderedIntersection(arr, arr2)){
			System.out.print("  ");
		}
		
	}
	
	
}
