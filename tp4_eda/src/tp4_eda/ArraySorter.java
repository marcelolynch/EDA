package tp4_eda;

public class ArraySorter {
	
	public static void swap(int[] arr, int i, int j){
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	
	public static void quickSort(int[] arr){
		qsort(arr, 0, arr.length - 1);
	}
	
	private static void qsort(int[] arr, int low, int high){
		
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

	
	public static boolean isOrdered(int[] arr){
		for(int i = 1; i < arr.length ; i++){
			if(arr[i-1] > arr[i])
				return false;
		}
		return true;
	}
	
	
	public static int[] createRandomArray(int size) {

		int[] ret = new int[size];

		for (int i=0; i<size; i++) {
			ret[i] = (int)(Math.random() * 100);
		}

		return ret;
	}
	public static void main(String[] args) {

		int[] sizes = new int[] {50000, 100000, 200000, 400000, 800000, 1000000, 1200000};

		long start, time;

		for (int size : sizes) {

		int[] arr = createRandomArray(size);
		start = System.currentTimeMillis();

		quickSort(arr);

		time = System.currentTimeMillis() - start;
	//	System.out.println(isOrdered(arr));
		System.out.println("Size: " + size + " Time: " + time + " ms");

		}

		}
	
	
}
