package tp6_eda;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grasa {
	
	static Random rand = new Random(System.currentTimeMillis());
	
	private static boolean isVowel(char c){
		c = Character.toLowerCase(c);
		return c=='a' || c=='e' || c=='i' || c == 'o' || c == 'u';
	}
	
	public static String toGrasa(String s){
		char[] arr = s.toCharArray();
		
		String str = "";
		for(char c : arr){
			str = str + Character.toString(c);			
			if(isVowel(c) && rand.nextDouble() < 0.3){
				str = str + Character.toString(Character.toLowerCase(c));
				if(rand.nextDouble() < 0.7){
					str = str + "h";
				}
			}
			}
		
		char[] arr2 = str.toCharArray();
		for(int i=0 ; i < arr2.length ; i++){
			if(arr2[i] == 'c' || arr2[i] == 'C'){
				arr2[i] = 'k';
			}
			if((arr2[i] == 'b' || arr2[i] == 'B') && rand.nextDouble() < 0.4){
				arr2[i] = 'v';
			}
			if((arr2[i] == 'v' || arr2[i] == 'V') && rand.nextDouble() < 0.4){
				arr2[i] = 'b';
			}
			if((arr2[i] == 'z' || arr2[i] == 'Z') && rand.nextDouble() < 0.4){
				arr2[i] = 's';
			}

			if(i%2 == 0){
				arr2[i] = Character.toUpperCase(arr2[i]);
			}
		}
		
		return new String(arr2);
	}
	
	public static void main(String[] args) {
		String s = "Aguante la vida";
		System.out.println("Traduciendo: >" + s);
		System.out.println(toGrasa(s));
	}
}
