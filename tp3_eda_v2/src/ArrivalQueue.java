import java.util.ArrayList;
import java.util.List;

public class ArrivalQueue<T> {
	
	private int min;
	private List<Cashier<T>> cashiers = new ArrayList<Cashier<T>>();
	
	public ArrivalQueue(int n){
		for(int i = 0; i < n ; i++){
			cashiers.add(new Cashier<T>());
			min = 0;
		}
	}
	
	public void newTask(T task){
		
	}
	
	
}
