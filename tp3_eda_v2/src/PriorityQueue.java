import java.util.NoSuchElementException;

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
	
	
	//Enqueue recursivo
	public void enqueue(T elem, int priority){
		first = enqueue(first, elem, priority);
	}
	
	private Node<Queue<T>> enqueue(Node<Queue<T>> node, T elem, int priority){
		if(node == null || node.priority > priority){
			Node<Queue<T>> n = new Node<Queue<T>>(new Queue<T>(), node, priority);
			n.queue.enqueue(elem);
			return n;
		}
		if(node.priority == priority){
			node.queue.enqueue(elem);
			return node;
		}
		else{
			node.next = enqueue(node.next, elem, priority);
			return node;
		}
	}
	
	
	public T dequeue(){
		if(isEmpty())
			throw new NoSuchElementException();
		
		T deq = first.queue.dequeue();
		
		if(first.queue.isEmpty())
			first = first.next;
		
		return deq;
	}
	
	
	
//	private void enqueueIterative(T elem, int priority){ //Feisimo
//		if(first == null || first.priority > priority){
//			first = new Node<Queue<T>>(new Queue<T>(), first, priority);
//			first.queue.enqueue(elem);
//			return;
//		}
//		
//		Node<Queue<T>> curr = first;
//		Node<Queue<T>> prev = null;
//		while(curr != null && curr.priority < priority){
//			prev = curr;
//			curr = curr.next;
//		}
//		
//		if(curr == null){
//			prev.next = new Node<Queue<T>>(new Queue<T>(), curr, priority);
//			prev.next.queue.enqueue(elem);
//		}
//		
//		else if(curr.priority == priority){
//			curr.queue.enqueue(elem);
//		}
//		else{ //curr.priority > priority. Agrego un nuevo nodo
//			prev.next = new Node<Queue<T>>(new Queue<T>(), curr, priority);
//			prev.next.queue.enqueue(elem);
//		}
//		
//	}
	
}
