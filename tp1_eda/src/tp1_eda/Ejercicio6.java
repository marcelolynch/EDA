package tp1_eda;

import java.util.*;

public class Ejercicio6 {
	
	public static int[] intersection(int[] a1, int[] a2){
		int[] ans = new int[(a1.length > a2.length) ? a2.length : a1.length];
		
		boolean found = false;
		int k = 0;
		
		for(int i = 0; i < a1.length ; i++, found = false){
			for(int j = 0; !found && j < a2.length ; j++){
				found = a1[i] == a2[j];
				
				if(found)
					ans[k++] = a1[i];
			}
		}
		int[] sized = new int[k];
		
		for(int i = 0; i<k ; i++){
			sized[i] = ans[i];	//Agrega N a la complejidad
		}
		
		return sized; //Esto es un asco
		
	}
	
	
	public static int[] orderedIntersection(int[] a1, int[] a2){
		int[] ans = new int[(a1.length > a2.length) ? a2.length : a1.length];
				
		int k = 0;

		for(int i = 0, j = 0; i < a1.length && j < a2.length ; ){
			if(a1[i] == a2[j]){
				ans[k++] = a1[i++];
				j++;
			}
			else if(a1[i] > a2[j]){
				j++;
			}
			else{
				i++;
			}
		}
		
		int[] sized = new int[k];
		
		for(int i = 0; i<k ; i++){
			sized[i] = ans[i];
		}
		
		return sized; //Esto es un asco supongo
		
	}
}
