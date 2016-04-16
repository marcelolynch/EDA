
public class Cashier<T> {
	Queue<T> queue;
	
	public Cashier(){
		queue = new Queue<T>();
	}
	
	public void attend(){
		
	}
	
	public int length(){
		return queue.size();
	}
}
