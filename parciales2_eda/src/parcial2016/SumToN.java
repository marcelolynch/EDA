package parcial2016;

import java.util.*;

public class SumToN {
	public static Set<List<Integer>> solve(List<Integer> numbers, int n){
		Set<Solution> checked = new HashSet<Solution>();
		Set<List<Integer>> ans = new HashSet<>();
		for(int i=0 ; i < numbers.size() ; i++){
			tryAll(numbers, i, checked, n, new Solution(), ans);
		}
		
		return ans;
	}
	
	
	private static void tryAll(List<Integer> numbers, int index, Set<Solution> checked, 
			int n, Solution prev, Set<List<Integer>> ans){
		Solution newSol = prev.clone();
		newSol.addNumber(numbers.get(index));
		if(checked.contains(newSol))
			return;
		checked.add(newSol);
		int sum = newSol.getSum();
		if(sum > n){
			return;
		}
		else if(sum == n){
			ans.add(newSol.asList());
		}
		else{
			for(int i = 0 ; i < numbers.size() ; i++){
				tryAll(numbers, i, checked, n, newSol, ans);
			}
		}
	}
	
	public static class Solution{
		private Map<Integer, Integer> map;
		private int sum;
		
		public Solution(){
			map = new HashMap<>();
			sum = 0;
		}
		
		public void addNumber(Integer number){
			if(!map.containsKey(number)){
				map.put(number, 0);
			}
			
			map.put(number, map.get(number) + 1);
			sum += number;
		}
		
		public Solution clone(){
			Solution clone = new Solution();
			for(Integer n: map.keySet()){
				clone.map.put(n, map.get(n));
			}
			clone.sum = sum;
			
			return clone;
			
		}
		
		public int getSum(){
			return sum;
		}
		
		public List<Integer> asList(){
			List<Integer> list = new ArrayList<>();
			for(Integer n: map.keySet()){
				int times = map.get(n);
				for(int i = 0; i< times ; i++){
					list.add(n);
				}
			}
			return list;
		}
		
		
		public int hashCode(){
			return map.hashCode();
		}
		
		public boolean equals(Object o){
			if(o == null || !(o instanceof Solution)){
				return false;
			}
			
			return ((Solution)o).map.equals(map);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Set<List<Integer>> s = solve(l, 10);
		for(List<?> list : s){
			System.out.println(list);
		}
	
	}

}
