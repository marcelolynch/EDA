package parciales2_eda;

import java.util.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class State {
	int[] table1, table2;
	
	public State(int size){
		table1 = new int[size];
		table2 = new int[size];
	}
	
	Double h;
	public double fitness(double[][] affinity){
		if(h!=null) return h;
		h = 0.0;
		for(int i = 0; i < table1.length ; i++){
			for(int j = 0 ; j < table1.length ; j++){
				h += affinity[table1[i]][table2[j]];
				h += affinity[table2[j]][table1[i]];
			}
		}
		return h;
	}

	public List<State> neighbors(){
		List<State> n = new ArrayList<State>();
		for(int i = 0; i < table1.length ; i++){
			for(int j = 0; j < table2.length ; j++){
				State s = this.clone();
				int aux = s.table1[i];
				s.table1[i] = s.table2[j];
				s.table2[j] = aux;
				n.add(s);
			}
		}
	
		return n;
	}
	
	
	public State clone(){
		State clone = new State(table1.length);
		for(int i = 0; i < table1.length ; i++){
			clone.table1[i] = table1[i];
			clone.table2[i] = table2[i];
		}
		return clone;
	}
	
	
	
	
	
	/** Hill climbing **/
	private static State randomState(int size) {
		State s = new State(size);
		int totalPeople = size * 2;
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 0; i < totalPeople ; i++){
			l.add(i);
		}
		
		Collections.shuffle(l);
		
		Iterator<Integer> iter = l.iterator();
		for(int i = 0 ; i < s.table1.length ; i++){
			s.table1[i] = iter.next();
		}

		for(int i = 0 ; i < s.table2.length ; i++){
			s.table2[i] = iter.next();
		}
		
		
		
		return s;
	}

	
	public void print(){
		System.out.println("Table 1:");
		System.out.print("[");
		for(int i = 0; i < table1.length ; i++)
			System.out.print(table1[i] + " ");
		System.out.println("]");

		System.out.println("Table 2:");
		System.out.print("[");
		for(int i = 0; i < table2.length ; i++)
			System.out.print(table2[i] + " ");
		System.out.println("]");
		
		System.out.println("Fitness" + h);
	}
	
	public static State approxSolution(int tableSize, double[][] affinity, int iterations){
		State current = randomState(tableSize);
		State max = current;
		
		int i = 0;
		while(i++ < iterations){
			boolean better = false;
			do{
				better = false;
				List<State> neighbors = current.neighbors();
				State bestNeighbor = current;
				
				for(State neighbor: neighbors){
					if(bestNeighbor.fitness(affinity) < neighbor.fitness(affinity)){
						bestNeighbor = neighbor;
					}
				}
				if(bestNeighbor != current){
					current = bestNeighbor;
					better = true;
				}
			}while(better);
			
			if(current.fitness(affinity) > max.fitness(affinity)){
				max = current;
			}
			//Alcance un maximo local
			//Arranca de vuelta en otro lugar del dominio 
			//a ver si encuentra mejor maximo local
			current = randomState(tableSize); 

		}
		
		return max;
	}
	
	public static State approxSolution2(int tableSize, double[][] affinity, int iterations){
		State current = randomState(tableSize);
		State max = current;
		
		int i = 0;
		while(i++ < iterations){
			if(current.fitness(affinity) > max.fitness(affinity)){
				max = current;
			}
			List<State> neighbors = current.neighbors();
			State bestNeighbor = current;
			for(int j = 0; j < neighbors.size() ; j++){
				if(bestNeighbor.fitness(affinity) < neighbors.get(j).fitness(affinity)){
					bestNeighbor = neighbors.get(j);
				}
			}
			if(!bestNeighbor.equals(current)){
				current = bestNeighbor;
			} else{ 
				//Alcance un maximo local
				//Arranca de vuelta en otro lugar del dominio 
				//a ver si encuentra mejor maximo local
				current = randomState(tableSize); 
			}
		}
		
		return max;
	}

	
	public static double[][] randomAffinity(int people){
		double[][] m = new double[people][people];
		for(int i = 0; i < people ; i++){
			for(int j = 0; j < people; j++)
				m[i][j] = (i==j) ? 1 : Math.random();
		}
		
		return m;
	}

	public static void main(String[] args) {
		double[][] affinity = randomAffinity(50);
		State s1 = approxSolution(25, affinity, 10);
		State s2 = approxSolution(25, affinity, 10);
	
		State s3 = approxSolution2(25, affinity, 100);
		State s4 = approxSolution2(25, affinity, 100);


		s1.print();
		System.out.println("==========");
		s2.print();
		
		System.out.println("===========");
		System.out.println("===========");

		s3.print();
		System.out.println("==========");
		s4.print();

		
	}
}
