package sorting;

import java.util.*;

public class Sorter {
	
	private Random rand = new Random();					
	private int[] intarray = new int[20];	
	
	public Sorter(){										
		arrRand();											
	}														


	///////////////////////////////////////////////////////////
	//// METODOS PARA MANEJAR Y CHEQUEAR EL ARREGLO  //////////
	///////////////////////////////////////////////////////////
															///
															///
	public void arrRand(){									///
		for(int i = 0; i < intarray.length ; i++){			///
			intarray[i] = rand.nextInt(1000);				///
		}													///
	}														///
															///
															///
															///
	public void printArr(){									///
		for(int i : intarray){								///
			System.out.print(i);							///
			System.out.print("  ");							///
		}													///
		System.out.println();								///
	}														///
															///
															///
	public void shuffle(){									///
		for(int i = 0; i < intarray.length ; i++){			///
			int pos = rand.nextInt(i+1);					///
			swap(intarray, i, pos);							///
		}													///
	}														///
															///
	public boolean ordered(int[] arr){						///
		for(int i = 1; i < arr.length ; i++){				///
			if(arr[i-1] > arr[i])							///
				return false;								///
		}													///
		return true;										///
															///
	}														///
///////////////////////////////////////////////////////////////
/////////////       TESTEO    /////////////////////////////////
	
	public void reSort(){
		shuffle();
		printArr();
		
		
		System.out.println("QuickSort");
		quickSort(intarray);

		printArr();
		System.out.print("Ordered: ");
		System.out.println(ordered(intarray));
	}
	
///////////////////////////////////////////////////////////////
	
	
	
/////////////////////// ALGORITMOS /////////////////////////////
	
	
	public void swap(int[] arr, int i, int j){
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	
	
	/** BUBBLESORT */
	public void bubbleSort(int[] arr){
		int n = arr.length;
		boolean swapped = true;
		for(int i = 0 ; swapped && i < n - 1 ; i++){
			swapped = false;
			for(int k = i+1 ; k < n ; k++){
				if(arr[i] > arr[k]){
					swap(arr,i,k);
					swapped = true;
				}
			}
		}
	}
	
	
	/** INSERTION SORT  */
	public void insertionSort(int[] arr){
		int n = arr.length;
		int min;
		for(int i = 0; i < n-1 ; i++){
			min = i;
			for(int j = i+1 ; j < n ; j++){
				if(arr[j] < arr[min]){
					min = j;
				}
			}
			if(min != i){
				swap(arr,i,min);
			}
		}
	}
	
	
	/** COMBSORT  */
	public void combSort(int[] arr){
		int n = arr.length;
		
		int gap = arr.length;
		double shrink = 1.3;
		gap /= shrink;
		
		boolean swapped = true;
		while(gap > 1 || swapped){
			int i = 0;
			swapped = false;
			while(i+gap < n){
				if(arr[i] > arr[i+gap]){
						swap(arr,i,i+gap);
						swapped = true;
					}
				i++;
			}
			gap /= shrink;	
		}
	}
	
	 
	/** MERGESORT  */
	public void mergeSort(int[] arr){
		int[] aux = new int[arr.length];
		msort(arr, aux, 0, arr.length - 1);
		
	}
	
	
	//MergeSort no recursivo en el lugar
	//Toma primero subarreglos de 2, despues de 4, etc.
	//Y los va mergenado
	public void altMergeSort(int[] arr){
		int[] aux = new int[arr.length];
		
		int n = arr.length;
		for(int size = 1; size < n ; size += size){
			for(int low = 0; low < n - size ; low += 2*size)
				merge(arr, aux, low, low+size-1, min(low + 2*size - 1, n-1));
		}
	}
	
	public int min(int a, int b){
		return a < b ? a : b;
	}
	
	private void msort(int[] arr, int[] aux, int low, int high){
	
		if(low == high)
			return;
		
		int mid = (low + high)/2;
		
		msort(arr, aux, low, mid);
		msort(arr, aux, mid+1, high);
		merge(arr, aux, low, mid, high);
}
	
	private void merge(int[] arr, int[] aux, int low, int mid, int high){
		//Arr se mergea ordenado
		//Copio todo al arreglo auxiliar
		for(int k = low; k <= high ; k++){
			aux[k] = arr[k];
		}
		
		int i = low, j = mid + 1;
		for(int k = low; k <= high ; k++){
			//Si ya terminaron i o j
			if(i > mid)
				arr[k] = aux[j++];
			else if(j > high)
				arr[k] = aux[i++];
			
			//Si no, comparo
			else if(aux[i] <= aux[j])
				arr[k] = aux[i++];
			else
				arr[k] = aux[j++];
		}
		

	}
	
	
	/** QUICKSORT */
	public void quickSort(int[] arr){
		qsort(arr, 0, arr.length - 1);
	}
	
	private void qsort(int[] arr, int low, int high){
		
		if(low >= high)
			return;

		//low sera el pivot
		int i = low, j = high+1; //corridos un indice por preincremento y predecremento
		
		while(i < j){
			while(i < high && arr[++i] < arr[low])
				; //Avanza el puntero
		
			while(j > low && arr[--j] > arr[low])
				; // Retrocede el puntero
			
			swap(arr,i,j);
		}
		
		swap(arr,i,j); // El ultimo swap es i > j y no iba, se corrige aca

		swap(arr,j,low); //j quedo en donde debe estar el pivote (justo al final de los mas chicos)
	
		qsort(arr, low, j-1);
		qsort(arr, j+1, high);
		
	}	
	
	
	/** RADIX SORT */
	
	public void radixSort(int[] arr, int maxDigits){
		
//		Queue<Integer>[] buckets = new LinkedList<>[10];
		
		for(int n = 1; n <= maxDigits ; n++){
			
		}
		
	}
	
	private int getLastDigit(int n, int pos){
		return (n/(int)Math.pow(10, pos-1)) % 10;
	}
	
	public static void main(String[] args) {
		Sorter s = new Sorter();
		s.reSort();
	}

}
