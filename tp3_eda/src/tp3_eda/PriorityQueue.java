package tp3_eda;

public class PriorityQueue<T> {
	private static class Node<T>{
		int priority;
		Node<T> next;
		T queue;
		
		public Node(T value, Node<T> next, int priority) {
			this.priority = priority;
			this.next = next;
			this.queue = value;
		}
	}
	
	private Node<Queue<T>> first;
	
	
	public boolean isEmpty(){
		return first == null;
	}
	
	
	public void enqueue(T elem, int priority){
		if(first == null || first.priority > priority){
			first = new Node<Queue<T>>(new Queue<T>(), first, priority);
			first.queue.enqueue(elem);
			return;
		}
		
		Node<Queue<T>> curr = first;
		Node<Queue<T>> prev = null;
		while(curr != null && curr.priority < priority){
			curr = curr.next;
			prev = curr;
		}
		
		if(curr == null)
			prev.next = new Node<Queue<T>>(new Queue<T>(), curr, priority);
		
		else if(curr.priority == priority){
			curr.queue.enqueue(elem);
		}
		else{ //curr.priority > priority. Agrego un nuevo nodo
			prev.next = new Node<Queue<T>>(new Queue<T>(), curr, priority);
		}
		
	}
	
	
	public T dequeue(){
		if(first == null)
			throw new IllegalStateException();
		
		T deq = first.queue.dequeue();
		
		if(first.queue.isEmpty())
			first = first.next;
		
		return deq;
	}
	
}
