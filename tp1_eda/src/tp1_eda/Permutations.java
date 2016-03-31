package tp1_eda;

import java.util.*;

public class Permutations {
	public static double permutations(String s){
		char[] st = s.toCharArray();
		double time = System.nanoTime() / 1.0;
		permutations(0, st);
		return System.nanoTime() - time;
	}
	
	static int count = 0;
	
	public static void permutations(int n, char[] string){
		if(n == string.length){
	//		System.out.println(string);
			return;
		}
	
		List<Character> swapped = new LinkedList<Character>(); //Puedo hacerlo con char[] gastando mas espacio
															// Quizas menos overhead que usar Character?
		
		for(int i = n; i < string.length ; i++){
			if(!find(string[i], swapped)){
				swapped.add(string[i]);
				swap(string,i,n);
				permutations(n+1, string);
				swap(string,i,n);
			}
		}
	}
	
	
	public static <T> boolean find(T c, List<T> list){
		for(T t: list){
			if(t.equals(c)){
				return true;
			}
		}
		return false;
	}

	public static void swap(char[] arr, int i, int j){
		char aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	public static void main(String[] args) {
		permutations("");
		System.out.println(permutations("abcdfg") / permutations("hijklm"));
	}
}
