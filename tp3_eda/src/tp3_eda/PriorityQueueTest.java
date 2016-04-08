package tp3_eda;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.enqueue(8,1);
		q.enqueue(1,10);
		q.enqueue(3,1);
		q.enqueue(4, 2);
		q.enqueue(4, 5);
		
		while(!q.isEmpty()){
			System.out.println(q.dequeue());
		}
	}
	
	
}
