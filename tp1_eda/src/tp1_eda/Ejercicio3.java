package tp1_eda;

public class Ejercicio3 {
	static boolean repeated(int[] arr){ 	//O(N^2)
		for(int i = 0; i<arr.length - 1; i++){
			for(int j = i +1; j < arr.length ; j++){
				if(arr[i] == arr[j])
					return true;
			}
		}
		return false;
	}
	
	
	static boolean repeatedOrdered(int[] arr){ // O(N)
		for(int i = 0; i<arr.length - 1; i++){
			if(arr[i] == arr[i+1])
				return true;
		}
		return false;
	}
	
	
	static boolean repeatedOrdered2(int[] arr){ //N log(N)
		for(int i = 0; i<arr.length - 1; i++)
			if(binSearch(arr,i+1,arr.length,arr[i])){
				return true;
			}
		
		return false;
	}
	
	

	static boolean binSearch(int[] arr, int start, int end, int n){
		int middle = start + (end-start)/2;
		int candidate = arr[middle];
		if(middle == start){
			return n==candidate;
		}
		if(n == candidate)
			return true;
		else if(n > candidate)
			return binSearch(arr,middle,end,n);
		else
			return binSearch(arr,start,middle,n);
		
	}
	
	
	
	
	
}
