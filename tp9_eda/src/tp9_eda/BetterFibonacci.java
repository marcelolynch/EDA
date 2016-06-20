package tp9_eda;

public class BetterFibonacci {
	public static long badFibonacci(int n){
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		return badFibonacci(n-1) + badFibonacci(n-2);
	}
	
	
	public static long fibo(int n){
		long[] fibo = new long[]{0,1};
		for(int i = 0 ; i < n ; i++){
			long aux = fibo[0];
			fibo[0] = fibo[0] + fibo[1];
			fibo[1] = aux;
		}
		
		return fibo[0];
	}
	
	
	
	public static void main(String[] args) {
		long tick;
		long tock;
		
		tick = System.nanoTime();
		System.out.println(badFibonacci(45));
		tock = System.nanoTime();
		System.out.println("Bad:" + (tock-tick)/1000000.0);
		
		tick = System.nanoTime();
		System.out.println(fibo(45));
		tock = System.nanoTime();
		
		System.out.println("Good:" + (tock-tick)/1000000.0);

	}

}
