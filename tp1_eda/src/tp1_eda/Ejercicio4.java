package tp1_eda;

public class Ejercicio4 {	
	static boolean ej4a(int[] arr){
		if(arr[arr.length -1] < arr.length 
				|| arr[0] > 0){
			return false;
		}
	
		for(int i=0; i<arr.length ; i++){
			if(i+1 == arr[i])
				return true;
			else if(arr[i]>i)
				return false;
		}
		
		return false;
	}
	
	static boolean ej4b(int[] arr){ 			//	O(log(N))
			return binSearch(arr,0,arr.length);
	}


	
	static boolean binSearch(int[] arr, int start, int end){
		int middle = start + (end-start)/2;
		int value = arr[middle];
		
		if(middle == start){
			return value == middle + 1;
		}
		
		/*if(value == middle + 1){   //	Vale la pena? Preguntar
			return true;
		}*/
		
		else if(value >  middle+1){
			return binSearch(arr,start,middle);
		}
		else
			return binSearch(arr,middle,end);
	}
}
